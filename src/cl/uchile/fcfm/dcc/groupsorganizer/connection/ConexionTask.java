package cl.uchile.fcfm.dcc.groupsorganizer.connection;

import android.app.AlertDialog;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.exception.CustomException;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.exception.ServerErrorException;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.nio.channels.NotYetConnectedException;

public abstract class ConexionTask<T> extends TareaAsincrona {
	
	public enum TipoConexion {WEB_SERVICE, POST}
	private Conexion conexion;

	public ConexionTask(Context context, TaskCallbacks taskCallbacks, TipoConexion tipoConexion) {
		super(context, taskCallbacks);
		conexion = getConexion(tipoConexion, context);
	}
	
	@Override
	protected Object doInBackground(Void... params) {
		if(!chequearParametros()) {
			return null;
		}
		Request request = construirRequest();
		if(request == null) return null;
		try {
			return parsearResultado(conexion.request(request));
		} catch (Exception e) {
			setThrownException(e);
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		Exception e = getThrownException();
		if (e != null) {
			if (e instanceof NotYetConnectedException) {
				Toast.makeText(getContext(), "No hay una conexión de datos activa", Toast.LENGTH_LONG).show();
			} else if (e instanceof IOException || e instanceof ClientProtocolException) {
				Toast.makeText(getContext(), "Ocurrió un error en la conexión", Toast.LENGTH_LONG).show();
			} else if (e instanceof CanceledException || e instanceof InterruptedException) {
				Toast.makeText(getContext(), "Error al traer los datos.", Toast.LENGTH_LONG).show();
			} else if (e instanceof ServerErrorException) {
				if(((ServerErrorException)e).hasWrappedException()) {
					new AlertDialog.Builder(getContext())
						.setTitle("Error de servidor")
						.setMessage(e.getLocalizedMessage())
						.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						}).create().show();
				} else {
					Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				}
			} else if (e instanceof CustomException){
				Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getContext(), "Error desconocido.", Toast.LENGTH_LONG).show();
			}
			e.printStackTrace();
		}
		
	}
	
	protected abstract Boolean chequearParametros();
	
	protected abstract Request construirRequest();
	
	protected abstract T parsearResultado(Object so);
	
	private static Conexion getConexion(TipoConexion tipoConexion, Context context) {
		if(tipoConexion == TipoConexion.POST)
			return new ConexionPost(context);
		return null;
	}

}
