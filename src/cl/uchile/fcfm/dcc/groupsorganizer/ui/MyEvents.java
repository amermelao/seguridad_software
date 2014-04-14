package cl.uchile.fcfm.dcc.groupsorganizer.ui;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cl.uchile.fcfm.dcc.groupsorganizer.R;

/**
 * Created by Ian on 13-04-2014.
 */
public class MyEvents extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.events_list, container, false);

        return v;
    }
}