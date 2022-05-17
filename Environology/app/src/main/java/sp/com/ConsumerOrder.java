package sp.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsumerOrder extends AppCompatActivity {

    private EditText itemNameOrder;
    private EditText lat_val_order;
    private EditText lon_val_order;
    private Button ordersubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_order);

        //retrieve item name and item image (check the update process in the lab assignment

        itemNameOrder = findViewById(R.id.itemNameOrder);
        lat_val_order = findViewById(R.id.et_latOrder);
        lon_val_order = findViewById(R.id.et_lonOrder);
        ordersubmit = findViewById(R.id.buttonSubmitOrder);
        ordersubmit.setOnClickListener(onOrderSubmit);

    }

    private View.OnClickListener onOrderSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //Toast.makeText(getApplicationContext(), "Order placed successfully", Toast.LENGTH_LONG).show();
            EnvironmentHelper myDB = new EnvironmentHelper(ConsumerOrder.this);
            myDB.insertOrders(itemNameOrder.getText().toString().trim(), lat_val_order.getText().toString().trim(),
                    lon_val_order.getText().toString().trim());

        }
    };

}