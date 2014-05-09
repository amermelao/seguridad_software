package cl.uchile.fcfm.dcc.groupsorganizer.conection;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

/**
 * Created by Gonzalo on 08-05-2014.
 */
public abstract class HttpConnection {
    private static final String URL = "http://192.168.137.1/groups_organizer/";
	RequestHandle mRequestHandle;
	private final AsyncHttpClient mClient;

	public HttpConnection(AsyncHttpClient client) {
		mClient = client;
	}

	protected String getBaseUrl() {
        return URL;
    }

    protected String getAbsoluteUrl(String url) {
        return getBaseUrl()+url;
    }

	protected AsyncHttpClient getClient() {
		return mClient;
	}

	protected abstract String getUrl();

	public void go(RequestParams params, AsyncHttpResponseHandler responseHandler) {
		mRequestHandle = getClient().post(getAbsoluteUrl(getUrl()), params, responseHandler);
	}
}
