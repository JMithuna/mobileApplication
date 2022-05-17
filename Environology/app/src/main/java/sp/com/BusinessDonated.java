package sp.com;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessDonated#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessDonated extends Fragment {


    RecyclerView recyclerViewDonate;
    EnvironmentHelper myDB;
    ArrayList<String> nameItemDonate, lat_donate, lon_donate;
    CustomAdapterDonate customAdapterDonate;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BusinessDonated() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusinessDonated.
     */
    // TODO: Rename and change types and number of parameters
    public static BusinessDonated newInstance(String param1, String param2) {
        BusinessDonated fragment = new BusinessDonated();
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
        View rootviewD = inflater.inflate(R.layout.business_donated, container, false);

        recyclerViewDonate = rootviewD.findViewById(R.id.recyclerviewdonated);

        //recyclerview
        myDB = new EnvironmentHelper(getActivity());
        nameItemDonate = new ArrayList<>();
        lat_donate = new ArrayList<>();
        lon_donate = new ArrayList<>();

        storeDonateDataInArrays();

        customAdapterDonate = new CustomAdapterDonate(getActivity(), nameItemDonate, lat_donate, lon_donate);
        recyclerViewDonate.setAdapter(customAdapterDonate);
        recyclerViewDonate.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootviewD;
    }

    public void storeDonateDataInArrays() {
        Cursor donateCursor = myDB.getAllDonateBuy();
        if(donateCursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No Data!", Toast.LENGTH_LONG).show();
        } else {
            while(donateCursor.moveToNext()) {
                nameItemDonate.add(donateCursor.getString(1));
                lat_donate.add(donateCursor.getString(2));
                lon_donate.add(donateCursor.getString(3));
            }
        }
    }
}