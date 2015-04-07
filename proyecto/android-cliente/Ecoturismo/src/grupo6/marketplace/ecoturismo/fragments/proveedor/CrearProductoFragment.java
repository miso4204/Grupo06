package grupo6.marketplace.ecoturismo.fragments.proveedor;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.asyntask.proveedor.CrearProductoAsynctTask;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CrearProductoFragment extends Fragment{


	private View view;
	private EditText editTextNombre;
	private EditText editTextLugar;
	private EditText editTextCiudad;
	private EditText editTextPrecio;
	private EditText editTextFechaInicio;
	private EditText editTextUrlImagen;
	private Button buttonCrear;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_crear_producto, container, false);
        cargarElementosGraficos();
        cargarListeners();
        return view;
    }

	private void cargarListeners() {
		buttonCrear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try{
					String nombre = editTextNombre.getText().toString();
					String lugar = editTextLugar.getText().toString();
					String ciudad = editTextCiudad.getText().toString();
					String precio = editTextPrecio.getText().toString();
					String fechaInicio = editTextFechaInicio.getText().toString();
					String urlImagen = editTextUrlImagen.getText().toString();
					
					if(!TextUtils.isEmpty(nombre) &&
							!TextUtils.isEmpty(lugar) &&
							!TextUtils.isEmpty(ciudad) &&
							!TextUtils.isEmpty(precio) &&
							!TextUtils.isEmpty(fechaInicio) &&
							!TextUtils.isEmpty(urlImagen)
						){
						Producto p = new Producto();
						p.setNombre(nombre);
						p.setLugar(lugar);
						p.setCiudad(ciudad);
						p.setPrecio(Double.parseDouble(precio));
						p.setFechaInicio(fechaInicio);
						p.setUrlImagen(urlImagen);
						
						new CrearProductoAsynctTask(CrearProductoFragment.this).execute(p);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
	}

	private void cargarElementosGraficos() {
		editTextNombre = (EditText) view.findViewById(R.id.Crear_Producto_EditText_Nombre);
		editTextLugar = (EditText) view.findViewById(R.id.Crear_Producto_EditText_Lugar);
		editTextCiudad = (EditText) view.findViewById(R.id.Crear_Producto_EditText_Ciudad);
		editTextPrecio = (EditText) view.findViewById(R.id.Crear_Producto_EditText_Precio);
		editTextFechaInicio = (EditText) view.findViewById(R.id.Crear_Producto_EditText_Fecha);
		editTextUrlImagen = (EditText) view.findViewById(R.id.Crear_Producto_EditText_Url_Imagen);
		buttonCrear = (Button) view.findViewById(R.id.Crear_Producto_Button_Crear_Producto);
	}
}