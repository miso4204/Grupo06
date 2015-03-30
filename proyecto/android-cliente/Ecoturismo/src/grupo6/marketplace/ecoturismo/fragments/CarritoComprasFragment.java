package grupo6.marketplace.ecoturismo.fragments;

import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.modelo.EcoturismoLocalData;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CarritoComprasFragment extends Fragment{

	private static EcoturismoLocalData ecoturismoLocalData;
	
	private static View view;
	private static ListView listViewCarritoCompras;
	private static TextView textViewTotalCarrito;
	private static ProductosAdapter productosAdapter;
	private static double totalCarrito;
	private static final boolean REMOVER_CARRITO = true;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_carrito_compras, container, false);
        ecoturismoLocalData = new EcoturismoLocalData(getActivity().getApplicationContext());
        cargarElementosGraficos();
        calcularTotalCarrito(getActivity());
        cargarListeners();
        return view;
    }

	private static void calcularTotalCarrito(Context context) {
		totalCarrito = 0;
		List<Producto> productos = productosAdapter.getProductos();
		if(productos != null && productos.size() > 0){
			for(Producto p: productos){
				totalCarrito = totalCarrito + p.getPrecio();
			}	
		}
		
		textViewTotalCarrito = (TextView) view.findViewById(R.id.Carrito_Compras_TextView_Total);
		textViewTotalCarrito.setText(context.getString(R.string.carrrito_compras_total) 
								    +" "
								    +CurrencyUtilidades.formatoDinero(totalCarrito
								    								 ,CurrencyUtilidades.LENGUAJE_ESPANOL
								    								 ,CurrencyUtilidades.CODIGO_COLOMBIA
								    								 )	
									);
	}

	private void cargarElementosGraficos() {
		productosAdapter = new ProductosAdapter(getActivity(), ecoturismoLocalData.getCarritoCompras(),REMOVER_CARRITO);
		listViewCarritoCompras = (ListView) view.findViewById(R.id.Carrito_Compras_ListView);
		listViewCarritoCompras.setAdapter(productosAdapter);
	}

	private void cargarListeners() {
	}

	public static void notificarAdaptador(Context context,Producto producto,boolean remover){
		if(productosAdapter != null){
			if(remover){
				productosAdapter.remove(producto);
			}else{
				productosAdapter.add(producto);
			}
			productosAdapter.notifyDataSetChanged();
			calcularTotalCarrito(context);
		}
	}
}
