package sp.com;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsumerRecycle#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
xml file: consumer_recycle.xml
 */

public class ConsumerRecycle extends Fragment {

    EditText itemNameRecycle;
    EditText lat_val_recycle;
    EditText lon_val_recycle;
    private ImageView recycle_image;
    private Button recyclesubmit;
    private Button imageRecycle;


    int RESULT_OK = 1;

    //public static final int CAMERA_REQUEST = 100;
    //public static final int STORAGE_REQUEST = 101;
    //String cameraPermission[];
    //String storagePermission[];

    int id = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConsumerRecycle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsumerRecycle.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsumerRecycle newInstance(String param1, String param2) {
        ConsumerRecycle fragment = new ConsumerRecycle();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(), "Successful", Toast.LENGTH_LONG).show();


        /*
        cameraPermission = new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        storagePermission = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

         */


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.consumer_recycle, container, false);

        itemNameRecycle = rootView.findViewById(R.id.itemNameRecycle);
        lat_val_recycle = rootView.findViewById(R.id.et_latRecycle);
        lon_val_recycle = rootView.findViewById(R.id.et_lonRecycle);

        DAORecycle daoRecycle = new DAORecycle();

        //itemNameRecycle = rootView.findViewById(R.id.itemNameRecycle);
        //lat_val_recycle = rootView.findViewById(R.id.et_latRecycle);
        //lon_val_recycle = rootView.findViewById(R.id.et_lonRecycle);
        recyclesubmit = rootView.findViewById(R.id.buttonSubmitRecycle);

        recyclesubmit.setOnClickListener(onRecycleSubmit);
        /*
         recyclesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_LONG).show();
                Recycle recycle = new Recycle(itemNameRecycle.getText().toString(), lat_val_recycle.getText().toString(),
                        lon_val_recycle.getText().toString());
                daoRecycle.add(recycle).addOnSuccessListener(suc-> {
                    Toast.makeText(getActivity(), "Recycle record added successfuly!", Toast.LENGTH_LONG).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(getActivity(), "Error, not inserted into database!", Toast.LENGTH_LONG).show();
                });
            }
        });
         */


        /*
        recyclesubmit.setOnClickListener(v -> {
            Recycle recycle = new Recycle(itemNameRecycle.getText().toString(), lat_val_recycle.getText().toString(),
                    lon_val_recycle.getText().toString());
            daoRecycle.add(recycle).addOnSuccessListener(suc->
            {
                Toast.makeText(getActivity(), "Recycle record added successfuly!", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(er->{
                Toast.makeText(getActivity(), "Error, not inserted into database!", Toast.LENGTH_LONG).show();
            });
         */


            //HashMap<String, Object> hashMap = new HashMap<>();
            //hashMap.put("itemNameRecycle", itemNameRecycle.getText().toString());
            //hashMap.put("lat_val_recycle", lat_val_recycle.getText().toString());
            //hashMap.put("lon_val_recycle", lon_val_recycle.getText().toString());


        //image related
        imageRecycle = rootView.findViewById(R.id.buttonImgRecycle);
        imageRecycle.setOnClickListener(onRecycleImageClick);
        recycle_image = rootView.findViewById(R.id.imageView2);

        return rootView;
    }


    private View.OnClickListener onRecycleSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EnvironmentHelper myDB = new EnvironmentHelper(getActivity());
            myDB.insertRecycle(itemNameRecycle.getText().toString().trim(), lat_val_recycle.getText().toString().trim(),
            lon_val_recycle.getText().toString().trim(), recycle_image);



        }
    };




    private View.OnClickListener onRecycleImageClick = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {

            if (ActivityCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        2000);
            } else {
                startGallery();
            }

            /*
            int avatar =0;
            if (avatar == 0 ) {
                if(!checkCameraPermission()) {
                    requestCameraPermission();
                } else {
                    pickFromGallery();
                }
            } else if (avatar == 1) {
                if (!checkStoragePermission()) {
                    requestStoragePermission();
                } else {
                    pickFromGallery();
                }
            }


             */


        }
    };

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recycle_image.setImageBitmap(bitmapImage);
                recycle_image.setImageURI(returnUri);
            }
        }
        //Uri returnUri;
        //returnUri = data.getData();

    /*
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        getActivity().requestPermissions(storagePermission, STORAGE_REQUEST);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private void pickFromGallery() {
        CropImage.activity().start(getActivity());
        //onActivityResult(requestCode, resultCode, data);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestCameraPermission() {
        getActivity().requestPermissions(cameraPermission, CAMERA_REQUEST);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        boolean result2= ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);
        return result && result2;
    }

    //override method onRequestPermissionResult



     */

/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.getActivity().onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length>0) {
                    boolean camera_accept=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean storage_accept=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if (camera_accept && storage_accept) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(getActivity(), "enable camera and storage permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length>0) {
                    boolean storage_accept = grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    if (storage_accept){
                        pickFromGallery();
                    }else {
                        Toast.makeText(getActivity(), "please enable storage permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }

 */

/*
     @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getActivity(), "launched", Toast.LENGTH_LONG).show();
        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Toast.makeText(getActivity(), "cropped successfully", Toast.LENGTH_LONG).show();
            if (resultCode==-1) {
                Toast.makeText(getActivity(), "image uploaded.", Toast.LENGTH_LONG).show();
                Uri resultUri =result.getUri();
                Picasso.with(getActivity()).load(resultUri).into(recycle_image);
            }
        }
    }

 */


    }
}