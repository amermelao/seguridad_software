package cl.uchile.fcfm.dcc.groupsorganizer.ui;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import cl.uchile.fcfm.dcc.groupsorganizer.R;
import cl.uchile.fcfm.dcc.groupsorganizer.controller.EventAdapter;
import cl.uchile.fcfm.dcc.groupsorganizer.data.Event;

/**
 * Created by Ian on 13-04-2014.
 */
public class MyEvents extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Event[] data = new Event[3];
        //TODO: Fake data: delete
        for (int i = 0; i < 3; i++)
            data[i] = new Event("Evento " + i, "Santa Rosa", "30", "5");

        EventAdapter adapter = new EventAdapter(getActivity(), R.layout.event_row, data);
        setListAdapter(adapter);
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.events_list, container, false);

        return v;
    }*/
}