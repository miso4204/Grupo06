package grupo6.marketplace.ecoturismo.fragments.usuario;

import grupo6.marketplace.ecoturismo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragmento que muestra el perfil del cliente
 * @author Alejo
 *
 */
public class PerfilFragment extends Fragment{

	private View view; 
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarElementosGraficos() {
	}

	private void cargarListeners() {
	}

}
