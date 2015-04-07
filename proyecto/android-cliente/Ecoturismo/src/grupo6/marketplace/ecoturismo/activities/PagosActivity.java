package grupo6.marketplace.ecoturismo.activities;

import grupo6.marketplace.ecoturismo.fragments.carrito.CarritoComprasFragment;
import grupo6.marketplace.ecoturismo.fragments.pagos.PagoEfectivoFragment;
import grupo6.marketplace.ecoturismo.fragments.pagos.PagoPseFragment;
import grupo6.marketplace.ecoturismo.fragments.pagos.PagoTarjetaCreditoFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import grupo6.marketplace.ecoturismo.R;

public class PagosActivity extends ActionBarActivity {

	public static final int MOSTRAR_EXITO = 987;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pagos);
		cargarReporte(getIntent().getExtras().getInt(CarritoComprasFragment.TIPO_PAGO));
	}

	private void cargarReporte(int tipoReporte) {
		switch (tipoReporte) {
		case CarritoComprasFragment.EFECTIVO:
			reemplazarFragmento(new PagoEfectivoFragment());
			break;
		case CarritoComprasFragment.PSE:
			reemplazarFragmento(new PagoPseFragment());
			break;
		case CarritoComprasFragment.TARJETA_CREDITO:
			reemplazarFragmento(new PagoTarjetaCreditoFragment());
			break;
		}
	}

	public void reemplazarFragmento(Fragment fragmento) {
		try {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(R.id.PagosActivity_FrameLayout,
					fragmento);
			fragmentTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pagoExitoso() {
		setResult(MOSTRAR_EXITO , getIntent());
    	finish();
	}

}