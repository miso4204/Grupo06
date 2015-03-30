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
 * Adaptador que se encarga de pintar cada una de las categorias del marketplace de ecoturismo
 * @author Alejo
 *
 */
@SuppressLint("InflateParams")
public class CategoriasAdapter extends ArrayAdapter<String>{
	
	private int [] imagenesCategorias = {R.drawable.alojamiento,
		     R.drawable.restaurantes,
		     R.drawable.atracciones
		     };
	private String[] categorias;
 	private LayoutInflater layoutInflater;

	public CategoriasAdapter(Context context,String [] categorias) {
		super(context, 0, categorias);
		this.categorias = categorias;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return categorias.length;
	}

	@Override
	public String getItem(int position) {
		return categorias[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vista = convertView;
		if (vista == null) {
			vista = layoutInflater.inflate(R.layout.fragment_categoria_item, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.textViewCategoria = (TextView) vista.findViewById(R.id.Categoria_TextView_Titulo_Item);
			viewHolder.imageViewCategoria = (ImageView) vista.findViewById(R.id.Categoria_ImageView_Item);
			vista.setTag(viewHolder);
		}

        
		ViewHolder holder = (ViewHolder) vista.getTag();
		
		String categoria = getItem(position);
		
		holder.textViewCategoria.setText(categoria);
		holder.imageViewCategoria.setImageResource(imagenesCategorias[position]);
		
		return vista;
	}

	static class ViewHolder {
		TextView textViewCategoria;
		ImageView imageViewCategoria;
	}

}
