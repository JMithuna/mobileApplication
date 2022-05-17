package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BusinessLogin extends AppCompatActivity {

    private Button button_loginbusiness;
    private EditText etBusinessEmail;
    private EditText etBusinessPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_login);

        button_loginbusiness = findViewById(R.id.button_loginBusiness);
        button_loginbusiness.setOnClickListener(onBusinessLogin);

        etBusinessEmail = findViewById(R.id.et_businessEmail);
        etBusinessPassword = findViewById(R.id.et_businessPassword);

    }

    private View.OnClickListener onBusinessLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent i = new Intent(BusinessLogin.this, Business.class);
            //startActivity(i);

            String businessEmailLogin = etBusinessEmail.getText().toString().trim();
            String validateBusinessEmail = "jmithuna2002@gmail.com";
            String businessPasswordLogin = etBusinessPassword.getText().toString().trim();
            String validateBusinessPassword = "Pass1234";


            if ((businessEmailLogin.equals(validateBusinessEmail)) && (businessPasswordLogin.equals(validateBusinessPassword))) {
                Toast.makeText(getApplicationContext(), "Welcome to Business Home Page!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(BusinessLogin.this, Business.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Access denied! Authorised Personnel only!", Toast.LENGTH_LONG).show();
            }

            /*
            The Business Home Page will have the Bottom Navigation Bar.
            (I do not think that the business side needs a Navigation Drawer)
            The Bottom Navigation Bar has the following fragments:
            1. Home
            2. Donated
            3. Recycled
            4. Orders
            5. Partners (Includes partners for the rewards and also list of volunteers)
             */


        }
    };
}