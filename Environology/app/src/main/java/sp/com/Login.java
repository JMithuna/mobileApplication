package sp.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    /*
    xml file: login.xml
    */

    private Button buttonLogin;
    private EditText etEmailLogin;
    private EditText etPasswordLogin;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(onLogin);

        etEmailLogin = findViewById(R.id.etEmailEnter);
        etPasswordLogin = findViewById(R.id.etPasswordEnter);
        fAuth = FirebaseAuth.getInstance();

    }

    private View.OnClickListener onLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String emailLogin = etEmailLogin.getText().toString().trim();
            String passwordLogin = etPasswordLogin.getText().toString().trim();



            if (TextUtils.isEmpty(emailLogin)) {
                etEmailLogin.setError("Email is required!");
                return;
            }

            if (TextUtils.isEmpty(passwordLogin)) {
                etPasswordLogin.setError("Password is required!");
                return;
            }

            //Authenticate the User
            fAuth.signInWithEmailAndPassword(emailLogin, passwordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, Consumer.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    };
}