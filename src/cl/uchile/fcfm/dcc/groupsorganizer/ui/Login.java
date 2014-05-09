package cl.uchile.fcfm.dcc.groupsorganizer.ui;

import org.apache.http.Header;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cl.uchile.fcfm.dcc.groupsorganizer.R;
import cl.uchile.fcfm.dcc.groupsorganizer.conection.LoginConn;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class Login extends CustomFragmentActivity {
    private TextView tvUser, tvPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvUser = (TextView) findViewById(R.id.user);

        tvPassword = (TextView) findViewById(R.id.pass);
    }

    public void onLoginClick(View v) {

	    if(tvUser.getText().length() == 0 || tvPassword.getText().length() == 0) {
		    Toast.makeText(this, "Usuario y/o contraseña no válidos", Toast.LENGTH_SHORT).show();
		    return;
	    }

	    LoginConn loginConn = new LoginConn(getHttpClient());
	    RequestParams reqParams = loginConn.generateParams(tvUser.getText(), tvPassword.getText());
	    loginConn.go(reqParams, new TextHttpResponseHandler() {
		    
		    @Override
		    public void onFailure(int statusCode, Header[] headers, String responseString,
		    		Throwable throwable) {
		    	Toast.makeText(Login.this, "Error when connecting to the server", Toast.LENGTH_LONG).show();
		    }

		    @Override
		    public void onSuccess(int statusCode, Header[] headers, String responseBody) {
			    if(statusCode == 200 && responseBody.trim().equals("LOGIN_SUCCESSFUL")) {
				    doLoginVerified();
			    }
		    }
	    });
    }

    private void doLoginVerified() {
    }
}
