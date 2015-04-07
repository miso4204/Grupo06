package grupo6.marketplace.ecoturismo.asyntask.pagos;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PagosActivity;
import grupo6.marketplace.ecoturismo.modelo.enums.TiposPago;
import grupo6.marketplace.ecoturismo.util.Constantes;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class PagarAsyncTask extends AsyncTask<Object, Void, Void> {
	
	protected static final String TAG = "PagarAsyncTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private String userName;
	private TiposPago tipoPago;
	private boolean pagoFueExitoso;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient.newInstance("");
	private PagosActivity pagosActivity;
	
	public PagarAsyncTask(PagosActivity activity) {
		this.pagosActivity = activity;
		progressDialog = new ProgressDialog(this.pagosActivity);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),Constantes.TIME_OUT_SOCKET);
		respuesta = Pagos.Respuesta.FALLO;
		pagoFueExitoso = false;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(pagosActivity.getString(R.string.pagando));
		progressDialog.show();
	}

	@Override
	protected Void doInBackground(Object... params) {
		try{
			userName =(String) params[0];
			tipoPago = (TiposPago) params[1];
			
			String tipoPagoString = getTipoPago(tipoPago);
			String urlFinal = Pagos.URL_PAGO
					          .replace(Pagos.PAYMENT_TYPE, tipoPagoString)
					          .replace(Pagos.USER_NAME, userName);
			
			HttpPost httpPostRequest = new HttpPost(urlFinal);
			httpPostRequest.setHeader(Constantes.ACCEPT, Constantes.APPLICATION_JSON);
			httpPostRequest.setHeader(Constantes.CONTENT_TYPE, Constantes.APPLICATION_JSON);
		    
			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpPostRequest, responseHandler);	
		}catch(Exception e){
			
		}
		return null;
		
	}
	
	private String getTipoPago(TiposPago tipoPago) {
		String res = Pagos.UrlTipoPago.EFECTIVO;
		if(tipoPago == TiposPago.CASH_ON_DELIVERY){
			res = Pagos.UrlTipoPago.EFECTIVO;
		}else if(tipoPago == TiposPago.CREDIT_CARD){
			res = Pagos.UrlTipoPago.TARJETA_CREDITO;
		}else if(tipoPago == TiposPago.PSE){
			res = Pagos.UrlTipoPago.PSE;
		}
		return res;
	}

	protected void onPostExecute(Void user) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case Pagos.Respuesta.EXITO:
			if(pagoFueExitoso){
				pagosActivity.pagoExitoso();
			}else{
				Toast.makeText(pagosActivity, R.string.pagos_carrito_vacio, Toast.LENGTH_SHORT).show();	
			}
				
			break;

		case Pagos.Respuesta.FALLO:
			Toast.makeText(pagosActivity, R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

		
	}
	
	private class JSONResponseHandler implements ResponseHandler<Void> {

		@Override
		public Void handleResponse(HttpResponse response) {
			try {
				respuesta = response.getStatusLine().getStatusCode();
				if (respuesta == Pagos.Respuesta.EXITO) {
					String JSONResponse = new BasicResponseHandler().handleResponse(response);
					JSONObject responseObjecto = (JSONObject) new JSONTokener(JSONResponse).nextValue();
					
					long idFactura = responseObjecto.getLong(Pagos.Respuesta.ID_FACTURA);
					if(idFactura != 0){
						pagoFueExitoso = true;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
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
	private static class Pagos {

		public static final String USER_NAME = "{userName}";
		public static final String PAYMENT_TYPE = "{paymentType}";
		
		public static class UrlTipoPago{
			public static final String EFECTIVO = "pay_cash";
			public static final String TARJETA_CREDITO = "pay_credit";
			public static final String PSE = "pay_pse";
		}
		
		
		public static final String URL_PAGO = Constantes.URL_BASE + "services/payment/"+PAYMENT_TYPE+"/"+USER_NAME;

		public static class Respuesta {
			public static final String ID_FACTURA = "id";
			public static final int EXITO = 200;
			public static final int FALLO = 500;
		}
	}
	
	
}
