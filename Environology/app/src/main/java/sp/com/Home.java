package sp.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    private Button createAccount;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportActionBar().hide();

        /*
        xml file: home.xml
        */

        //This is the Consumer Home Page
        /*
        App colour
        --> green_teal for main layout
        --> green_semi for text?
        --> green_light for icons etc.
         */

        /*
        INCLUDE THE BUSINESS SIDE OPTION HERE AS WELL;
        THE BUSINESS SIDE OPTION SHOULD ALSO HAVE A PASSWORD FIELD
        SO THAT ONLY THE ACTUAL BUSINESS PEOPLE CAN ENTER.
         */


        createAccount = findViewById(R.id.createAcc);
        createAccount.setOnClickListener(onCreateAcc);

        logIn = findViewById(R.id.loginButton);
        logIn.setOnClickListener(onLogin);
    }

    private View.OnClickListener onCreateAcc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Home.this, CreateAccount.class);
            startActivity(i);
        }
    };

    private View.OnClickListener onLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
            alertDialog.setTitle("Business or User?");
            alertDialog.setMessage("Are you the Business Owner or Consumer?");

            alertDialog.setPositiveButton("Business Owner", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {

                    Intent i  = new Intent(Home.this, BusinessLogin.class);
                    startActivity(i);
                    //finish();
                    //System.exit(0);
                }
            });

            alertDialog.setNegativeButton("Consumer", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    //dialog.cancel();
                    Intent i = new Intent(Home.this, Login.class);
                    startActivity(i);
                }
            });

            alertDialog.show();

            //Intent i = new Intent(Home.this, Login.class);
            //startActivity(i);
        }
    };
}