package grupo6.marketplace.ecoturismo.fragments.pagos;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.activities.PagosActivity;
import grupo6.marketplace.ecoturismo.application.EcoturismoApplication;
import grupo6.marketplace.ecoturismo.asyntask.pagos.PagarAsyncTask;
import grupo6.marketplace.ecoturismo.modelo.enums.TiposPago;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PagoEfectivoFragment extends Fragment {
	
	private EcoturismoApplication ecoturismoApplication;
	private View view;
	private Button buttonPagar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_pago_efectivo,container, false);
		cargarDatosLocales();
		cargarElementosGraficos();
		cargarListeners();
		return view;
	}

	private void cargarDatosLocales() {
		ecoturismoApplication = (EcoturismoApplication) getActivity().getApplication();
	}

	private void cargarElementosGraficos() {
		buttonPagar = (Button) view.findViewById(R.id.Pago_Efectivo_Button);
		
	}

	private void cargarListeners() {
		buttonPagar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userName = ecoturismoApplication.getUsuario().getUsuario();
				new PagarAsyncTask((PagosActivity)getActivity()).execute(userName,TiposPago.CASH_ON_DELIVERY);
			}
		});
		
	}
}
