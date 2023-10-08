package dhanush.com.firestoreapp;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    String lat="80.057869";
    String lon="12.09899";

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;

    private static final String CHANNEL_ID = "my_channel_id";
    private static final int NOTIFICATION_ID = 101;

    // private EditText phoneNumberEditText;
    // private EditText messageEditText;
    DatabaseReference databaseReference;
    ImageView btnlogout,location;
    AppCompatButton sendButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        location = findViewById(R.id.getloaction);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fetchLocation();
            }
        });

        btnlogout = findViewById(R.id.logout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {

            Intent i = new Intent(MainActivity.this, login.class);
            startActivity(i);
        }

        //phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        // messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.btn1);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String[] phoneNumber = {""};
                String k = "9942694615";
                //String message = "AMBULANCE ALERT FOR DRIVERS ";model m;
                fetchLocation();
                String message = "AMBULANCE ALERT FOR DRIVERS \n"+ "LATITUDE:"+lat+"\nLONGITUDE:"+lon;

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //  phoneNumber[0] = snapshot.getValue().toString();
                        for (DataSnapshot d : snapshot.getChildren()) {
                            model m = d.getValue(model.class);
                            callsms(m.getNumber(), message);
                            // Toast.makeText(MainActivity.this,m.getNumber(),Toast.LENGTH_LONG).show();

                        }
                        //String k = phoneNumber[0].toString();
                        // callsms(k,message);
                        //sendButton.setText(phoneNumb);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }

    public void callsms(String ph, String msg) {
        if (!ph.isEmpty() && !msg.isEmpty()) {
            sendSMS(ph, msg);
            showNotification("SMS Sent", "Message sent successfully.");
        } else {
            Toast.makeText(MainActivity.this, ph, Toast.LENGTH_SHORT).show();
        }
    }

    void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.addcamera) // Specify the small icon here


                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "LOCATION WILL BE FETCHED", Toast.LENGTH_LONG).show();
            fetchLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            Toast.makeText(MainActivity.this, "LOCATION WILL BE FETCHED PERMISSIONS ARE GRANTED", Toast.LENGTH_LONG).show();


            fetchLocation();
        }
    }

    // @SuppressLint("MissingPermission")
    void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            lat = String.valueOf(location.getLatitude());
                            lon  = String.valueOf(location.getLongitude());

                            //getlat(String.valueOf(latitude));
                           // getlong(String.valueOf(longitude));




                            // Store the latitude and longitude in your database here
                            // You can use SQLite, Room, or any other database library

                            Toast.makeText(MainActivity.this,
                                    "Latitude: " + lat + "\nLongitude: " + lon, Toast.LENGTH_SHORT).show();
                            //return "latitude: " + latitude + "\nLongitude: " + longitude;
                        } else {
                            Toast.makeText(MainActivity.this, "Location not available",
                                    Toast.LENGTH_SHORT).show();
                        }
                        ///return null;
                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getlat(String l){
        //String o;
        this.lat = l;
    }
    public void getlong(String l){
        String o;
        this.lon = l;
    }
}
