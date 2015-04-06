package grupo6.marketplace.ecoturismo.activities;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.busquedas.BusquedasFragment;
import grupo6.marketplace.ecoturismo.fragments.busquedas.ProductosPorCiudadFragment;
import grupo6.marketplace.ecoturismo.fragments.busquedas.ProductosPorFechaFragment;
import grupo6.marketplace.ecoturismo.fragments.busquedas.ProductosPorPrecioFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
/**
 * Actividad que se encarga de mostrar los productos disponibles 
 * @author Alejo
 *
 */
public class ProductosActivity extends ActionBarActivity {

	static final int MOSTRAR_CARRITO = 123;

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_carrito, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productos);
		cargarFragmentoInicial(getIntent().getExtras().getInt(BusquedasFragment.TIPO_BUSQUEDA));
	}

	private void cargarFragmentoInicial(int tipoBusqueda) {
		switch (tipoBusqueda) {
		case BusquedasFragment.UBICACION:
			reemplazarFragmento(new ProductosPorCiudadFragment());
			break;
		case BusquedasFragment.PRECIO:
			reemplazarFragmento(new ProductosPorPrecioFragment());
			break;
		case BusquedasFragment.FECHA:
			reemplazarFragmento(new ProductosPorFechaFragment());
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        case R.id.Ver_Carrito:
        	setResult(MOSTRAR_CARRITO , getIntent());
        	finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	public void reemplazarFragmento(Fragment fragmento) {
		try{
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.ProductosActivity_FrameLayout, fragmento);
			fragmentTransaction.commit();
		}catch(Exception e){
			e.printStackTrace();	
		}
	}
}
