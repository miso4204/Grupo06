package grupo6.marketplace.ecoturismo.adapters;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.carrito.RemoverDelCarritoAsyncTask;
import grupo6.marketplace.ecoturismo.fragments.carrito.CarritoComprasFragment;
import grupo6.marketplace.ecoturismo.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class CarritoComprasAdapter extends ArrayAdapter<Producto>{
	
	private List<Producto> productos;
 	private LayoutInflater layoutInflater;
 	private CarritoComprasFragment carritoComprasFragment;
 	private EcoturismoApplication ecoturismoApplication;
 	
	public CarritoComprasAdapter(FragmentActivity context,List<Producto> productos,CarritoComprasFragment carritoComprasFragment,EcoturismoApplication ecoturismoApplication) {
		super(context, 0, productos);
		if(productos != null){
			this.productos = productos;
		}else{
			this.productos = new ArrayList<Producto>();
		}
		this.carritoComprasFragment = carritoComprasFragment;
		this.ecoturismoApplication = ecoturismoApplication;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		return productos.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vista = convertView;
		if (vista == null) {
			vista = layoutInflater.inflate(R.layout.fragment_productos_item, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.textViewProuctoNombre = (TextView) vista.findViewById(R.id.Producto_TextView_Nombre);
			viewHolder.textViewProuctoCiudad = (TextView) vista.findViewById(R.id.Producto_TextView_Ciudad);
			viewHolder.textViewProuctoFecha = (TextView) vista.findViewById(R.id.Producto_TextView_Fecha);
			viewHolder.textViewProuctoPrecio = (TextView) vista.findViewById(R.id.Producto_TextView_Precio);
			viewHolder.imageButtonRemoverCarrito = (ImageButton) vista.findViewById(R.id.Producto_ImageButton_Actualizar_Carrito);
			
			vista.setTag(viewHolder);
		}

        
		ViewHolder holder = (ViewHolder) vista.getTag();
		
		final Producto producto = getItem(position);
		
		holder.textViewProuctoNombre.setText(producto.getNombre());
		holder.textViewProuctoCiudad.setText(producto.getCiudad());
		holder.textViewProuctoFecha.setText(producto.getFechaInicio());
		holder.textViewProuctoPrecio.setText(producto.getPrecioConFormato());
		holder.imageButtonRemoverCarrito.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userName = ecoturismoApplication.getUsuario().getUsuario();
				new RemoverDelCarritoAsyncTask(carritoComprasFragment).execute(userName,producto);
			}
		});
		
			holder.imageButtonRemoverCarrito.setImageResource(R.drawable.remover_de_carrito);
		
		return vista;
	}

	static class ViewHolder {
		TextView textViewProuctoNombre;
		TextView textViewProuctoCiudad;
		TextView textViewProuctoFecha;
		TextView textViewProuctoPrecio;
		ImageButton imageButtonRemoverCarrito; 
	}

}
