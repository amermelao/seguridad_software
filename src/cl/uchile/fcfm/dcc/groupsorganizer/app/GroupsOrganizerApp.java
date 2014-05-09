package cl.uchile.fcfm.dcc.groupsorganizer.app;

import android.app.Application;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;

/**
 * Created by Gonzalo on 08-05-2014.
 */
public class GroupsOrganizerApp extends Application {

	private AsyncHttpClient mHttpClient;

	@Override
	public void onCreate() {
		super.onCreate();
		mHttpClient = new AsyncHttpClient();
		PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
		mHttpClient.setCookieStore(myCookieStore);
	}

	public AsyncHttpClient getAsyncHttpClient() {
		return this.mHttpClient;
	}

}
