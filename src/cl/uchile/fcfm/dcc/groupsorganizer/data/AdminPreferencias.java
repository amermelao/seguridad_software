package cl.uchile.fcfm.dcc.groupsorganizer.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;
import java.util.HashMap;

public class AdminPreferencias {
    // Categorías en las preferencias PREFERENCIAS_DATOS.
    public static final String
            PERSONAL = "Personal", GIRADORES = "Giradores", RANKING = "Ranking",
            INFO_MUNICIPALIDAD = "Info_Comuna", DEUDORES = "Deudores", INGRESOS = "Ingresos", GASTOS = "Gastos",
            CONCEJALES = "Concejales", IMAGEN_COMUNA = "Imagen_Comuna", AREAS = "Areas";
    // Preferencias disponibles en la aplicación.
    private static final String
            PREFERENCIAS_GENERALES = "PREFERENCIAS_GENERALES", PREFERENCIAS_DATOS = "PREFERENCIAS_DATOS";
    // Categorías disponibles en las preferencias  PREFERENCIAS_GENERALES
    private static final String
            IP_SERVIDOR = "IP_SERVIDOR", ID_USUARIO = "ID_USUARIO", COD_AREA = "COD_AREA",
            CODIGO_SISTEMA = "CODIGO_SISTEMA", TIPO_CONEXION = "TIPO_CONEXION",
            NUMERO_NIVELES = "NUMERO_NIVELES", HASH_CALLES = "HASH_CALLES", COD_CLIENTE = "COD_CLIENTE",
            ULT_FECHA_INICIO = "ULT_FECHA_INICIO", ULT_FECHA_FIN = "ULT_FECHA_FIN",
            AREA_SELECCIONADA = "AREA_SELECCIONADA";

    private Context mContext;

    public AdminPreferencias(Context context) {
        mContext = context;
    }

    public String getIpServidor() {
        return getPreferencias().getString(IP_SERVIDOR, null);
    }

    public void setIpServidor(String ip) {
        getPreferencias().edit().putString(IP_SERVIDOR, ip).commit();
    }

    public String getUltimoHashCalle() {
        return getPreferencias().getString(HASH_CALLES, "");
    }

    public void setUltimoHashCalle(String hash) {
        getPreferencias().edit().putString(HASH_CALLES, hash).commit();
    }

    public HashMap<Integer, String> getAreas() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        String consAreas = getPreferenciasDatos().getString(AREAS, "");
        if (consAreas.equals("") == false) {    // si hay datos
            String[] parAreas = consAreas.split(";");
            for (String par : parAreas) {
                String[] sPar = par.split(":");
                if (sPar.length != 2) continue;
                map.put(Integer.parseInt(sPar[0]), sPar[1]);
            }
        }
        return map;
    }

    private SharedPreferences getPreferencias() {
        return mContext.getSharedPreferences(PREFERENCIAS_GENERALES, 0);
    }

    public SharedPreferences getPreferenciasDatos() {
        return mContext.getSharedPreferences(PREFERENCIAS_DATOS, 0);
    }

    public int getAreaSeleccionada() {
        return getPreferencias().getInt(AREA_SELECCIONADA, -1);
    }

    public void setAreaSeleccionada(int cod) {
        getPreferencias().edit().putInt(AREA_SELECCIONADA, cod).commit();
    }

    public int getCodCliente() {
        return getPreferencias().getInt(COD_CLIENTE, -1);
    }

    public void setCodCliente(int i) {
        getPreferencias().edit().putInt(COD_CLIENTE, i).commit();
    }

    public Date getFechaInicio() {
        Long ms = getPreferencias().getLong(ULT_FECHA_INICIO, -1);
        if (ms == -1)
            return null;
        return new Date(ms);
    }

    public void setFechaInicio(Date date) {
        getPreferencias().edit().putLong(ULT_FECHA_INICIO, date.getTime()).commit();
    }

    public Date getFechaFin() {
        Long ms = getPreferencias().getLong(ULT_FECHA_FIN, -1);
        if (ms == -1)
            return null;
        return new Date(ms);
    }

    public void setFechaFin(Date date) {
        getPreferencias().edit().putLong(ULT_FECHA_FIN, date.getTime()).commit();
    }

    public int getCodArea() {
        return getPreferencias().getInt(COD_AREA, 1);
    }

    public void setCodArea(int codArea) {
        getPreferencias().edit().putInt(COD_AREA, codArea).commit();
    }

    public int getTipoConexion() {
        return getPreferencias().getInt(TIPO_CONEXION, -1);
    }

    public void setTipoConexion(int tipoConexion) {
        getPreferencias().edit().putInt(TIPO_CONEXION, tipoConexion).commit();
    }

    public int getCodSistema() {
        return getPreferencias().getInt(CODIGO_SISTEMA, -1);
    }

    public void setCodSistema(int codSistema) {
        getPreferencias().edit().putInt(CODIGO_SISTEMA, codSistema).commit();
    }

    public int getNumNiveles() {
        return getPreferencias().getInt(NUMERO_NIVELES, -1);
    }

    public void setNumNiveles(int value) {
        getPreferencias().edit().putInt(NUMERO_NIVELES, value).commit();
    }

    public void setValores(String tipoValores, Object data) {
        if (data == null) return;
        getPreferenciasDatos().edit().putString(tipoValores, data.toString()).commit();
    }
    /*
	public DatosSeccion getValores(String tipoValores) {
		String jsonString = getPreferenciasDatos().getString(tipoValores, "{}");
		try {
			return new DatosSeccion(jsonString);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
