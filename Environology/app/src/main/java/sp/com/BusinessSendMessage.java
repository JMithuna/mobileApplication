package sp.com;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BusinessSendMessage extends AppCompatActivity {

    private Button sendMessageToConsumer;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    private EditText number;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_send_message);

        sendMessageToConsumer = findViewById(R.id.sendMessageToConsumer);
        sendMessageToConsumer.setOnClickListener(onSendToConsumer);

        number = findViewById(R.id.inputNumber);
        message = findViewById(R.id.inputMessage);

        sendMessageToConsumer.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)) {
            sendMessageToConsumer.setEnabled(true);

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }

    private View.OnClickListener onSendToConsumer = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            String phoneNumber = number.getText().toString();
            String smsMessage = message.getText().toString();

            if(phoneNumber == null || phoneNumber.length() == 0 || smsMessage == null || smsMessage.length() == 0) {
                return;
            }

            if(checkPermission(Manifest.permission.SEND_SMS)) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
                Toast.makeText(getApplicationContext(), "Message Sent!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to send message!", Toast.LENGTH_LONG).show();
            }

        }
    };

    public boolean checkPermission(String permission) {

        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}