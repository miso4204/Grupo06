package grupo6.marketplace.ecoturismo.activities;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.fragments.busquedas.BusquedasFragment;
import grupo6.marketplace.ecoturismo.fragments.carrito.CarritoComprasFragment;
import grupo6.marketplace.ecoturismo.fragments.proveedor.CrearProductoFragment;
import grupo6.marketplace.ecoturismo.fragments.reportes.ReportesFragment;
import grupo6.marketplace.ecoturismo.fragments.usuario.PerfilFragment;
import grupo6.marketplace.ecoturismo.modelo.enums.RolType;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
/**
 * Actividad que se encarga de cargar los fragmentos de la vista principal (buseudas,carrito de compras, perfil, reportes) 
 * @author Alejo
 *
 */
public class PrincipalActivity extends ActionBarActivity {
 
	public static final int REQUEST_CODE_PRODUCTOS_ACTIVITY = 123;
	public static final int REQUEST_CODE_PAGOS_ACTIVITY = 2345;
	
	private EcoturismoApplication ecoturismoApplication;
	private InicialPagerAdapter principalPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cargarDatosLocales();
        cargarListaFragmentos();
        cargarNavigationTabs();
    }

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getApplication();
	}

	private void cargarListaFragmentos() {
		RolType rol = ecoturismoApplication.getRol();
		
		fragmentos = new ArrayList<Fragment>();
		
		if(rol == RolType.CLIENT){
			cargarFragmentosCliente();	
		}else if(rol == RolType.ADMIN){
			cargarFragmentosAdmin();	
		}else if(rol == RolType.PROVIDER){
			cargarFragmentosProvider();	
		}
	}

	private void cargarFragmentosProvider() {
		fragmentos.add(new CrearProductoFragment());
        fragmentos.add(new PerfilFragment());
	}

	private void cargarFragmentosAdmin() {
        fragmentos.add(new ReportesFragment());
        fragmentos.add(new PerfilFragment());
	}

	private void cargarFragmentosCliente() {
		fragmentos.add(new BusquedasFragment());
        fragmentos.add(new CarritoComprasFragment());
        fragmentos.add(new PerfilFragment());
	}

	private void cargarNavigationTabs() {
		principalPagerAdapter = new InicialPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.Principal_ViewPager);
        viewPager.setAdapter(principalPagerAdapter);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	RolType rol = ecoturismoApplication.getRol();
		
		if(rol == RolType.CLIENT){
			MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.menu_carrito, menu);
		}
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Ver_Carrito:
			mostrarCarritoCompras();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	if(requestCode == REQUEST_CODE_PRODUCTOS_ACTIVITY && resultCode == ProductosActivity.MOSTRAR_CARRITO){
    		mostrarCarritoCompras();
    	}
    	if(requestCode == REQUEST_CODE_PAGOS_ACTIVITY && resultCode == PagosActivity.MOSTRAR_EXITO){
    		AlertUtilidades.mostrarAlert(this, R.drawable.ic_launcher, R.string.app_name, R.string.pagos_exito, R.string.ok);
    	}
    	
    	super.onActivityResult(requestCode, resultCode, intent);
    }
    
	private void mostrarCarritoCompras() {
		int indiceCarrito = buscarIndiceCarrito();
		viewPager.setCurrentItem(indiceCarrito);
	}
	
    private int buscarIndiceCarrito() {
    	int pos = 0;
		for(int i = 0;i<fragmentos.size();i++){
			if(fragmentos.get(i) instanceof CarritoComprasFragment){
				pos = i;
				i = fragmentos.size();
			}
		}
		return pos;
	}

	class InicialPagerAdapter extends FragmentPagerAdapter {
 
        Context context;
 
        public InicialPagerAdapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.context = context;
        }
 
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = fragmentos.get(position);
            return fragment;
        }
 
        @Override
        public int getCount() {
            return fragmentos.size();
        }
 
        @Override
        public CharSequence getPageTitle(int position) {
        	RolType rol = ecoturismoApplication.getRol();
    		
    		if(rol == RolType.CLIENT){
    			return getResources().getStringArray(R.array.titulos_fragmentos_cliente)[position];	
    		}else if(rol == RolType.ADMIN){
    			return getResources().getStringArray(R.array.titulos_fragmentos_admin)[position];
    		}else if(rol == RolType.PROVIDER){
    			return getResources().getStringArray(R.array.titulos_fragmentos_provider)[position];
    		}
    		return getResources().getStringArray(R.array.titulos_fragmentos_cliente)[position];	
        }
    }
}
