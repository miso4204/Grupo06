package grupo6.marketplace.ecoturismo.fragments.usuario;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.usuario.LoginAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.Usuario;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
/**
 * Fragmento que muestra la vista de login del marketplace de ecoturismo
 * @author Alejo
 *
 */
public class LoginFragment extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	private View view;
	private EditText editTextUsuario;
	private EditText editTextClave;
	private Button buttonLogin;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        cargarDatosLocales();
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		
	}

	private void cargarElementosGraficos() {
		editTextUsuario = (EditText) view.findViewById(R.id.Login_EditText_Usuario);
		editTextClave = (EditText) view.findViewById(R.id.Login_EditText_Clave);
		buttonLogin = (Button) view.findViewById(R.id.Login_Button_Iniciar_Sesion);
	}

	private void cargarListeners() {
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String usuario = editTextUsuario.getText().toString();
				String clave = editTextClave.getText().toString();
				if(!TextUtils.isEmpty(usuario) && !TextUtils.isEmpty(clave)){
					new LoginAsyncTask(LoginFragment.this).execute(usuario,clave);
				}
			}
		});
	}

	public void abrirActividadPrincipal(Usuario usuario) {
		ecoturismoApplication.setUsuario(usuario);
		Intent i = new Intent(getActivity(),PrincipalActivity.class);
		getActivity().startActivity(i);
		getActivity().finish();
	}

}
