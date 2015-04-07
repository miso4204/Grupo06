package grupo6.marketplace.ecoturismo.fragments.busquedas;

import java.util.ArrayList;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
/**
 * Fragmento que muestra la lista de productos del marketplace de ecoturismo 
 * @author Alejo
 *
 */
public class ProductosPorCiudadFragment extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	
	private View view;
	private Button buttonBuscar;
	private ListView listViewProductos;
	private ProductosAdapter productosAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_producto_ciudad, container, false);
        cargarDatosLocales();
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		productosAdapter = new ProductosAdapter(getActivity(),new ArrayList<Producto>(),ecoturismoApplication);
	}
	
	private void cargarElementosGraficos() {
		buttonBuscar = (Button) view.findViewById(R.id.Busqueda_Ciudad_Button);
		listViewProductos = (ListView) view.findViewById(R.id.Busqueda_Ciudad_ListView);
	}
	
	private void cargarListeners() {
		
		buttonBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listViewProductos.setAdapter(productosAdapter);
			}
		});
		
		listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Producto producto = productosAdapter.getItem(position);
				AlertUtilidades.mostrarAlertProducto(getActivity(),producto);
			}
		});
		
	}
}