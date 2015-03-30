package grupo6.marketplace.ecoturismo.fragments;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.adapters.ProductosAdapter;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ProductosFragment extends ListFragment{

	private List<Producto> productos;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		cargarProductos();
		ProductosAdapter productosAdapter = new ProductosAdapter(getActivity(),productos);
		setListAdapter(productosAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

	private void cargarProductos() {
		productos = new ArrayList<Producto>();
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
		productos.add(new Producto("Hotel - Campestre", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "San Gil", "25 Marzo 2015", 45000));
		productos.add(new Producto("Cabaña", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Barichara", "25 Marzo 2015", 185000));
		productos.add(new Producto("Restuarante Donde Juancho", "Hotel cerca del Parque del Chicamocha. 4 personas. 2 dias. 2 noches. Incluye desayuno", "Neiva", "25 Marzo 2015", 85000));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Producto producto = productos.get(position);
		AlertUtilidades.mostrarAlert(getActivity(), R.drawable.ic_launcher, R.string.producto_descripcion_titulo, producto.getDescripcion(), R.string.ok);
		super.onListItemClick(l, v, position, id);
	}

}