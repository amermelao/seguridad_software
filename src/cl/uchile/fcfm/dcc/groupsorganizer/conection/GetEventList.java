package cl.uchile.fcfm.dcc.groupsorganizer.conection;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by Gonzalo on 08-05-2014.
 */
public class GetEventList extends HttpConnection {

	public GetEventList(AsyncHttpClient client) {
		super(client);
	}

	@Override
	protected String getUrl() {
		return "list_events.php";
	}


}
