package grupo6.marketplace.ecoturismo.asyntask.busqueda;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.busquedas.ProductosPorPrecioFragment;
import grupo6.marketplace.ecoturismo.modelo.CalificacionDTO;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.Constantes;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class BusquedaPorPrecioAsyncTask extends AsyncTask<Double, Void, List<Producto>> {
	
	protected static final String TAG = "BusquedaPorPrecioAsyncTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private double precioInicial;
	private double precioFinal;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient.newInstance("");
	private ProductosPorPrecioFragment productosFragment;
	
	public BusquedaPorPrecioAsyncTask(ProductosPorPrecioFragment productosFragment) {
		this.productosFragment = productosFragment;
		progressDialog = new ProgressDialog(this.productosFragment.getActivity());
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),Constantes.TIME_OUT_SOCKET);
		respuesta = Busqueda.Respuesta.FALLO;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(productosFragment.getString(R.string.progress_buscando));
		progressDialog.show();
	}

	@Override
	protected List<Producto> doInBackground(Double... params) {
		try{
			precioInicial = params[0];
			precioFinal = params[1];
			
			String urlFinal = Busqueda.URL_BUSQUEDA
						      .replace(Busqueda.PRECIO_INICIAL, ""+precioInicial)
							  .replace(Busqueda.PRECIO_FINAL, ""+precioFinal);
			
			HttpGet httpGetRequest = new HttpGet(urlFinal);
			httpGetRequest.setHeader(Constantes.ACCEPT, Constantes.APPLICATION_JSON);
			httpGetRequest.setHeader(Constantes.CONTENT_TYPE, Constantes.APPLICATION_JSON);
		    
			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpGetRequest, responseHandler);	
		}catch(Exception e){
			
		}
		return null;
		
	}
	
	protected void onPostExecute(List<Producto> productos) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case Busqueda.Respuesta.EXITO:
			if(productos != null){
				productosFragment.mostrarProducto(productos);
				if(productos.isEmpty()){
					Toast.makeText(productosFragment.getActivity(), R.string.no_se_encontraron_productos, Toast.LENGTH_SHORT).show();
				}
			}else{
				productosFragment.limpiarLista();
				Toast.makeText(productosFragment.getActivity(), R.string.no_se_encontraron_productos, Toast.LENGTH_SHORT).show();
			}
			break;

		case Busqueda.Respuesta.FALLO:
			Toast.makeText(productosFragment.getActivity(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

		
	}
	
	private class JSONResponseHandler implements ResponseHandler<List<Producto>> {

		@Override
		public List<Producto> handleResponse(HttpResponse response) {
			List<Producto> productos = null;
			try {
				respuesta = response.getStatusLine().getStatusCode();
				if (respuesta == Busqueda.Respuesta.EXITO) {
					String JSONResponse = new BasicResponseHandler().handleResponse(response);
					JSONArray responseObjecto = (JSONArray) new JSONTokener(JSONResponse).nextValue();
					productos = new ArrayList<Producto>();
					
					for(int i=0;i<responseObjecto.length();i++){
						JSONObject jsonProducto = responseObjecto.getJSONObject(i);
						Producto p = new Producto();
						p.setId(jsonProducto.getLong(Busqueda.Respuesta.ID_PRODUCTO)); 
				        p.setNombre(jsonProducto.getString(Busqueda.Respuesta.NOMBRE_PRODUCTO));
				        p.setLugar(jsonProducto.getString(Busqueda.Respuesta.LUGAR_PRODUCTO));
				        p.setCiudad(jsonProducto.getString(Busqueda.Respuesta.CIUDAD_PRODUCTO));
				        p.setPrecio(jsonProducto.getDouble(Busqueda.Respuesta.PRECIO_PRODUCTO));
				        p.setFechaInicio(jsonProducto.getString(Busqueda.Respuesta.FECHA_PRODUCTO));
				        p.setUrlImagen(jsonProducto.getString(Busqueda.Respuesta.URL_IMAGEN_PRODUCTO));
				        
				        JSONArray calificacionesJSON = jsonProducto.getJSONArray(Busqueda.Respuesta.CALIFICACIONES_PRODUCTO);
				        List<CalificacionDTO> calificaciones = new ArrayList<CalificacionDTO>();
				        for(int j=0;j<calificacionesJSON.length();j++){
				        	JSONObject jsonCalificacion = calificacionesJSON.getJSONObject(j);
				        	String nombre = jsonCalificacion.getString(Busqueda.Respuesta.CALIFICACIONES_PRODUCTO_NOMBRE);
				        	Double puntuacion = jsonCalificacion.getDouble(Busqueda.Respuesta.CALIFICACIONES_PRODUCTO_PUNTUACION);
				        	Integer cantidadVotantes = jsonCalificacion.getInt(Busqueda.Respuesta.CALIFICACIONES_PRODUCTO_CANTIDAD_VOTANTES);
				        	CalificacionDTO c = new CalificacionDTO(nombre, puntuacion, cantidadVotantes);
				        	calificaciones.add(c);
				        }
				        p.setCalificaciones(calificaciones);
				        productos.add(p);
					}
					
				}

			} catch (Exception e) {
				productos = null;
				e.printStackTrace();
			}
			
			cerrarConexion();
			
			return productos;
		}
		
		private void cerrarConexion() {
			try {
				if (androidHttpCliente != null)
					androidHttpCliente.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Busqueda {


	    public static final String PRECIO_INICIAL = "{precioInicial}";
		public static final String PRECIO_FINAL =   "{precioFinal}";
		
		public static final String URL_BUSQUEDA = Constantes.URL_BASE + "services/producto/buscar_por_precio/"+PRECIO_INICIAL+"/"+PRECIO_FINAL;

		public static class Respuesta {
	        public static final String ID_PRODUCTO = "id";
	        public static final String NOMBRE_PRODUCTO = "nombre";
	        public static final String LUGAR_PRODUCTO = "lugar";
	        public static final String CIUDAD_PRODUCTO = "ciudad";
	        public static final String PRECIO_PRODUCTO = "precio";
	        public static final String FECHA_PRODUCTO = "fechaInicio";
	        public static final String URL_IMAGEN_PRODUCTO = "urlImagen";
	        public static final String CALIFICACIONES_PRODUCTO = "calificaciones";
	        public static final String CALIFICACIONES_PRODUCTO_NOMBRE = "nombre";
	        public static final String CALIFICACIONES_PRODUCTO_PUNTUACION = "puntuacion";
	        public static final String CALIFICACIONES_PRODUCTO_CANTIDAD_VOTANTES = "cantidadVotantes";
			public static final int EXITO = 200;
			public static final int FALLO = 500;
		}
		
	}
}

