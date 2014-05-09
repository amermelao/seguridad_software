package cl.uchile.fcfm.dcc.groupsorganizer.connection.tasks;

import android.content.Context;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.ConexionTask;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.Request;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.TaskCallbacks;

/**
 * Created by Gonzalo on 13-04-2014.
 */
public class LoginConnection extends ConexionTask<Boolean> {

    private String userName, password;
    public LoginConnection(Context context, TaskCallbacks taskCallbacks) {
        super(context, taskCallbacks, TipoConexion.POST);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Boolean chequearParametros() {
        return userName != null && password != null && userName.length() > 0 && password.length() > 0;
    }

    @Override
    protected Request construirRequest() {
        Request request = new Request("http://localhost/groups_organizer/login.php");
        request.add("user", userName);
        request.add("pass", password);

        return request;
    }

    @Override
    protected Boolean parsearResultado(Object so) {
        if(so != null) {
            return so.toString().equals("YES");
        }
        return false;
    }
}
