package cl.uchile.fcfm.dcc.groupsorganizer.ui;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import cl.uchile.fcfm.dcc.groupsorganizer.R;
import cl.uchile.fcfm.dcc.groupsorganizer.cl.uchile.fcfm.dcc.groupsorganizer.data.AdminPreferencias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Created by Ian on 13-04-2014.
 */
public class PublicEvents extends ListFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Spinner mAreaSpinner;
    public boolean bloquearUpdateSpinner;

    private TextView alcalde, SecMun, administrador, secPlac, concejales;
    private ImageView imagenPrincipal;

    private class Area {
        public Area(int codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }

        public int getCodigo() {
            return codigo;
        }

        public String toString() {
            return nombre;
        }

        String nombre;
        int codigo;
    }

    /**
     * The Fragment's UI is just a simple text view showing its instance number.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.events_list, container, false);

//
//        alcalde = (TextView) v.findViewById(R.id.textAlcalde);
//        SecMun = (TextView) v.findViewById(R.id.textSecretarioMunicipal);
//        administrador = (TextView) v.findViewById(R.id.textAdministradorMunicipal);
//        secPlac = (TextView) v.findViewById(R.id.textSecretarioPlanificacion);
//        concejales = (TextView) v.findViewById(R.id.textConcejales);
//
//        imagenPrincipal = (ImageView) v.findViewById(R.id.logoMunicipalidad);
        AdminPreferencias pref = new AdminPreferencias(getActivity());
        HashMap<Integer, String> m = pref.getAreas();
        ArrayList<Area> string_array = new ArrayList<Area>();
        for (Entry<Integer, String> entry : m.entrySet()) {
            string_array.add(new Area(entry.getKey(), entry.getValue()));
        }
        final Area[] areas = string_array.toArray(new Area[string_array.size()]);


        return v;
    }

    private void mostrarImagenBase64(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imagenPrincipal.setImageBitmap(bm);
    }

    @Override
    public void onResume() {
        super.onResume();
        new AdminPreferencias(getActivity()).getPreferenciasDatos()
                .registerOnSharedPreferenceChangeListener(this);
        onDataChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        new AdminPreferencias(getActivity()).getPreferenciasDatos()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    private void onDataChanged() {
        /* Cargamos la info */
//        DatosSeccion datosSeccion = new AdminPreferencias(getActivity())
//                .getValores(AdminPreferencias.INFO_MUNICIPALIDAD);
//        Map<String, Object> map = datosSeccion.getMap();
//        if (map.size() != 0) {
//            alcalde.setText(map.get("alcalde").toString());
//            administrador.setText(map.get("administrador").toString());
//            SecMun.setText(map.get("secretario_municipal").toString());
//            secPlac.setText(map.get("secplac").toString());
//        } else setInvalid(1);

//		/* Cargamos el logo */
//        datosSeccion = new AdminPreferencias(getActivity()).getValores(AdminPreferencias.IMAGEN_COMUNA);
//        map = datosSeccion.getMap();
//        if (map.size() != 0) {
//            mostrarImagenBase64(map.get("Imagen").toString());
//        }
//		/* Cargamos los concejales */
//        datosSeccion = new AdminPreferencias(getActivity()).getValores(AdminPreferencias.CONCEJALES);
//        map = datosSeccion.getMap();
//        if (map.size() != 0) {
//            JSONArray array;
//            try {
//                array = (JSONArray)map.get("Concejales");
//                StringBuilder sb = new StringBuilder();
//                for (int i=0;i<array.length();i++)
//                    sb.append("> ").append(array.getString(i)).append("\n");
//                concejales.setText(sb.toString());
//            } catch (JSONException e) {
//                concejales.setText("");
//                e.printStackTrace();
//            }
//        }
//
//        datosSeccion = new AdminPreferencias(getActivity())
//                .getValores(AdminPreferencias.IMAGEN_COMUNA);
//        map = datosSeccion.getMap();
//        if (datosSeccion.getMap().size() != 0) {
//            mostrarImagenBase64(map.get("Imagen").toString());
//        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(AdminPreferencias.INFO_MUNICIPALIDAD) || key.equals(AdminPreferencias.IMAGEN_COMUNA) || key.equals(AdminPreferencias.CONCEJALES))
            onDataChanged();
    }

    private void setInvalid(int i) {
        if (i == 1) {
            alcalde.setText("Región: No hay datos");
            SecMun.setText("No hay datos");
            administrador.setText("Provincia: No hay datos");
            secPlac.setText("No hay datos");
        } else concejales.setText("No hay datos");
    }
}
