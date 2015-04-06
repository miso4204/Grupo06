package grupo6.marketplace.ecoturismo.fragments.usuario;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * Fragmento que muestra la vista de login del marketplace de ecoturismo
 * @author Alejo
 *
 */
public class LoginFragment extends Fragment{

	private View view; 
	private Button buttonLogin;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarElementosGraficos() {
		buttonLogin = (Button) view.findViewById(R.id.Login_Button_Iniciar_Sesion);
	}

	private void cargarListeners() {
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				abrirActividadPrincipal();
			}
		});
	}

	protected void abrirActividadPrincipal() {
		Intent i = new Intent(getActivity(),PrincipalActivity.class);
		getActivity().startActivity(i);
		getActivity().finish();
	}

}
