package grupo6.marketplace.ecoturismo.fragments.usuario;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.usuario.RegistroAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.Usuario;
import grupo6.marketplace.ecoturismo.modelo.enums.RolType;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
/**
 * Fragmento que muestra la vista de registro del marketplace de ecoturismo
 * @author Alejo
 *
 */
public class RegistroFragment  extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	private View view; 
	private EditText editTextNombre;
	private EditText editTextDireccion;
	private EditText editTextTelefono;
	private EditText editTextUsuario;
	private EditText editTextClave;
	private  Spinner spinnerRol;
	private Button buttonRegistrar;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_registro, container, false);
        cargarDatosLocales();
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		
	}
	
	private void cargarElementosGraficos() {
		editTextNombre = (EditText) view.findViewById(R.id.Registro_EditText_Nombre);
		editTextDireccion= (EditText) view.findViewById(R.id.Registro_EditText_Direccion);
		editTextTelefono= (EditText) view.findViewById(R.id.Registro_EditText_Telefono);
		editTextUsuario= (EditText) view.findViewById(R.id.Registro_EditText_UserName);
		editTextClave= (EditText) view.findViewById(R.id.Registro_EditText_Clave);
		spinnerRol = (Spinner) view.findViewById(R.id.Registro_Rol_Spinner);
		spinnerRol.setPrompt(getString(R.string.registro_selecciona_un_rol));
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.roles, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerRol.setAdapter(adapter);
		buttonRegistrar = (Button) view.findViewById(R.id.Registro_Button_Registrar);
	}

	private void cargarListeners() {
		buttonRegistrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nombre = editTextNombre.getText().toString();
				String direccion = editTextDireccion.getText().toString();
				String telefono = editTextTelefono.getText().toString();
				String usuario = editTextUsuario.getText().toString();
				String clave = editTextClave.getText().toString();
				if(!TextUtils.isEmpty(nombre) &&
				   !TextUtils.isEmpty(direccion) &&
				   !TextUtils.isEmpty(telefono) &&
				   !TextUtils.isEmpty(usuario) &&
				   !TextUtils.isEmpty(clave) &&
				   spinnerRol.getSelectedItem() != null &&
				   !TextUtils.isEmpty(spinnerRol.getSelectedItem().toString())){
					Usuario user = new Usuario();
					user.setNombre(nombre);
					user.setDireccion(direccion);
					user.setTelefono(telefono);
					user.setUsuario(usuario);
					user.setPassword(clave);
					user.setRol(getRolSegunSpinner(spinnerRol.getSelectedItem().toString()));
					new RegistroAsyncTask(RegistroFragment.this).execute(user);
				}
			}

			private RolType getRolSegunSpinner(String rol) {
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
		});
	}

	public void abrirActividadPrincipal(Usuario usuario) {
		ecoturismoApplication.setUsuario(usuario);
		Intent i = new Intent(getActivity(),PrincipalActivity.class);
		getActivity().startActivity(i);
		getActivity().finish();
	}
	
	public static class Rol {
		public static final String CLIENT = "Cliente";
		public static final String PROVIDER = "Proveedor";
		public static final String ADMIN = "Administrador";
	}

}
