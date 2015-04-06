package grupo6.marketplace.ecoturismo.fragments.busquedas;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.ProductoTable;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ProductosPorPrecioFragment extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	private List<Producto> productos;
	
	private View view;
	private Button buttonBuscar;
	private ListView listViewProductos; 
	private ProductosAdapter productosAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_producto_precio, container, false);
        cargarDatosLocales();
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
		productos = ProductoTable.getProductos(ecoturismoApplication.getEcoturismoSqlHelper());
		productosAdapter = new ProductosAdapter(getActivity(),productos,ecoturismoApplication.getEcoturismoSqlHelper());
	}
	
	private void cargarElementosGraficos() {
		buttonBuscar = (Button) view.findViewById(R.id.Busqueda_Precio_Button);
		listViewProductos = (ListView) view.findViewById(R.id.Busqueda_Precio_ListView);
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