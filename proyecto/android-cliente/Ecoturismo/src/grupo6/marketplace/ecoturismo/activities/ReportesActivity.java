package grupo6.marketplace.ecoturismo.activities;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.fragments.reportes.ReporteVentasEntreFechasFragment;
import grupo6.marketplace.ecoturismo.fragments.reportes.ReporteVentasPorCiudadFragment;
import grupo6.marketplace.ecoturismo.fragments.reportes.ReportesFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class ReportesActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reportes);
		cargarReporte(getIntent().getExtras().getInt(ReportesFragment.TIPO_REPORTE));
	}

	private void cargarReporte(int tipoReporte) {
		switch (tipoReporte) {
		case R.string.reporte_ventas_por_ciudad:
			reemplazarFragmento(new ReporteVentasPorCiudadFragment());
			break;
		case R.string.reporte_ventas_entre_fechas:
			reemplazarFragmento(new ReporteVentasEntreFechasFragment());
			break;
		}
	}
	
	public void reemplazarFragmento(Fragment fragmento) {
		try{
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.ReportesActivity_FrameLayout, fragmento);
			fragmentTransaction.commit();
		}catch(Exception e){
			e.printStackTrace();	
		}
	}

}