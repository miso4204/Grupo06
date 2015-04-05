package grupo6.marketplace.ecoturismo.fragments;

import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import grupo6.marketplace.ecoturismo.activities.ProductosActivity;
import grupo6.marketplace.ecoturismo.adapters.BusquedasAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/**
 * Fragmento que muestra las busquedas del marketplace de ecoturismo
 * @author Alejo
 *
 */

public class BusquedasFragment extends ListFragment{

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		BusquedasAdapter busquedasAdapter = new BusquedasAdapter(getActivity());
		setListAdapter(busquedasAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		abrirProductosActivity();
		super.onListItemClick(l, v, position, id);
	}

	private void abrirProductosActivity() {
		Intent i = new Intent(getActivity(),ProductosActivity.class);
		getActivity().startActivityForResult(i, PrincipalActivity.REQUEST_CODE_PRODUCTOS_ACTIVITY);
	}
}
