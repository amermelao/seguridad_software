package cl.uchile.fcfm.dcc.groupsorganizer.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cl.uchile.fcfm.dcc.groupsorganizer.R;
import cl.uchile.fcfm.dcc.groupsorganizer.data.Event;

/**
 * Created by Ian on 14-04-2014.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    Context context;
    int layoutResourceId;
    Event data[] = null;

    public EventAdapter(Context context, int layoutResourceId, Event[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EventHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EventHolder();
            holder.name = (TextView) row.findViewById(R.id.eventRowTextEventName);
            holder.location = (TextView) row.findViewById(R.id.eventRowTextLocation);
            holder.confirmed = (TextView) row.findViewById(R.id.eventRowTextConfirmed);
            holder.invited = (TextView) row.findViewById(R.id.eventRowTextInvited);

            row.setTag(holder);
        } else {
            holder = (EventHolder) row.getTag();
        }

        Event event = data[position];

        holder.name.setText(event.name);
        holder.location.setText(event.location);
        holder.confirmed.setText(event.confirmed);
        holder.invited.setText(event.invited);

        return row;
    }

    static class EventHolder {
        TextView name, location, confirmed, invited;
    }

}
