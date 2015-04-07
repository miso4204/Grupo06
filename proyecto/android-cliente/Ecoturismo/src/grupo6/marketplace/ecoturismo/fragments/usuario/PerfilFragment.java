package grupo6.marketplace.ecoturismo.fragments.usuario;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Usuario;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Fragmento que muestra el perfil del cliente
 * @author Alejo
 *
 */
public class PerfilFragment extends Fragment{
	private EcoturismoApplication ecoturismoApplication;
	private Usuario usuario;
	private View view; 
	private EditText editTextNombre;
	private EditText editTextDireccion;
	private EditText editTextTelefono;
	private EditText editTextUsuario;
	private Button buttonCerrarSesion;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        cargarDatosLocales();
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		usuario = ecoturismoApplication.getUsuario();
	}

	private void cargarElementosGraficos() {
		editTextNombre = (EditText) view.findViewById(R.id.Perfil_EditText_Nombre);
		editTextNombre.setText(usuario.getNombre());
		
		editTextDireccion = (EditText) view.findViewById(R.id.Perfil_EditText_Direccion);
		editTextDireccion.setText(usuario.getDireccion());
		
		editTextTelefono = (EditText) view.findViewById(R.id.Perfil_EditText_Telefono);
		editTextTelefono.setText(usuario.getTelefono());
		
		editTextUsuario = (EditText) view.findViewById(R.id.Perfil_EditText_UserName);
		editTextUsuario.setText(usuario.getUsuario());
		
		buttonCerrarSesion = (Button) view.findViewById(R.id.Perfil_Button_Logout);
	}

	private void cargarListeners() {
		buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
	}

}
