package grupo6.marketplace.ecoturismo.fragments;

import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.CarritoComprasAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.CarritoComprasTable;
import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * Fragmento que muestra el carrito de compras del marketplace de ecoturismo
 * @author Alejo
 *
 */
@SuppressLint("InflateParams")
public class CarritoComprasFragment extends Fragment{

	private EcoturismoApplication ecoturismoApplication;
	private List<Producto> carritoCompras;
	private double totalCarrito;
	
	private View view;
	private ListView listViewCarritoCompras;
	private TextView textViewTotalCarrito;
	private CarritoComprasAdapter carritoComprasAdapter;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_carrito_compras, container, false);
        cargarDatosLocales();
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }
	
	@Override
	public void onResume() {
		super.onResume();
		cargarCarritoCompras();
	}

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
	}

	private void cargarElementosGraficos() {
		textViewTotalCarrito = (TextView) view.findViewById(R.id.Carrito_Compras_TextView_Total);
		listViewCarritoCompras = (ListView) view.findViewById(R.id.Carrito_Compras_ListView);
	}

	private void cargarCarritoCompras() {
		carritoCompras = CarritoComprasTable.getCarritoCompras(ecoturismoApplication.getEcoturismoSqlHelper());
		carritoComprasAdapter = new CarritoComprasAdapter(getActivity(), carritoCompras,ecoturismoApplication.getEcoturismoSqlHelper(),CarritoComprasFragment.this);
		listViewCarritoCompras.setAdapter(carritoComprasAdapter);
		calcularTotalCarrito();
	}
	
	public void calcularTotalCarrito() {
		totalCarrito = 0;
		for(Producto p: carritoCompras){
			totalCarrito = totalCarrito + p.getPrecio(); 
		}
		textViewTotalCarrito.setText(getString(R.string.carrrito_compras_total) 
								    +" "
								    +CurrencyUtilidades.formatoDinero(totalCarrito
								    								 ,CurrencyUtilidades.LENGUAJE_ESPANOL
								    								 ,CurrencyUtilidades.CODIGO_COLOMBIA
								    								 )	
									);
	}
	
	

	

	private void cargarListeners() {
		listViewCarritoCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Producto producto = carritoCompras.get(position);
				LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View vista = inflater.inflate(R.layout.dialog_descripcion_producto,null);
				TextView tituloTextView = (TextView) vista.findViewById(R.id.Detalle_Producto_TextView);
				tituloTextView.setText(producto.getCiudad() + "-"+ producto.getNombre());

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setView(vista);

				final Dialog dialog = builder.create();
				dialog.show();
			}
		});
	}

}
