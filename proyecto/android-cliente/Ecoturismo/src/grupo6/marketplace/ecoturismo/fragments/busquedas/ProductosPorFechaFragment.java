package grupo6.marketplace.ecoturismo.fragments.busquedas;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.busqueda.BusquedaPorFechasAsyncTask;
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

public class ProductosPorFechaFragment extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	
	private View view;
	private Button buttonBuscar;
	private ListView listViewProductos; 
	private EditText editTextFechaInicial;
	private EditText editTextFechaFinal;
	
	private ProductosAdapter productosAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_producto_fecha, container, false);
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
		editTextFechaInicial = (EditText) view.findViewById(R.id.Busqueda_Fecha_EditText_FechaInicio);
		editTextFechaFinal = (EditText) view.findViewById(R.id.Busqueda_Fecha_EditText_FechaFinal);
		buttonBuscar = (Button) view.findViewById(R.id.Busqueda_Fecha_Button);
		listViewProductos = (ListView) view.findViewById(R.id.Busqueda_Fecha_ListView);
	}
	
	private void cargarListeners() {
		
		buttonBuscar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String inicio = editTextFechaInicial.getText().toString();
				String fin = editTextFechaFinal.getText().toString();
				
				if(!TextUtils.isEmpty(inicio) && !TextUtils.isEmpty(fin)){
					new BusquedaPorFechasAsyncTask(ProductosPorFechaFragment.this).execute(inicio,fin);
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
