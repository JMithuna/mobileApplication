package sp.com;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import sp.com.databinding.ActivityEnvironmentMapBinding;

public class EnvironmentMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityEnvironmentMapBinding binding;

    private double lat;
    private double lon;

    private String mallName;
    private LatLng MALL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEnvironmentMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        lat = getIntent().getDoubleExtra("LATITUDE", 0);
        lon = getIntent().getDoubleExtra("LONGITUDE", 0);
        //mallName = getIntent().getStringExtra("NAME");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.environment_map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        MALL = new LatLng(lat, lon);

        // Add a marker in Sydney and move the camera
        Marker shop = mMap.addMarker(new MarkerOptions().position(MALL));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MALL, 15));
    }
}