package grupo6.marketplace.ecoturismo.fragments.reportes;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.asyntask.reportes.ReportesVentasPorCiudadAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.ReporteVentasCiudadDTO;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ReporteVentasPorCiudadFragment extends Fragment {

	private View view;
	EditText editTextCiudad;
	Button buttonVerReporte;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_reporte_ventas_ciudad,
				container, false);
		cargarElementosGraficos();
		cargarListeners();
		return view;
	}

	private void cargarElementosGraficos() {
		editTextCiudad = (EditText) view.findViewById(R.id.Reporte_Ventas_Ciudad_EditText);
		buttonVerReporte = (Button) view.findViewById(R.id.Reporte_Ventas_Ciudad_Button);
	}

	private void cargarListeners() {
		buttonVerReporte.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String ciudad = editTextCiudad.getText().toString();
				if(!TextUtils.isEmpty(ciudad)){
					new ReportesVentasPorCiudadAsyncTask(ReporteVentasPorCiudadFragment.this).execute(ciudad);
				}
			}
		});
		
	}

	public void mostrarReporte(ReporteVentasCiudadDTO reporte) {
		AlertUtilidades.mostrarAlert(getActivity(), R.drawable.ic_launcher, R.string.app_name, reporte.toString(), R.string.ok);
		
	}
}
