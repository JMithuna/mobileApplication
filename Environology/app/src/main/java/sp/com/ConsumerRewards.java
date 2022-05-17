package sp.com;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsumerRewards#newInstance} factory method to
 * create an instance of this fragment.
 */

/*
xml file: consumer_rewards.xml
 */

public class ConsumerRewards extends Fragment {

    RecyclerView recyclerView;
    EnvironmentHelper myDB;
    ArrayList<String> row_id, greenPoints;
    CustomAdapterRewards customAdapterRewards;
    private Button qr_scanner;
    String[] permissions = {
            Manifest.permission.CAMERA
    };

    int PERM_CODE = 11;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConsumerRewards() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsumerRewards.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsumerRewards newInstance(String param1, String param2) {
        ConsumerRewards fragment = new ConsumerRewards();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.consumer_rewards, container, false);

        recyclerView = view.findViewById(R.id.rewards_rv);
        myDB = new EnvironmentHelper(getActivity());
        greenPoints = new ArrayList<>();

        qr_scanner = view.findViewById(R.id.qrScanner);
        qr_scanner.setOnClickListener(onQRScan);

        checkpermissions();

        storeRewardsDataInArrays();

        customAdapterRewards = new CustomAdapterRewards(getActivity(), row_id, greenPoints);
        recyclerView.setAdapter(customAdapterRewards);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    void storeRewardsDataInArrays() {
        Cursor cursor = myDB.getAllRewards();
        if(cursor.getCount() == 0 ) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                greenPoints.add(cursor.getString(1));
            }
        }
    }

    private View.OnClickListener onQRScan = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            Intent i = new Intent(getActivity(), QRScanner.class);
            startActivity(i);
        }

    };

    private boolean checkpermissions() {
        List<String> listofpermissions = new ArrayList<>();
        for (String perm: permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), perm) != PackageManager.PERMISSION_GRANTED) {
                listofpermissions.add(perm);
            }
        }

        if (!listofpermissions.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listofpermissions.toArray(new String[listofpermissions.size()]), PERM_CODE);
            return false;
        }

        return true;
    }
}