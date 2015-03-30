package grupo6.marketplace.ecoturismo.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Se encarga de mostrar AlertDialog al usuario
 * @author Alejo
 *
 */
public class AlertUtilidades {

	public static void mostrarAlert(final Activity contexto, int icono,int titulo, int mensaje, int neutral) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
		alerta.setTitle(titulo);
		alerta.setMessage(mensaje);
		alerta.setIcon(icono);
		alerta.setNeutralButton(neutral, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		alerta.show();
	}

	public static void mostrarAlert(final Activity contexto, int icono,int titulo, String mensaje, int neutral) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(contexto);
		alerta.setTitle(titulo);
		alerta.setMessage(mensaje);
		alerta.setIcon(icono);
		alerta.setNeutralButton(neutral, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		alerta.show();
	}
	
}
