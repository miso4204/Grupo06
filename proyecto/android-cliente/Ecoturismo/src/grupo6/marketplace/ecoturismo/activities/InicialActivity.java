package grupo6.marketplace.ecoturismo.activities;

import java.util.ArrayList;
import java.util.List;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.LoginFragment;
import grupo6.marketplace.ecoturismo.fragments.RegistroFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

public class InicialActivity extends ActionBarActivity {
 
    private InicialPagerAdapter inicialPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragmentos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        cargarListaFragmentos();
        cargarNavigationTabs();
    }

	private void cargarListaFragmentos() {
		fragmentos = new ArrayList<Fragment>();
        fragmentos.add(new LoginFragment());
        fragmentos.add(new RegistroFragment());
	}

	private void cargarNavigationTabs() {
		inicialPagerAdapter = new InicialPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.Inicial_ViewPager);
        viewPager.setAdapter(inicialPagerAdapter);
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
            return getResources().getStringArray(R.array.titulos_fragmentos_iniciales)[position];
        }
    }
 
}