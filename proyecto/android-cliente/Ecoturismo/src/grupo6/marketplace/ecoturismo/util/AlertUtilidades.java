package grupo6.marketplace.ecoturismo.util;

import grupo6.marketplace.ecoturismo.R;
import grupo6.marketplace.ecoturismo.modelo.Producto;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Se encarga de mostrar AlertDialog al usuario
 * @author Alejo
 *
 */
@SuppressLint("InflateParams")
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
	
	public static void mostrarAlertProducto(final Activity contexto,Producto producto) {
		LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vista = inflater.inflate(R.layout.dialog_descripcion_producto,null);

		AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
		builder.setView(vista);

		final Dialog dialog = builder.create();
		dialog.show();
	}
	
}
