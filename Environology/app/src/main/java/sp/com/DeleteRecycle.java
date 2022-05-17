package sp.com;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteRecycle extends AppCompatActivity {

    EditText nameRecycleDelete, lat_recycleDelete, lon_recycleDelete;
    Button deleteRecyclebtn;

    String id, nameItemRecycleDelete, latRecycleDelete, lonRecycleDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_recycle);

        nameRecycleDelete = findViewById(R.id.itemNameRecycleDelete);
        lat_recycleDelete = findViewById(R.id.et_latRecycleDelete);
        lon_recycleDelete = findViewById(R.id.et_lonRecycle_Delete);
        deleteRecyclebtn = findViewById(R.id.recycleDelete);
        //deleteRecyclebtn.setOnClickListener(onDeleteRecycleData);

        getAndSetRecycleIntentData();

        deleteRecyclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmRecycleDeleteDialog();
                //getFragmentManager().beginTransaction().addToBackStack(null).commit();
            }
        });


    }



    void getAndSetRecycleIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nameItemRecycle") && getIntent().hasExtra("lat_recycle") &&
                getIntent().hasExtra("lon_recycle")) {
            //getting data from intent
            id = getIntent().getStringExtra("id");
            nameItemRecycleDelete = getIntent().getStringExtra("nameItemRecycle");
            latRecycleDelete = getIntent().getStringExtra("lat_recycle");
            lonRecycleDelete = getIntent().getStringExtra("lon_recycle");

            //setting intent data
            nameRecycleDelete.setText(nameItemRecycleDelete);
            lat_recycleDelete.setText(latRecycleDelete);
            lon_recycleDelete.setText(lonRecycleDelete);
        } else {
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }
    }

    void confirmRecycleDeleteDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DeleteRecycle.this);
        alertDialog.setTitle("Confirm DELETE?");
        alertDialog.setMessage("Are you sure you want to Delete the item?");

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                EnvironmentHelper myDB = new EnvironmentHelper(DeleteRecycle.this);
                myDB.deleteOneRowRecycle(id);
                finish();



                //System.exit(0);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }
}