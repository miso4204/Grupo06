package grupo6.marketplace.ecoturismo.fragments;

import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.CarritoComprasAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.CarritoComprasTable;
import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
/**
 * Fragmento que muestra el carrito de compras del marketplace de ecoturismo
 * @author Alejo
 *
 */
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
        ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
        carritoCompras = CarritoComprasTable.getCarritoCompras(ecoturismoApplication.getEcoturismoSqlHelper());
		
        cargarElementosGraficos();
        calcularTotalCarrito();
        cargarListeners();
        return view;
    }

	@Override
	public void onResume() {
		super.onResume();
		carritoComprasAdapter.notifyDataSetChanged();
	}
	
	private void calcularTotalCarrito() {
		
		textViewTotalCarrito = (TextView) view.findViewById(R.id.Carrito_Compras_TextView_Total);
		textViewTotalCarrito.setText(getString(R.string.carrrito_compras_total) 
								    +" "
								    +CurrencyUtilidades.formatoDinero(totalCarrito
								    								 ,CurrencyUtilidades.LENGUAJE_ESPANOL
								    								 ,CurrencyUtilidades.CODIGO_COLOMBIA
								    								 )	
									);
	}

	private void cargarElementosGraficos() {
		carritoComprasAdapter = new CarritoComprasAdapter(getActivity(), carritoCompras,ecoturismoApplication.getEcoturismoSqlHelper());
		listViewCarritoCompras = (ListView) view.findViewById(R.id.Carrito_Compras_ListView);
		listViewCarritoCompras.setAdapter(carritoComprasAdapter);
	}

	private void cargarListeners() {
	}

}
