package grupo6.marketplace.ecoturismo.fragments.busquedas;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.busqueda.BusquedaPorPrecioAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ProductosPorPrecioFragment extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	
	private View view;
	private EditText editTextFechaInicial;
	private EditText editTextFechaFinal;
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
		productosAdapter = new ProductosAdapter(getActivity(),new ArrayList<Producto>(),ecoturismoApplication);
	}
	
	private void cargarElementosGraficos() {
		editTextFechaInicial = (EditText) view.findViewById(R.id.Busqueda_Precio_EditText_PrecioInicio);
		editTextFechaFinal = (EditText) view.findViewById(R.id.Busqueda_Precio_EditText_PrecioFinal);
		buttonBuscar = (Button) view.findViewById(R.id.Busqueda_Precio_Button);
		listViewProductos = (ListView) view.findViewById(R.id.Busqueda_Precio_ListView);
	}
	
	private void cargarListeners() {
		
		buttonBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String inicio = editTextFechaInicial.getText().toString();
				String fin = editTextFechaFinal.getText().toString();
				
				if(!TextUtils.isEmpty(inicio) && !TextUtils.isEmpty(fin)){
					try{
						double precioInicial = Double.parseDouble(editTextFechaInicial.getText().toString());
						double precioFinal = Double.parseDouble(editTextFechaFinal.getText().toString());
						new BusquedaPorPrecioAsyncTask(ProductosPorPrecioFragment.this).execute(precioInicial,precioFinal);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
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

	public void mostrarProducto(List<Producto> productos) {
		productosAdapter.clear();
		productosAdapter.addAll(productos);
		listViewProductos.setAdapter(productosAdapter);
	}

	public void limpiarLista() {
		productosAdapter.clear();
		listViewProductos.setAdapter(productosAdapter);
	}
}