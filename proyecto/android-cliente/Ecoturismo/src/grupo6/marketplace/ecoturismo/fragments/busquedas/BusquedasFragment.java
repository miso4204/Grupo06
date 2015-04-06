package grupo6.marketplace.ecoturismo.fragments.busquedas;

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
 * 
 * @author Alejo
 *
 */

public class BusquedasFragment extends ListFragment {

	public static final String TIPO_BUSQUEDA = "BusquedasFragment.TIPO_BUSQUEDA";

	public static final int UBICACION = 0;
	public static final int PRECIO = 1;
	public static final int FECHA = 2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		BusquedasAdapter busquedasAdapter = new BusquedasAdapter(getActivity());
		setListAdapter(busquedasAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case UBICACION:
			abrirProductosActivity(UBICACION);
			break;
		case PRECIO:
			abrirProductosActivity(PRECIO);
			break;
		case FECHA:
			abrirProductosActivity(FECHA);
			break;
		}

		super.onListItemClick(l, v, position, id);
	}

	private void abrirProductosActivity(int tipoBusqueda) {
		Intent i = new Intent(getActivity(),ProductosActivity.class);
		i.putExtra(TIPO_BUSQUEDA, tipoBusqueda);
		getActivity().startActivityForResult(i, PrincipalActivity.REQUEST_CODE_PRODUCTOS_ACTIVITY);
	}
}
