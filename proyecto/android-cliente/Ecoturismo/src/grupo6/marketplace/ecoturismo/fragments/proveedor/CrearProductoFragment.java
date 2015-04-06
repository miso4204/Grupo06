package grupo6.marketplace.ecoturismo.fragments.proveedor;

import grupo6.marketplace.ecoturismo.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CrearProductoFragment extends Fragment{

	private View view; 
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_producto, container, false);
        return view;
    }
}