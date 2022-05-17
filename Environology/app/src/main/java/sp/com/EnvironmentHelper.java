package sp.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
//import android.util.Log;

public class EnvironmentHelper extends SQLiteOpenHelper {

    //database name = environment.db
    //Table's name = shopping_table

    private Context context;
    private static final String DATABASE_NAME = "environment.db"; //name of database
    private static final int SCHEMA_VERSION = 1;
    private static final String RECYCLE_TABLE_NAME = "recycle_table";
    private static final String DONATE_BUY_TABLE_NAME = "donate_buy_table";
    //Donate_Buy table: The list of items donated will appear in the Buy page of consumer side,
    //and the list of donated items will appear in the donated page of business side.
    private static final String ORDERS_TABLE_NAME = "orders_table";
    private static final String REWARDS_TABLE_NAME = "rewards_table";


    public EnvironmentHelper(Context context) {

        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //will be called once when the database is not created
        db.execSQL("CREATE TABLE recycle_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " itemNameRecycle TEXT, lat_val_recycle TEXT, lon_val_recycle TEXT, recycle_image TEXT);");

        db.execSQL("CREATE TABLE donate_buy_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " itemNameDonate TEXT, lat_val_donate TEXT, lon_val_donate TEXT, donate_image BLOB);");

        db.execSQL("CREATE TABLE orders_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " itemNameOrder TEXT, lat_val_order TEXT, lon_val_order TEXT, order_image BLOB);");

        db.execSQL("CREATE TABLE rewards_table (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " greenPoints TEXT);");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "recycle_table");

        db.execSQL("DROP TABLE IF EXISTS " + "donate_buy_table");

        db.execSQL("DROP TABLE IF EXISTS " + "orders_table");

        db.execSQL("DROP TABLE IF EXISTS " + "rewards_table");

        onCreate(db);
    }



    public Cursor getAllRecycle() {

        String recycle_query = "SELECT * FROM " + "recycle_table";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor recycle_cursor = null;

        if (db != null) {
            recycle_cursor = db.rawQuery(recycle_query, null);
        }
        return recycle_cursor;

    }

    public Cursor getAllDonateBuy() {

        String donate_buy_query = "SELECT * FROM " + "donate_buy_table";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor donate_buy_cursor = null;

        if (db != null) {
            donate_buy_cursor = db.rawQuery(donate_buy_query, null);
        }
        return donate_buy_cursor;

    }

    public Cursor getAllOrders() {

        String orders_query = "SELECT * FROM " + "orders_table";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor orders_cursor = null;

        if (db != null) {
            orders_cursor = db.rawQuery(orders_query, null);
        }
        return orders_cursor;
    }

    public Cursor getAllRewards() {

        String rewards_query = "SELECT * FROM " + "rewards_table";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rewards_cursor = null;
        if(db != null) {
            rewards_cursor = db.rawQuery(rewards_query, null);
        }

        return rewards_cursor;
    }



    public void insertRecycle(String itemNameRecycle, String lat_val_recycle, String lon_val_recycle, ImageView recycle_image) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("itemNameRecycle", itemNameRecycle);
        cv.put("lat_val_recycle", lat_val_recycle);
        cv.put("lon_val_recycle", lon_val_recycle);
        cv.put("recycle_image", ImageViewToByte(recycle_image));
        //cv.put("recycle_image", recycle_image);

        long result = db.insert("recycle_table", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add item to list.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully added item to list.", Toast.LENGTH_LONG).show();
        }

    }

    private byte[] ImageViewToByte(ImageView recycle_image) {
        Bitmap bitmap = ((BitmapDrawable)recycle_image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[]bytes=stream.toByteArray();
        return bytes;
    }

    public void insertDonateBuy(String itemNameDonate, String lat_val_donate, String lon_val_donate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("itemNameDonate", itemNameDonate);
        cv.put("lat_val_donate", lat_val_donate);
        cv.put("lon_val_donate", lon_val_donate);

        long result = db.insert("donate_buy_table", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add item to list.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully added item to list.", Toast.LENGTH_LONG).show();
        }

    }



    public void insertOrders(String itemNameOrder, String lat_val_order, String lon_val_order) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("itemNameOrder", itemNameOrder);
        cv.put("lat_val_order", lat_val_order);
        cv.put("lon_val_order", lon_val_order);

        long result = db.insert("orders_table", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add item to list.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully added item to list.", Toast.LENGTH_LONG).show();
        }

    }

    public void insertRewards(String greenPoints) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("greenPoints", greenPoints);

        long result = db.insert("rewards_table", null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add Green Points to list.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully added the points!", Toast.LENGTH_LONG).show();
        }
    }




    void deleteOneRowRecycle(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("recycle_table", "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_LONG).show();

        }

    }





}
