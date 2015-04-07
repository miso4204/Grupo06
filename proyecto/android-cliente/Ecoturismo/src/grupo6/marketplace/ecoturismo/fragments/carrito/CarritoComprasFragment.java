package grupo6.marketplace.ecoturismo.fragments.carrito;

import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PagosActivity;
import grupo6.marketplace.ecoturismo.activities.PrincipalActivity;
import grupo6.marketplace.ecoturismo.adapters.CarritoComprasAdapter;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.carrito.CargarCarritoComprasAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import grupo6.marketplace.ecoturismo.util.CurrencyUtilidades;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Fragmento que muestra el carrito de compras del marketplace de ecoturismo
 * 
 * @author Alejo
 *
 */
@SuppressLint("InflateParams")
public class CarritoComprasFragment extends Fragment {

	public static final String TIPO_PAGO = "CarritoComprasFragment.TIPO_PAGO";
	public static final int EFECTIVO = 0;
	public static final int PSE = 1;
	public static final int TARJETA_CREDITO = 2;

	private EcoturismoApplication ecoturismoApplication;
	private double totalCarrito;

	private View view;
	private ListView listViewCarritoCompras;
	private TextView textViewTotalCarrito;
	private CarritoComprasAdapter carritoComprasAdapter;
	private Button buttonComprar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_carrito_compras, container,
				false);
		cargarDatosLocales();
		cargarElementosGraficos();
		cargarListeners();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		getCarritoCompras();
	}

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity()
				.getApplication();
	}

	private void cargarElementosGraficos() {
		textViewTotalCarrito = (TextView) view
				.findViewById(R.id.Carrito_Compras_TextView_Total);
		listViewCarritoCompras = (ListView) view
				.findViewById(R.id.Carrito_Compras_ListView);
		buttonComprar = (Button) view
				.findViewById(R.id.Carrito_Compras_Button_Comprar);
	}

	private void getCarritoCompras() {
		String userName = ecoturismoApplication.getUsuario().getUsuario();
		new CargarCarritoComprasAsyncTask(CarritoComprasFragment.this).execute(userName);
	}
	
	public void cargarCarritoCompras(List<Producto> productos) {
		carritoComprasAdapter = new CarritoComprasAdapter(getActivity(),
				                productos, 
				                CarritoComprasFragment.this,
				                ecoturismoApplication);
		listViewCarritoCompras.setAdapter(carritoComprasAdapter);
		calcularTotalCarrito();
	}

	public void calcularTotalCarrito() {
		totalCarrito = 0;
		for(int i = 0;i<carritoComprasAdapter.getCount();i++){
			Producto p = carritoComprasAdapter.getItem(i);
			totalCarrito = totalCarrito + p.getPrecio();
		}
		textViewTotalCarrito.setText(getString(R.string.carrrito_compras_total)
				+ " "
				+ CurrencyUtilidades.formatoDinero(totalCarrito,
						CurrencyUtilidades.LENGUAJE_ESPANOL,
						CurrencyUtilidades.CODIGO_COLOMBIA));
	}

	private void cargarListeners() {
		listViewCarritoCompras
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Producto producto = carritoComprasAdapter.getItem(position);
						AlertUtilidades.mostrarAlertProducto(getActivity(),producto);
					}
				});

		buttonComprar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mostrarTiposDePago();
			}
		});
	}

	protected void mostrarTiposDePago() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.app_name).setItems(R.array.pagos,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						mostrarPagosActivity(which);
					}
				});
		builder.create().show();

	}

	protected void mostrarPagosActivity(int pago) {
		switch (pago) {
		case EFECTIVO:
			abrirPagosActivity(pago);
			break;
		case PSE:
			abrirPagosActivity(pago);
			break;
		case TARJETA_CREDITO:
			abrirPagosActivity(pago);
			break;
		}

	}

	private void abrirPagosActivity(int pago) {
		Intent i = new Intent(getActivity(), PagosActivity.class);
		i.putExtra(TIPO_PAGO, pago);
		getActivity().startActivityForResult(i, PrincipalActivity.REQUEST_CODE_PAGOS_ACTIVITY);

	}

	public void remove(Producto producto) {
		carritoComprasAdapter.remove(producto);
		carritoComprasAdapter.notifyDataSetChanged();
		calcularTotalCarrito();
	}
}
