package grupo6.marketplace.ecoturismo.asyntask.proveedor;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.proveedor.CrearProductoFragment;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import grupo6.marketplace.ecoturismo.util.Constantes;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class CrearProductoAsynctTask extends AsyncTask<Producto, Void, Void> {

	protected static final String TAG = "CrearProductoAsynctTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient
			.newInstance("");
	private CrearProductoFragment crearProductoFragment;

	public CrearProductoAsynctTask(CrearProductoFragment crearProductoFragment) {
		this.crearProductoFragment = crearProductoFragment;
		progressDialog = new ProgressDialog(
				this.crearProductoFragment.getActivity());
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(
				androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),
				Constantes.TIME_OUT_SOCKET);
		respuesta = CrearProducto.Respuesta.FALLO;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(crearProductoFragment
				.getString(R.string.creando_progress));
		progressDialog.show();
	}

	@Override
	protected Void doInBackground(Producto... params) {
		try {
			Producto p = params[0];

			HttpPost httpPostRequest = new HttpPost(
					CrearProducto.URL_CREAR_PRODUCTO);
			httpPostRequest.setHeader(Constantes.ACCEPT,
					Constantes.APPLICATION_JSON);
			httpPostRequest.setHeader(Constantes.CONTENT_TYPE,
					Constantes.APPLICATION_JSON);

			StringEntity json = getJsonStringEntity(p);
			httpPostRequest.setEntity(json);

			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpPostRequest, responseHandler);
		} catch (Exception e) {

		}
		return null;

	}

	protected void onPostExecute(Void res) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case CrearProducto.Respuesta.EXITO:
			AlertUtilidades.mostrarAlert(crearProductoFragment.getActivity(),
					R.drawable.ic_launcher, R.string.app_name,
					R.string.producto_creado, R.string.ok);
			break;

		case CrearProducto.Respuesta.FALLO:
			Toast.makeText(crearProductoFragment.getActivity(),
					R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

	}

	private class JSONResponseHandler implements ResponseHandler<Void> {

		@Override
		public Void handleResponse(HttpResponse response) {
			respuesta = response.getStatusLine().getStatusCode();
			cerrarConexion();
			return null;
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

	private StringEntity getJsonStringEntity(Producto p) throws Exception {
		JSONObject json = new JSONObject();
		json.put(CrearProducto.Solicitud.NOMBRE_PRODUCTO, p.getNombre());
		json.put(CrearProducto.Solicitud.LUGAR_PRODUCTO, p.getLugar());
		json.put(CrearProducto.Solicitud.CIUDAD_PRODUCTO, p.getCiudad());
		json.put(CrearProducto.Solicitud.PRECIO_PRODUCTO, p.getPrecio());
		json.put(CrearProducto.Solicitud.FECHA_INICIO_PRODUCTO,
				p.getFechaInicio());
		json.put(CrearProducto.Solicitud.URL_IMAGEN_PRODUCTO, p.getUrlImagen());
		return new StringEntity(json.toString(), HTTP.UTF_8);
	}

	private static class CrearProducto {

		public static final String URL_CREAR_PRODUCTO = Constantes.URL_BASE
				+ "services/producto/crear";

		public static class Solicitud {
			public static final String NOMBRE_PRODUCTO = "nombre";
			public static final String LUGAR_PRODUCTO = "lugar";
			public static final String CIUDAD_PRODUCTO = "ciudad";
			public static final String PRECIO_PRODUCTO = "precio";
			public static final String FECHA_INICIO_PRODUCTO = "fechaInicio";
			public static final String URL_IMAGEN_PRODUCTO = "urlImagen";
		}

		public static class Respuesta {
			public static final int EXITO = 200;
			public static final int FALLO = 500;

		}

	}

}
