package dhanush.com.firestoreapp;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dhanush.com.firestoreapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    //AIzaSyD-8Ls12oUwxXaKAngTpHmgYPQejK1b8Ao
    private GoogleMap mMap;


    FrameLayout map;
    public static final int REQUEST_CODE =101;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map = findViewById(R.id.map);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(12.9249, 80.1000);
        mMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in tambaram"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        LatLng mech1 = new LatLng(12.9451, 80.1296);
        mMap.addMarker(new MarkerOptions().position(mech1)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mech))
                .title("Sri balaji mechshop"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mech1));

        LatLng mech2 = new LatLng(12.9651, 80.1376);
        mMap.addMarker(new MarkerOptions().position(mech2)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mech))
                .title("vijay mechshop"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mech2));


        LatLng mech3 = new LatLng(12.9851, 80.0296);
        mMap.addMarker(new MarkerOptions().position(mech3)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mech))
                .title("bharat bunk"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mech3));

    }
}