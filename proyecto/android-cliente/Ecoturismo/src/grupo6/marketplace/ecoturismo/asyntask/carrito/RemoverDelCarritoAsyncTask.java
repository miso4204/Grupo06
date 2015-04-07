package grupo6.marketplace.ecoturismo.asyntask.carrito;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.carrito.CarritoComprasFragment;
import grupo6.marketplace.ecoturismo.modelo.Producto;
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

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class RemoverDelCarritoAsyncTask extends AsyncTask<Object, Void, Void> {

	protected static final String TAG = "RemoverDelCarritoAsyncTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private String usuario;
	private Producto producto;
	boolean fueRemovido;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient
			.newInstance("");
	private CarritoComprasFragment carritoComprasFragment;

	public RemoverDelCarritoAsyncTask(CarritoComprasFragment carritoComprasFragment) {
		this.carritoComprasFragment = carritoComprasFragment;
		progressDialog = new ProgressDialog(carritoComprasFragment.getActivity());
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(
				androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),
				Constantes.TIME_OUT_SOCKET);
		respuesta = Remover.Respuesta.FALLO;
		fueRemovido = false;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(carritoComprasFragment.getString(R.string.removiendo_progress));
		progressDialog.show();
	}

	@Override
	protected Void doInBackground(Object... params) {
		try {
			usuario = (String) params[0];
			producto = (Producto) params[1];

			HttpPut httpPutRequest = new HttpPut(Remover.URL_REMOVER);
			httpPutRequest.setHeader(Constantes.ACCEPT,
					Constantes.APPLICATION_JSON);
			httpPutRequest.setHeader(Constantes.CONTENT_TYPE,
					Constantes.APPLICATION_JSON);

			StringEntity json = getJsonStringEntity(usuario, producto.getId());
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
		case Remover.Respuesta.EXITO:
			if(fueRemovido){
				carritoComprasFragment.remove(producto);
				AlertUtilidades.mostrarAlert(carritoComprasFragment.getActivity(), R.drawable.ic_launcher,
						R.string.app_name, R.string.producto_removido, R.string.ok);	
			}else{
				Toast.makeText(carritoComprasFragment.getActivity(), R.string.producto_no_encontrado_carrito, Toast.LENGTH_SHORT)
				.show();	
			}
			
			break;

		case Remover.Respuesta.FALLO:
			Toast.makeText(carritoComprasFragment.getActivity(), R.string.error_conexion, Toast.LENGTH_SHORT)
					.show();
			break;

		}

	}

	private class JSONResponseHandler implements ResponseHandler<Void> {

		@Override
		public Void handleResponse(HttpResponse response){
			respuesta = response.getStatusLine().getStatusCode();
			if (respuesta == Remover.Respuesta.EXITO) {
				try{
					String JsonResponse = new BasicResponseHandler().handleResponse(response);
					fueRemovido = Boolean.parseBoolean(JsonResponse);
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
		json.put(Remover.Solicitud.USUARIO, usuario);
		json.put(Remover.Solicitud.ID_PRODUCTO, idProducto);
		return new StringEntity(json.toString(), HTTP.UTF_8);
	}

	private static class Remover {

		public static final String URL_REMOVER = Constantes.URL_BASE
				+ "services/carrito/remover";

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
