package grupo6.marketplace.ecoturismo.fragments;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RegistroFragment  extends Fragment{

	private View view; 
	private Button buttonRegistrar;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registro_fragment, container, false);
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarElementosGraficos() {
		buttonRegistrar = (Button) view.findViewById(R.id.Registro_Button_Registrar);
	}

	private void cargarListeners() {
		buttonRegistrar.setOnClickListener(new View.OnClickListener() {
			
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
