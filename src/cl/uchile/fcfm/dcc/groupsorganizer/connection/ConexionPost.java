package cl.uchile.fcfm.dcc.groupsorganizer.connection;

import android.content.Context;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConexionPost extends Conexion {
	
	/**
	 * Cliente HTTP para realizar una petición HTTP.
	 */
	HttpClient httpClient;
	/**
	 * Objeto {@link org.apache.http.client.methods.HttpPost} con el cual se realiza una petición post a una
	 * URL.
	 */
	HttpPost httpPost;
	/**
	 * Parámetros Http para realizar la transacción Http.
	 */
	HttpParams params;

	/**
	 * Constructor que especifica un tiempo de timout para las peticiones http
	 * 
	 * @param context
	 *            contexto de la aplicación que usará la conexión para usar el
	 *            servicio ConnectivityManager
	 */
	
	public ConexionPost(Context context) {
		super(context);
		httpClient = new DefaultHttpClient();
		params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		HttpConnectionParams.setSoTimeout(params, 7000);
	}

	/**
	 * Permite hace una consulta a una URL y retorna los contenidos devueltos
	 * por dicha URL.
	 * @param request La solicitud a enviar al servidor
	 * @return Lo devuelto por el script PHP
	 */
	@Override
	public String request(Request request) throws Exception {
		if (!isOnline(mContext)) {
			throw new NotYetConnectedException();
		}
		Map<String, Object> valores = request.getPackage();
		
		List<NameValuePair> valoresPost = new ArrayList<NameValuePair>();
		
		for(String key : valores.keySet()) {
			valoresPost.add(new BasicNameValuePair(key, valores.get(key).toString()));
		}
			
		InputStream is;
		httpPost = new HttpPost(request.getUri());
		httpPost.setEntity(new UrlEncodedFormEntity(valoresPost));
		httpClient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		HttpResponse respuesta = httpClient.execute(httpPost);
		HttpEntity entity = respuesta.getEntity();
		is = entity.getContent();
		return toStringResponse(is);
	}
	/**
	 * Permite leer los contenidos desde un {@link java.io.InputStream} y convertirlos a
	 * un String.
	 * 
	 * @param is
	 *            El {@link java.io.InputStream} desde el cual leer los contenidos.
	 * @return Un String con los contenidos leídos desde el {@link java.io.InputStream}.
	 */
	private static String toStringResponse(InputStream is) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			return sb.toString();
		} catch (Exception e) {
			throw new org.apache.http.ParseException();
		}
	}
}
