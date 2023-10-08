package dhanush.com.firestoreapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dhanush.com.firestoreapp.databinding.ActivityMaps2Binding;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {
    FrameLayout map;
    private GoogleMap mMap;
    private ActivityMaps2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        map = findViewById(R.id.map);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
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
        LatLng sydney = new LatLng(12.9451, 80.1296);
        mMap.addMarker(new MarkerOptions().position(sydney)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bunk))
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng sydney2 = new LatLng(12.9051, 80.1590);
        mMap.addMarker(new MarkerOptions().position(sydney2)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bunk))
                .title("BHARAT PERTOL BUNK"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));

        LatLng sydney3 = new LatLng(12.7051, 80.0296);
        mMap.addMarker(new MarkerOptions().position(sydney3)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bunk))
                .title("INDIAN BUNK"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney3));

        LatLng sydney4 = new LatLng(12.8408, 80.2396);
        mMap.addMarker(new MarkerOptions().position(sydney4)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bunk))
                .title("RELIENCE BUNK"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney4));
    }
}