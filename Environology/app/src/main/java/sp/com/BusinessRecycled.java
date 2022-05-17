package sp.com;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessRecycled#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessRecycled extends Fragment {

    //RecyclerView

    //RecyclerView recyclerView;

    //RecyclerView


    RecyclerView recyclerView;
    EnvironmentHelper myDB;
    ArrayList<String> row_id, nameItemRecycle, lat_recycle, lon_recycle;
    CustomAdapter customAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusinessRecycled() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessRecycled.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessRecycled newInstance(String param1, String param2) {
        BusinessRecycled fragment = new BusinessRecycled();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        //RecyclerView



        //RecyclerView

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.business_recycled, container, false);

        recyclerView = rootview.findViewById(R.id.recyclerviewrecycled);

        //recyclerview
        myDB = new EnvironmentHelper(getActivity());

        row_id = new ArrayList<>();
        nameItemRecycle = new ArrayList<>();
        lat_recycle = new ArrayList<>();
        lon_recycle = new ArrayList<>();

        storeRecycleDataInArrays();

        customAdapter = new CustomAdapter(getActivity(), row_id, nameItemRecycle, lat_recycle, lon_recycle);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //BusinessRecycled.recyclerView.getAdapter().notifyDataSetChanged();

        return rootview;

    }

    public void storeRecycleDataInArrays() {
        Cursor recycleCursor = myDB.getAllRecycle();
        if(recycleCursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No Data!", Toast.LENGTH_LONG).show();
        } else {
            while(recycleCursor.moveToNext()) {
                row_id.add(recycleCursor.getString(0));
                nameItemRecycle.add(recycleCursor.getString(1));
                lat_recycle.add(recycleCursor.getString(2));
                lon_recycle.add(recycleCursor.getString(3));
            }
        }
    }



}