package grupo6.marketplace.ecoturismo.fragments;

import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.ProductoTable;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/**
 * Fragmento que muestra la lista de productos del marketplace de ecoturismo 
 * @author Alejo
 *
 */
public class ProductosFragment extends ListFragment{

	private EcoturismoApplication ecoturismoApplication;
	private List<Producto> productos;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		productos = ProductoTable.getProductos(ecoturismoApplication.getEcoturismoSqlHelper());
		
		ProductosAdapter productosAdapter = new ProductosAdapter(getActivity(),productos,ecoturismoApplication.getEcoturismoSqlHelper());
		setListAdapter(productosAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Producto producto = productos.get(position);
		AlertUtilidades.mostrarAlert(getActivity(), R.drawable.ic_launcher, R.string.producto_descripcion_titulo, producto.getDescripcion(), R.string.ok);
		super.onListItemClick(l, v, position, id);
	}

}