package grupo6.marketplace.ecoturismo.adapters;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.modelo.sql.EcoturismoSqlHelper;
import grupo6.marketplace.ecoturismo.modelo.sql.tables.CarritoComprasTable;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Adaptador que se encarga de pintar cada uno de las productos del marketplace de ecoturismo
 * @author Alejo
 *
 */
@SuppressLint("InflateParams")
public class ProductosAdapter extends ArrayAdapter<Producto>{
	
	private List<Producto> productos;
 	private LayoutInflater layoutInflater;
 	private EcoturismoSqlHelper ecoturismoSqlHelper;
 	
	public ProductosAdapter(Context context,List<Producto> productos,EcoturismoSqlHelper ecoturismoSqlHelper) {
		super(context, 0, productos);
		if(productos != null){
			this.productos = productos;
		}else{
			this.productos = new ArrayList<Producto>();
		}
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.ecoturismoSqlHelper = ecoturismoSqlHelper;
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
			viewHolder.imageButtonAgregarCarrito = (ImageButton) vista.findViewById(R.id.Producto_ImageButton_Actualizar_Carrito);
			
			vista.setTag(viewHolder);
		}

        
		ViewHolder holder = (ViewHolder) vista.getTag();
		
		final Producto producto = getItem(position);
		
		holder.textViewProuctoNombre.setText(producto.getNombre());
		holder.textViewProuctoCiudad.setText(producto.getCiudad());
		holder.textViewProuctoFecha.setText(producto.getFechaInicio());
		holder.textViewProuctoPrecio.setText(producto.getPrecioConFormato());
		holder.imageButtonAgregarCarrito.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				CarritoComprasTable.agregarACarrito(ecoturismoSqlHelper, producto);
			}
		});
		
			holder.imageButtonAgregarCarrito.setImageResource(R.drawable.agregar_a_carrito);
		
		return vista;
	}

	static class ViewHolder {
		TextView textViewProuctoNombre;
		TextView textViewProuctoCiudad;
		TextView textViewProuctoFecha;
		TextView textViewProuctoPrecio;
		ImageButton imageButtonAgregarCarrito; 
	}
}
