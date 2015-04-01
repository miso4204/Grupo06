package grupo6.marketplace.ecoturismo.activities;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.CarritoComprasFragment;
import grupo6.marketplace.ecoturismo.fragments.CategoriasFragment;
import grupo6.marketplace.ecoturismo.fragments.PerfilFragment;
import grupo6.marketplace.ecoturismo.fragments.ReportesFragment;
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
 * Actividad que se encarga de cargar los fragmentos de la vista principal (categorias,carrito de compras, perfil, reportes) 
 * @author Alejo
 *
 */
public class PrincipalActivity extends ActionBarActivity {
 
	public static final int REQUEST_CODE_PRODUCTOS_ACTIVITY = 123;
	
    private static final int INDICE_CARRITO_COMPRAS = 1;
	private InicialPagerAdapter principalPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cargarListaFragmentos();
        cargarNavigationTabs();
    }

	private void cargarListaFragmentos() {
		fragmentos = new ArrayList<Fragment>();
        fragmentos.add(new CategoriasFragment());
        fragmentos.add(new CarritoComprasFragment());
        fragmentos.add(new PerfilFragment());
        fragmentos.add(new ReportesFragment());
	}

	private void cargarNavigationTabs() {
		principalPagerAdapter = new InicialPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.Principal_ViewPager);
        viewPager.setAdapter(principalPagerAdapter);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_carrito, menu);
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
    	
    	super.onActivityResult(requestCode, resultCode, intent);
    }
    
	private void mostrarCarritoCompras() {
		viewPager.setCurrentItem(INDICE_CARRITO_COMPRAS);
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
        	return getResources().getStringArray(R.array.titulos_fragmentos_principales)[position];
        }
    }
}
