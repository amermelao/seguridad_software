package cl.uchile.fcfm.dcc.groupsorganizer.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cl.uchile.fcfm.dcc.groupsorganizer.R;

/**
 * Created by Roberto
 */
public class Register extends Activity {

    EditText mUserName,
            mUserAge,
            mUserMail,
            mUserConfMail,
            mUserPass,
            mUserConfPass;
    Button mOkButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //create the link whit registe.xml
        setContentView(R.layout.register);

        mUserName = (EditText) findViewById(R.id.registerName);
        mUserAge = (EditText) findViewById(R.id.registerAge);
        mUserMail = (EditText) findViewById(R.id.registermail);
        mUserConfMail = (EditText) findViewById(R.id.registerCpnfirmMail);
        mUserPass = (EditText) findViewById(R.id.registerPass);
        mUserConfPass = (EditText) findViewById(R.id.registerConfirmPass);

        mOkButton = (Button) findViewById(R.id.registerButton);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void okInfo(View view){

        boolean equalMail = mUserConfMail.equals(mUserMail);
        boolean equalPass = mUserConfPass.equals(mUserPass);
        boolean notEmptyName = mUserName.length() > 0;
        boolean notEmptyAge = mUserAge.length() > 0;

        if (equalMail && equalPass && notEmptyName && notEmptyAge)
            showRegisterWarning("Register OK");
        else {
            if (!equalMail)
                showRegisterWarning("E-mail don't match.");
            if (!equalPass)
                showRegisterWarning("Password don't match.");
            if (!notEmptyName)
                showRegisterWarning("Please enter a name.");
            if (!notEmptyAge)
                showRegisterWarning("Please enter age.");
        }

    }

    private void showRegisterWarning(CharSequence text){

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        assert context != null;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
