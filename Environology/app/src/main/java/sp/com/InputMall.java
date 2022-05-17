package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputMall extends AppCompatActivity {

    EditText latitudem, longitudem, mallname;
    Button savemall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_mall);

        mallname = findViewById(R.id.mallname);
        latitudem = findViewById(R.id.latitudem);
        longitudem = findViewById(R.id.longitudem);
        savemall = findViewById(R.id.savemall);
        savemall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitudemall = latitudem.getText().toString();
                String longitudemall = longitudem.getText().toString();
                //String namemall = mallname.getText().toString();

                //Intent i = new Intent(DataInput.this, CustomAdapter.class);

                //extras.putString("latitudeValue", latVal);
                //extras.putString("longitudeValue", lonVal);
                //extras.putDouble("latDouble", lat1);
                //extras.putDouble("lonDouble", lon1);
                //i.putExtras(extras);

                Intent i1 = new Intent(InputMall.this , CustomAdapterRewards.class);
                Bundle extras = new Bundle();
                extras.putString("LATITUDE", latitudemall);
                extras.putString("LONGITUDE",longitudemall);
                //i1.putExtra("NAME", namemall);
                i1.putExtras(extras);

            }
        });


    }
}