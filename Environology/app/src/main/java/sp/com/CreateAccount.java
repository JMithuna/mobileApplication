package sp.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class CreateAccount extends AppCompatActivity {

    private Button buttonCreate;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        //This is the Consumer side activity to create an account.
        getSupportActionBar().hide();

        /*
        xml file: create_account.xml
        */

        /*
        When the user creates their account, so when they press create account button,
        they will be presented to the login page.
         */

        buttonCreate = findViewById(R.id.button_create);
        buttonCreate.setOnClickListener(onCreateAccount);

        etName = findViewById(R.id.enterName);
        etEmail = findViewById(R.id.enterEmail);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirmPassword);

        fAuth = FirebaseAuth.getInstance();

        //Validate data


    }

    private View.OnClickListener onCreateAccount = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etName.setError("Name is required!");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email is required!");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password is required!");
                return;
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                etConfirmPassword.setError("Re-enter Password for confirmation!");
                return;
            }

            if (password.length() < 6) {
                etPassword.setError("Password legnth must be >= 6 characters!");
                return;
            }

            /*
             if (confirmPassword != password) {
                etConfirmPassword.setError("Password does not match! Please re-enter!");
                return;
            }
             */


            //Register the user in firebase
            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User Created.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateAccount.this, Login.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    };
}