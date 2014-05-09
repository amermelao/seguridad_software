package cl.uchile.fcfm.dcc.groupsorganizer.conection;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Created by Gonzalo on 29-04-2014.
 */
public class LoginConn extends HttpConnection {

	private static final String mUrl = "login.php";

	public LoginConn(AsyncHttpClient client) {
		super(client);
	}

	@Override
	protected String getUrl() {
		return mUrl;
	}

	public RequestParams generateParams(CharSequence user, CharSequence pass) {
        return new RequestParams("user", user, "pass", pass);
    }

}
