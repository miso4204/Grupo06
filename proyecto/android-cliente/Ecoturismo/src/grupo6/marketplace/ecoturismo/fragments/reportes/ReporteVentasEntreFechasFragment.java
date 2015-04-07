package grupo6.marketplace.ecoturismo.fragments.reportes;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.asyntask.reportes.ReportesVentasFechasAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.ReporteVentasFechasDTO;
import grupo6.marketplace.ecoturismo.util.AlertUtilidades;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ReporteVentasEntreFechasFragment  extends Fragment {

	private View view;
	private EditText editTextFechaInicial;
	private EditText editTextFechaFinal;
	private Button buttonVerReporte;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_reporte_ventas_fechas,container, false);
		cargarElementosGraficos();
		cargarListeners();
		return view;
	}

	private void cargarElementosGraficos() {
		editTextFechaInicial = (EditText) view.findViewById(R.id.Reporte_Ventas_Entre_Fecha_EditText_FechaInicio);
		editTextFechaFinal = (EditText) view.findViewById(R.id.Reporte_Ventas_Entre_Fecha_EditText_FechaFinal);
		buttonVerReporte = (Button) view.findViewById(R.id.Reporte_Ventas_Entre_Fecha_Button);
		
	}

	private void cargarListeners() {
		buttonVerReporte.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String inicio = editTextFechaInicial.getText().toString();
				String fin = editTextFechaFinal.getText().toString();
				if(!TextUtils.isEmpty(inicio)
						&& !TextUtils.isEmpty(fin)){
					new ReportesVentasFechasAsyncTask(ReporteVentasEntreFechasFragment.this).execute(inicio,fin);
				}
			}
		});
		
	}

	public void mostrarReporte(ReporteVentasFechasDTO reporte) {
		AlertUtilidades.mostrarAlert(getActivity(), R.drawable.ic_launcher, R.string.app_name, reporte.toString(), R.string.ok);
	}
}
