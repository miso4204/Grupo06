package grupo6.marketplace.ecoturismo.asyntask.carrito;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.carrito.CarritoComprasFragment;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.Constantes;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class CargarCarritoComprasAsyncTask  extends AsyncTask<String, Void, List<Producto>> {
	
	protected static final String TAG = "CargarCarritoComprasAsyncTask";
	private int respuesta;
	private String userName;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient.newInstance("");
	private CarritoComprasFragment carritoComprasFragment;
	
	public CargarCarritoComprasAsyncTask(CarritoComprasFragment carritoComprasFragment) {
		this.carritoComprasFragment = carritoComprasFragment;
		HttpConnectionParams.setConnectionTimeout(androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),Constantes.TIME_OUT_SOCKET);
		respuesta = Carrito.Respuesta.FALLO;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected List<Producto> doInBackground(String... params) {
		try{
			userName = params[0];
			
			String urlFinal = Carrito.URL_GET_CARRITO
						      .replace(Carrito.USER_NAME, userName);
			
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
		switch (respuesta) {
		case Carrito.Respuesta.EXITO:
			if(productos != null){
				carritoComprasFragment.cargarCarritoCompras(productos);
				if(productos.isEmpty()){
					Toast.makeText(carritoComprasFragment.getActivity(), R.string.carrito_compras_vacio, Toast.LENGTH_SHORT).show();
				}
			}else{
				carritoComprasFragment.cargarCarritoCompras(new ArrayList<Producto>());
				Toast.makeText(carritoComprasFragment.getActivity(), R.string.carrito_compras_vacio, Toast.LENGTH_SHORT).show();
			}
			break;

		case Carrito.Respuesta.FALLO:
			Toast.makeText(carritoComprasFragment.getActivity(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

		
	}
	
	private class JSONResponseHandler implements ResponseHandler<List<Producto>> {

		@Override
		public List<Producto> handleResponse(HttpResponse response) {
			List<Producto> productos = null;
			try {
				respuesta = response.getStatusLine().getStatusCode();
				if (respuesta == Carrito.Respuesta.EXITO) {
					String JSONResponse = new BasicResponseHandler().handleResponse(response);
					JSONArray responseObjecto = (JSONArray) new JSONTokener(JSONResponse).nextValue();
					productos = new ArrayList<Producto>();
					
					for(int i=0;i<responseObjecto.length();i++){
						JSONObject jsonProducto = responseObjecto.getJSONObject(i);
						Producto p = new Producto();
						p.setId(jsonProducto.getLong(Carrito.Respuesta.ID_PRODUCTO)); 
				        p.setNombre(jsonProducto.getString(Carrito.Respuesta.NOMBRE_PRODUCTO));
				        p.setLugar(jsonProducto.getString(Carrito.Respuesta.LUGAR_PRODUCTO));
				        p.setCiudad(jsonProducto.getString(Carrito.Respuesta.CIUDAD_PRODUCTO));
				        p.setPrecio(jsonProducto.getDouble(Carrito.Respuesta.PRECIO_PRODUCTO));
				        p.setFechaInicio(jsonProducto.getString(Carrito.Respuesta.FECHA_PRODUCTO));
				        p.setUrlImagen(jsonProducto.getString(Carrito.Respuesta.URL_IMAGEN_PRODUCTO));
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
	
	private static class Carrito {


	    public static final String USER_NAME = "{userName}";
		
		public static final String URL_GET_CARRITO = Constantes.URL_BASE + "/services/carrito/listar/"+USER_NAME;

		public static class Respuesta {
	        public static final String ID_PRODUCTO = "id";
	        public static final String NOMBRE_PRODUCTO = "nombre";
	        public static final String LUGAR_PRODUCTO = "lugar";
	        public static final String CIUDAD_PRODUCTO = "ciudad";
	        public static final String PRECIO_PRODUCTO = "precio";
	        public static final String FECHA_PRODUCTO = "fechaInicio";
	        public static final String URL_IMAGEN_PRODUCTO = "urlImagen";
			public static final int EXITO = 200;
			public static final int FALLO = 500;
		}
		
	}
}
