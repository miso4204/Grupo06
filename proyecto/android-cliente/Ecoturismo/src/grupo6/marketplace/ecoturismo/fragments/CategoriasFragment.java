package grupo6.marketplace.ecoturismo.fragments;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.ProductosActivity;
import grupo6.marketplace.ecoturismo.adapters.CategoriasAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CategoriasFragment extends ListFragment{

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String [] categorias = getResources().getStringArray(R.array.categorias);
		CategoriasAdapter categoriasAdapter = new CategoriasAdapter(getActivity(),categorias);
		setListAdapter(categoriasAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		abrirProductosActivity();
		super.onListItemClick(l, v, position, id);
	}

	private void abrirProductosActivity() {
		Intent i = new Intent(getActivity(),ProductosActivity.class);
		getActivity().startActivity(i);
	}
}
