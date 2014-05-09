package cl.uchile.fcfm.dcc.groupsorganizer.data;

/**
 * Created by Ian on 14-04-2014.
 */
public class Event {
    public String name;
    public String location;
    public String confirmed;
    public String invited;

    public Event(String name, String location, String confirmed, String invited) {
        this.name = name;
        this.location = location;
        this.confirmed = confirmed;
        this.invited = invited;
    }
}
