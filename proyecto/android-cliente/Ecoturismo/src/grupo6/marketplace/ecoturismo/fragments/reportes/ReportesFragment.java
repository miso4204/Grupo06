package grupo6.marketplace.ecoturismo.fragments.reportes;

import grupo6.marketplace.ecoturismo.activities.ReportesActivity;
import grupo6.marketplace.ecoturismo.adapters.ReportesVentasAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/**
 * Fragmento que muestra la vista de reportes del marketplace de ecoturismo
 * @author Alejo
 *
 */
public class ReportesFragment extends ListFragment{

	public static final String TIPO_REPORTE = "ReportesFragment.TIPO_REPORTE";
	private ReportesVentasAdapter reportesVentasAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		reportesVentasAdapter = new ReportesVentasAdapter(getActivity());
		setListAdapter(reportesVentasAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		int tipoReporte = reportesVentasAdapter.getItem(position);
		abrirReportesActivity(tipoReporte);
		super.onListItemClick(l, v, position, id);
	}
	
	private void abrirReportesActivity(int tipoReporte){
		Intent i = new Intent(getActivity(),ReportesActivity.class);
		i.putExtra(TIPO_REPORTE, tipoReporte);
		getActivity().startActivity(i);
	}

}
