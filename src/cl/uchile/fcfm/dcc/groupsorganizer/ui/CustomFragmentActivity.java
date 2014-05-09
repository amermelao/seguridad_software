package cl.uchile.fcfm.dcc.groupsorganizer.ui;

import android.support.v4.app.FragmentActivity;
import cl.uchile.fcfm.dcc.groupsorganizer.app.GroupsOrganizerApp;

import com.loopj.android.http.AsyncHttpClient;

public abstract class CustomFragmentActivity extends FragmentActivity {

	public AsyncHttpClient getHttpClient() {
		return ((GroupsOrganizerApp)getApplication()).getAsyncHttpClient();

	}
	

}
