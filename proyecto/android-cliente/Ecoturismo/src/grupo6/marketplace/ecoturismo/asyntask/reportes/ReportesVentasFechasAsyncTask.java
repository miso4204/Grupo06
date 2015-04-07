package grupo6.marketplace.ecoturismo.asyntask.reportes;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.reportes.ReporteVentasEntreFechasFragment;
import grupo6.marketplace.ecoturismo.modelo.ReporteVentasFechasDTO;
import grupo6.marketplace.ecoturismo.util.Constantes;


import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class ReportesVentasFechasAsyncTask extends
		AsyncTask<String, Void, ReporteVentasFechasDTO> {

	protected static final String TAG = "ReportesVentasFechasAsyncTask";
	private int respuesta;
	private String fechaInicial;
	private String fechaFinal;
	private ProgressDialog progressDialog;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient
			.newInstance("");
	private ReporteVentasEntreFechasFragment reporteFragment;

	public ReportesVentasFechasAsyncTask(
			ReporteVentasEntreFechasFragment reporteFragment) {
		this.reporteFragment = reporteFragment;
		progressDialog = new ProgressDialog(this.reporteFragment.getActivity());
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),Constantes.TIME_OUT_SOCKET);
		respuesta = Reporte.Respuesta.FALLO;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(reporteFragment.getString(R.string.cargando_progress));
		progressDialog.show();
	}

	@Override
	protected ReporteVentasFechasDTO doInBackground(String... params) {
		try {
			fechaInicial = params[0];
			fechaFinal = params[1];

			String urlFinal = Reporte.URL_GET_REPORTE
					         .replace(Reporte.FECHA_INICIAL,fechaInicial)
					         .replace(Reporte.FECHA_FINAL,fechaFinal);

			HttpGet httpGetRequest = new HttpGet(urlFinal);
			httpGetRequest.setHeader(Constantes.ACCEPT,
					Constantes.APPLICATION_JSON);
			httpGetRequest.setHeader(Constantes.CONTENT_TYPE,
					Constantes.APPLICATION_JSON);

			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpGetRequest, responseHandler);
		} catch (Exception e) {

		}
		return null;

	}
	@Override
	protected void onPostExecute(ReporteVentasFechasDTO reporte) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case Reporte.Respuesta.EXITO:
			if (reporte != null) {
			  reporteFragment.mostrarReporte(reporte);
			}
			break;

		case Reporte.Respuesta.FALLO:
			Toast.makeText(reporteFragment.getActivity(),R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

	}

	private class JSONResponseHandler implements
			ResponseHandler<ReporteVentasFechasDTO> {

		@Override
		public ReporteVentasFechasDTO handleResponse(HttpResponse response) {
			ReporteVentasFechasDTO reporte = null;
			try {
				respuesta = response.getStatusLine().getStatusCode();
				if (respuesta == Reporte.Respuesta.EXITO) {
					String JSONResponse = new BasicResponseHandler()
							.handleResponse(response);
					JSONObject responseObjecto = (JSONObject) new JSONTokener(
							JSONResponse).nextValue();

					String fechaInicial = responseObjecto.getString(Reporte.Respuesta.FECHA_INICIAL);
					String fechaFinal = responseObjecto.getString(Reporte.Respuesta.FECHA_FINAL);
					long totalVentas = responseObjecto.getLong(Reporte.Respuesta.TOTAL_VENTAS);
					double totalDineroEnVentas = responseObjecto.getDouble(Reporte.Respuesta.TOTAL_DINERO_VENTAS);
					reporte = new ReporteVentasFechasDTO(fechaInicial, fechaFinal, totalVentas, totalDineroEnVentas);
				}

			} catch (Exception e) {
				reporte = null;
				e.printStackTrace();
			}

			cerrarConexion();

			return reporte;
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

	private static class Reporte {

		public static final String FECHA_INICIAL = "{fechaInicial}";
		public static final String FECHA_FINAL = "{fechaFinal}";

		public static final String URL_GET_REPORTE = Constantes.URL_BASE
				+ "services/reportes/ventas/" + FECHA_INICIAL + "/" + FECHA_FINAL;

		public static class Respuesta {
			public static final String FECHA_INICIAL = "fechaInicial";
			public static final String FECHA_FINAL = "fechaFinal";
			public static final String TOTAL_VENTAS = "totalVentas";
			public static final String TOTAL_DINERO_VENTAS = "totalDineroEnVentas";
			public static final int EXITO = 200;
			public static final int FALLO = 500;
		}

	}
}
