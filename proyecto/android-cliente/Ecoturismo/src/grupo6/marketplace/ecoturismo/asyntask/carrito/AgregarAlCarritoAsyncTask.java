package grupo6.marketplace.ecoturismo.asyntask.carrito;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import grupo6.marketplace.ecoturismo.util.Constantes;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class AgregarAlCarritoAsyncTask extends AsyncTask<Object, Void, Void> {

	protected static final String TAG = "AgregarAlCarritoAsyncTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private String usuario;
	private Long idProducto;
	boolean fueAgregado;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient
			.newInstance("");
	private Activity context;

	public AgregarAlCarritoAsyncTask(Activity context) {
		this.context = context;
		progressDialog = new ProgressDialog(context);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(
				androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),
				Constantes.TIME_OUT_SOCKET);
		respuesta = Agregar.Respuesta.FALLO;
		fueAgregado = false;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(context
				.getString(R.string.agregando_progress));
		progressDialog.show();
	}

	@Override
	protected Void doInBackground(Object... params) {
		try {
			usuario = (String) params[0];
			idProducto = (Long) params[1];

			HttpPut httpPutRequest = new HttpPut(Agregar.URL_AGREGAR);
			httpPutRequest.setHeader(Constantes.ACCEPT,
					Constantes.APPLICATION_JSON);
			httpPutRequest.setHeader(Constantes.CONTENT_TYPE,
					Constantes.APPLICATION_JSON);

			StringEntity json = getJsonStringEntity(usuario, idProducto);
			httpPutRequest.setEntity(json);

			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpPutRequest, responseHandler);
		} catch (Exception e) {

		}
		return null;

	}

	@Override
	protected void onPostExecute(Void usuario) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case Agregar.Respuesta.EXITO:
			if(fueAgregado){
				AlertUtilidades.mostrarAlert(context, R.drawable.ic_launcher,
						R.string.app_name, R.string.producto_agregado, R.string.ok);	
			}else{
				Toast.makeText(context, R.string.producto_ya_en_carrito, Toast.LENGTH_SHORT)
				.show();	
			}
			
			break;

		case Agregar.Respuesta.FALLO:
			Toast.makeText(context, R.string.error_conexion, Toast.LENGTH_SHORT)
					.show();
			break;

		}

	}

	private class JSONResponseHandler implements ResponseHandler<Void> {

		@Override
		public Void handleResponse(HttpResponse response){
			respuesta = response.getStatusLine().getStatusCode();
			if (respuesta == Agregar.Respuesta.EXITO) {
				try{
					String JsonResponse = new BasicResponseHandler().handleResponse(response);
					fueAgregado = Boolean.parseBoolean(JsonResponse);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
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

	private StringEntity getJsonStringEntity(String usuario, Long idProducto)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put(Agregar.Solicitud.USUARIO, usuario);
		json.put(Agregar.Solicitud.ID_PRODUCTO, idProducto);
		return new StringEntity(json.toString(), HTTP.UTF_8);
	}

	private static class Agregar {

		public static final String URL_AGREGAR = Constantes.URL_BASE
				+ "services/carrito/agregar";

		public static class Solicitud {
			public static final String USUARIO = "userName";
			public static final String ID_PRODUCTO = "idProducto";
		}

		public static class Respuesta {
			public static final int EXITO = 200;
			public static final int FALLO = 500;

		}
	}

}
