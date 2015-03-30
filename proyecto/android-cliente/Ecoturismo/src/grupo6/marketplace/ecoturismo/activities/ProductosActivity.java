package grupo6.marketplace.ecoturismo.activities;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.ProductosFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ProductosActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productos);
		reemplazarFragmento(new ProductosFragment());
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:
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
