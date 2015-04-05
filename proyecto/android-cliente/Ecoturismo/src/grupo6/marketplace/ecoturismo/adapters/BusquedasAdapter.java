package grupo6.marketplace.ecoturismo.adapters;

import grupo6.marketplace.ecoturismo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Adaptador que se encarga de pintar cada una de las busquedas del marketplace de ecoturismo
 * @author Alejo
 *
 */
@SuppressLint("InflateParams")
public class BusquedasAdapter extends ArrayAdapter<Integer>{
	
	private int [] imagenesBusquedas = {
			 R.drawable.alojamiento,
		     R.drawable.restaurantes,
		     R.drawable.atracciones
		     };
	private static Integer [] busquedas = { R.string.busqueda_por_ubicacion,
									R.string.busqueda_por_precio,
									R.string.busqueda_por_fecha};
 	private LayoutInflater layoutInflater;

	public BusquedasAdapter(Context context) {
		super(context, 0, busquedas);
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return busquedas.length;
	}

	@Override
	public Integer getItem(int position) {
		return busquedas[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vista = convertView;
		if (vista == null) {
			vista = layoutInflater.inflate(R.layout.fragment_busqueda_item, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.textViewCategoria = (TextView) vista.findViewById(R.id.Categoria_TextView_Titulo_Item);
			viewHolder.imageViewCategoria = (ImageView) vista.findViewById(R.id.Categoria_ImageView_Item);
			vista.setTag(viewHolder);
		}

        
		ViewHolder holder = (ViewHolder) vista.getTag();
		
		Integer busqueda = getItem(position);
		
		holder.textViewCategoria.setText(busqueda);
		holder.imageViewCategoria.setImageResource(imagenesBusquedas[position]);
		
		return vista;
	}

	static class ViewHolder {
		TextView textViewCategoria;
		ImageView imageViewCategoria;
	}

}
