package grupo6.marketplace.ecoturismo.asyntask.usuario;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.usuario.RegistroFragment;
import grupo6.marketplace.ecoturismo.modelo.Usuario;
import grupo6.marketplace.ecoturismo.modelo.enums.RolType;
import grupo6.marketplace.ecoturismo.util.Constantes;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class RegistroAsyncTask extends AsyncTask<Usuario, Void, Long> {
	
	protected static final String TAG = "RegistroAsyncTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private Usuario usuario;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient.newInstance("");
	private RegistroFragment registroFragment;
	
	public RegistroAsyncTask(RegistroFragment registroFragment) {
		this.registroFragment = registroFragment;
		progressDialog = new ProgressDialog(this.registroFragment.getActivity());
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),Constantes.TIME_OUT_SOCKET);
		respuesta = Registro.Respuesta.FALLO;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(registroFragment.getString(R.string.login_progress_inicio_sesion));
		progressDialog.show();
	}

	@Override
	protected Long doInBackground(Usuario... params) {
		try{
			usuario = params[0];
			
			HttpPost httpPostRequest = new HttpPost(Registro.URL_REGISTRO);
			httpPostRequest.setHeader(Constantes.ACCEPT, Constantes.APPLICATION_JSON);
			httpPostRequest.setHeader(Constantes.CONTENT_TYPE, Constantes.APPLICATION_JSON);
		    
			StringEntity jsonRegistro = getJsonStringEntity(usuario);
			httpPostRequest.setEntity(jsonRegistro);

			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpPostRequest, responseHandler);	
		}catch(Exception e){
			
		}
		return null;
		
	}
	
	protected void onPostExecute(Long idUsuario) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case Registro.Respuesta.EXITO:
			if(idUsuario != null){
				usuario.setId(idUsuario);
				registroFragment.abrirActividadPrincipal(usuario);
			}else{
				Toast.makeText(registroFragment.getActivity(), R.string.registro_error, Toast.LENGTH_SHORT).show();
			}
			break;

		case Registro.Respuesta.FALLO:
			Toast.makeText(registroFragment.getActivity(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

		
	}
	
	private class JSONResponseHandler implements ResponseHandler<Long> {

		@Override
		public Long handleResponse(HttpResponse response) {
			Long idUsuario = null;
			try {
				respuesta = response.getStatusLine().getStatusCode();
				if (respuesta == Registro.Respuesta.EXITO) {
					String JsonResponse = new BasicResponseHandler().handleResponse(response);
					idUsuario = Long.parseLong(JsonResponse);
				}

			} catch (Exception e) {
				idUsuario = null;
				e.printStackTrace();
			}
			
			cerrarConexion();
			
			return idUsuario;
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
	
	private StringEntity getJsonStringEntity(Usuario usuario) throws Exception{
		JSONObject json = new JSONObject();
		json.put(Registro.Solicitud.USUARIO, usuario.getUsuario());
		json.put(Registro.Solicitud.CLAVE, usuario.getPassword());
		json.put(Registro.Solicitud.NOMBRE, usuario.getNombre());
		json.put(Registro.Solicitud.DIRECCION, usuario.getDireccion());
		json.put(Registro.Solicitud.TELEFONO, usuario.getTelefono());
		json.put(Registro.Solicitud.ROL, Registro.getRolType(usuario.getRol()));
		return new StringEntity(json.toString(), HTTP.UTF_8);
	}
	
	private static class Registro {

		public static final String URL_REGISTRO = Constantes.URL_BASE + "services/usuario/crear";

		public static class Solicitud {
			public static final String USUARIO = "usuario";
			public static final String CLAVE = "password";
			public static final String NOMBRE = "nombre";
			public static final String DIRECCION = "direccion";
			public static final String TELEFONO = "telefono";
			public static final String ROL = "rol";
		}

		public static class Respuesta {
			public static final int EXITO = 200;
			public static final int FALLO = 500;
		}
		
		public static class Rol {
			public static final String CLIENT = "CLIENT";
			public static final String ADMIN = "ADMIN";
			public static final String PROVIDER = "PROVIDER";
		}
		
		public static String getRolType(RolType rol){
			String rolString = Rol.CLIENT; 
			if(rol == RolType.CLIENT){
				rolString = Rol.CLIENT;
			}else if(rol == RolType.ADMIN){
				rolString = Rol.ADMIN;
			}else if(rol == RolType.PROVIDER){
				rolString = Rol.PROVIDER;
			}
			return rolString;
		}
	}
	
	
}
