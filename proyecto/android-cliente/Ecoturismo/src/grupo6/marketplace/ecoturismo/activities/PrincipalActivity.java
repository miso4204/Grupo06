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
import android.content.Context;
import android.os.Bundle;

public class PrincipalActivity extends ActionBarActivity {
 
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
