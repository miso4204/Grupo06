package grupo6.marketplace.ecoturismo.asyntask.usuario;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;
import org.json.JSONTokener;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.usuario.LoginFragment;
import grupo6.marketplace.ecoturismo.modelo.Usuario;
import grupo6.marketplace.ecoturismo.modelo.enums.RolType;
import grupo6.marketplace.ecoturismo.util.Constantes;
import android.app.ProgressDialog;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.widget.Toast;

public class LoginAsyncTask extends AsyncTask<String, Void, Usuario> {
	
	protected static final String TAG = "LoginAsyncTask";
	private int respuesta;
	private ProgressDialog progressDialog;
	private String usuario;
	private String clave;
	private AndroidHttpClient androidHttpCliente = AndroidHttpClient.newInstance("");
	private LoginFragment loginFragment;
	
	public LoginAsyncTask(LoginFragment loginFragment) {
		this.loginFragment = (LoginFragment) loginFragment;
		progressDialog = new ProgressDialog(this.loginFragment.getActivity());
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		HttpConnectionParams.setConnectionTimeout(androidHttpCliente.getParams(), Constantes.TIME_OUT_CONEXION);
		HttpConnectionParams.setSoTimeout(androidHttpCliente.getParams(),Constantes.TIME_OUT_SOCKET);
		respuesta = Login.Respuesta.FALLO;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setMessage(loginFragment.getString(R.string.login_progress_inicio_sesion));
		progressDialog.show();
	}

	@Override
	protected Usuario doInBackground(String... params) {
		try{
			usuario = (String) params[0];
			clave = (String) params[1];
			
			HttpPost httpPostRequest = new HttpPost(Login.URL_LOGIN);
			httpPostRequest.setHeader(Constantes.ACCEPT, Constantes.APPLICATION_JSON);
			httpPostRequest.setHeader(Constantes.CONTENT_TYPE, Constantes.APPLICATION_JSON);
		    
			StringEntity jsonLogin = getJsonStringEntity(usuario,clave);
			httpPostRequest.setEntity(jsonLogin);

			JSONResponseHandler responseHandler = new JSONResponseHandler();

			return androidHttpCliente.execute(httpPostRequest, responseHandler);	
		}catch(Exception e){
			
		}
		return null;
		
	}
	
	protected void onPostExecute(Usuario usuario) {
		if (progressDialog != null) {
			if (progressDialog.isShowing())
				progressDialog.dismiss();
		}
		switch (respuesta) {
		case Login.Respuesta.EXITO:
			if(usuario != null){
				loginFragment.abrirActividadPrincipal(usuario);
			}else{
				Toast.makeText(loginFragment.getActivity(), R.string.login_error_credenciales, Toast.LENGTH_SHORT).show();
			}
			break;

		case Login.Respuesta.FALLO:
			Toast.makeText(loginFragment.getActivity(), R.string.error_conexion, Toast.LENGTH_SHORT).show();
			break;

		}

		
	}
	
	private class JSONResponseHandler implements ResponseHandler<Usuario> {

		@Override
		public Usuario handleResponse(HttpResponse response) {
			Usuario usuario = null;
			try {
				respuesta = response.getStatusLine().getStatusCode();
				if (respuesta == Login.Respuesta.EXITO) {
					String JSONResponse = new BasicResponseHandler().handleResponse(response);
					JSONObject responseObjecto = (JSONObject) new JSONTokener(JSONResponse).nextValue();
					usuario = new Usuario();
					usuario.setId(responseObjecto.getLong(Login.Respuesta.ID_USUARIO));
					usuario.setUsuario(responseObjecto.getString(Login.Respuesta.USUARIO));
					usuario.setPassword(responseObjecto.getString(Login.Respuesta.CLAVE));
					usuario.setNombre(responseObjecto.getString(Login.Respuesta.NOMBRE));
					usuario.setDireccion(responseObjecto.getString(Login.Respuesta.DIRECCION));
					usuario.setTelefono(responseObjecto.getString(Login.Respuesta.TELEFONO));
					usuario.setRol(Login.getRolType(responseObjecto.getString(Login.Respuesta.ROL)));
					usuario.setEmail(responseObjecto.getString(Login.Respuesta.EMAIL));
					usuario.setWebsite(responseObjecto.getString(Login.Respuesta.WEB_SITE));
					
				}

			} catch (Exception e) {
				usuario = null;
				e.printStackTrace();
			}
			
			cerrarConexion();
			
			return usuario;
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
	
	private StringEntity getJsonStringEntity(String usuario, String clave) throws Exception{
		JSONObject json = new JSONObject();
		json.put(Login.Solicitud.USUARIO, usuario);
		json.put(Login.Solicitud.CLAVE, clave);	
		return new StringEntity(json.toString(), HTTP.UTF_8);
	}
	
	private static class Login {

		public static final String URL_LOGIN = Constantes.URL_BASE + "services/usuario/login";

		public static class Solicitud {
			public static final String USUARIO = "usuario";
			public static final String CLAVE = "password";
		}

		public static class Respuesta {
			public static final String ID_USUARIO = "id";
			public static final String USUARIO = "usuario";
			public static final String CLAVE = "password";
			public static final String NOMBRE = "nombre";
			public static final String DIRECCION = "direccion";
			public static final String TELEFONO = "telefono";
			public static final String ROL = "rol";
			public static final String EMAIL = "email";
			public static final String WEB_SITE = "website";
			public static final int EXITO = 200;
			public static final int FALLO = 500;
			
		}
		
		public static class Rol {
			public static final String CLIENT = "CLIENT";
			public static final String ADMIN = "ADMIN";
			public static final String PROVIDER = "PROVIDER";
		}
		
		public static RolType getRolType(String rol){
			RolType rolType = RolType.CLIENT; 
			if(rol.equalsIgnoreCase(Rol.CLIENT)){
				rolType = RolType.CLIENT;
			}else if(rol.equalsIgnoreCase(Rol.ADMIN)){
				rolType = RolType.ADMIN;
			}else if(rol.equalsIgnoreCase(Rol.PROVIDER)){
				rolType = RolType.PROVIDER;
			}
			return rolType;
		}
	}
	
	
}
