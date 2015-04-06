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

@SuppressLint("InflateParams")
public class ReportesVentasAdapter extends ArrayAdapter<Integer>{
	
	private int [] imagenesBusquedas = {
			 R.drawable.ubicacion,
		     R.drawable.fechas
		     };
	private static Integer [] busquedas = { R.string.reporte_ventas_por_ciudad,
									R.string.reporte_ventas_entre_fechas};
 	private LayoutInflater layoutInflater;

	public ReportesVentasAdapter(Context context) {
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
			viewHolder.textViewBusqueda = (TextView) vista.findViewById(R.id.Categoria_TextView_Titulo_Item);
			viewHolder.imageViewCategoria = (ImageView) vista.findViewById(R.id.Categoria_ImageView_Item);
			vista.setTag(viewHolder);
		}

        
		ViewHolder holder = (ViewHolder) vista.getTag();
		
		Integer busqueda = getItem(position);
		
		holder.textViewBusqueda.setText(busqueda);
		holder.imageViewCategoria.setImageResource(imagenesBusquedas[position]);
		
		return vista;
	}

	static class ViewHolder {
		TextView textViewBusqueda;
		ImageView imageViewCategoria;
	}

}
