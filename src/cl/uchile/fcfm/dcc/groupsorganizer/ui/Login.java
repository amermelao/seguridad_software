package cl.uchile.fcfm.dcc.groupsorganizer.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cl.uchile.fcfm.dcc.groupsorganizer.R;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.TareaAsincrona;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.tasks.LoginConnection;

public class Login extends CustomFragmentActivity {
    private TextView tvUser, tvPassword;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvUser = (TextView) findViewById(R.id.login);
        tvPassword = (TextView) findViewById(R.id.pass);
    }

    public void onLoginClick(View v) {
        if(tvUser.getText().length() == 0 || tvPassword.getText().length() == 0) {
            Toast.makeText(this, "Usuario y/o contraseña no válidos", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginConnection loginConnection = new LoginConnection(this, this);
        loginConnection.setUserName(tvUser.getText().toString());
        loginConnection.setUserName(tvPassword.getText().toString());
        runTask(loginConnection);
    }

    @Override
    public void onPostExecute(TareaAsincrona tarea, Object... vars) {
        super.onPostExecute(tarea, vars);
        if(tarea instanceof LoginConnection) {
            if((Boolean)vars[0]) {
                doLoginVerified();
            }
        }
    }

    private void doLoginVerified() {

    }
}
