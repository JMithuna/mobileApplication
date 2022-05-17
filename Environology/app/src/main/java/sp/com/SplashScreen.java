package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class SplashScreen extends AppCompatActivity {
    Handler h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
        xml file: splash_screen.xml
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //To remove Action Bar from SplashScreen Activity.
        getSupportActionBar().hide();
        h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Home.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}