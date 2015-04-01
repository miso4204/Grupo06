package grupo6.marketplace.ecoturismo.fragments;

import java.util.List;

import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import grupo6.marketplace.ecoturismo.activities.ProductosActivity;
import grupo6.marketplace.ecoturismo.adapters.CategoriasAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Categoria;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.CategoriaTable;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/**
 * Fragmento que muestra las categorias del marketplace de ecoturismo
 * @author Alejo
 *
 */

public class CategoriasFragment extends ListFragment{

	private EcoturismoApplication ecoturismoApplication;
	private List<Categoria> categorias;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		categorias = CategoriaTable.getCategorias(ecoturismoApplication.getEcoturismoSqlHelper());
		
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
		getActivity().startActivityForResult(i, PrincipalActivity.REQUEST_CODE_PRODUCTOS_ACTIVITY);
	}
}
