package cl.uchile.fcfm.dcc.groupsorganizer.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public abstract class Conexion {

	/**
	 * Contexto desde el cual se ejecuta la conexión a la BD.
	 */
	protected Context mContext;

	public Conexion(Context context) {
		this.mContext = context;
	}

	/**
	 * Permite saber si el dispositivo tiene conexión a internet
	 * 
	 * @return True si existe una conexión a internet. False, de lo contrario.
	 */
	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
	
	public abstract Object request(Request request) throws Exception;

	protected Context getContext() {
		return mContext;
	}
}