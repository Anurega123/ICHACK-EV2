package dhanush.com.firestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.Manifest;


public class homepage extends AppCompatActivity {
    private static final int REQUEST_CALL_PERMISSION = 1;

    LinearLayout layout,sos1,ss1,ss2;
    ImageView location;
    AppCompatImageButton btn1;
    ImageView iv;
    FirebaseAuth auth;
    ImageView logout;
    String k,o;
    DatabaseReference databaseReference;
    MainActivity m = new MainActivity();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        //firebaseDatabase = FirebaseDatabase.getInstance().getReference("users");
        auth = FirebaseAuth.getInstance();


        String i = auth.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(i);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                k = snapshot.child("location").getValue().toString();
                o = snapshot.child("number").getValue().toString();
                //Toast.makeText((Context) homepage.this, k,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        layout = findViewById(R.id.soss);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homepage.this,"will do soon",Toast.LENGTH_LONG).show();
            }
        });






        sos1 = findViewById(R.id.soss);
        sos1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(homepage.this,R.style.dialogs2);
                dialog.setContentView(R.layout.dialogue2);
                AppCompatButton yes,no;
                dialog.show();
                yes = dialog.findViewById(R.id.diagyes1);
                no =  dialog.findViewById(R.id.diagno1);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(homepage.this,R.style.dialogs);
                        dialog.setContentView(R.layout.dialogue);
                        AppCompatButton yes,no;
                        dialog.show();
                        yes = dialog.findViewById(R.id.diagyes);
                        no =  dialog.findViewById(R.id.diagno);

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // if(k.contains("chennai"))
                                k = "NEED FOR EMGRNCY AMBULANCE IN "+k;
                                sendSMS(o,k);

                            }
                        });

                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(homepage.this,"will soon",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+"8973645887"));
                                startActivity(intent);

                            }
                        });
                        dialog.show();


                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog dialog = new Dialog(homepage.this,R.style.dialogs);
                        dialog.setContentView(R.layout.dialogue);
                        AppCompatButton yes,no;
                        dialog.show();
                        yes = dialog.findViewById(R.id.diagyes);
                        no =  dialog.findViewById(R.id.diagno);

                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // if(k.contains("chennai"))
                                k = "NEED FOR EMGRNCY FIRE-ENGINE IN "+k;
                                sendSMS(o,k);

                            }
                        });

                        no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(homepage.this,"will soon",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:"+"9345823257"));
                                startActivity(intent);

                            }
                        });
                        dialog.show();
                    }
                });
            }
        });

        location = findViewById(R.id.getloactionh);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, MapsActivity2.class);
                startActivity(i);
            }
        });

        logout = findViewById(R.id.logouth);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(homepage.this, login.class);
                startActivity(i);
            }
        });

        ss1 = findViewById(R.id.sss2);
        ss1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, MapsActivity.class);
                startActivity(i);
            }
        });

        ss2 = findViewById(R.id.sss);
        ss2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, MapsActivity2.class);
                startActivity(i);
            }
        });


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
    public void makeCall(View view) {
        // Check if permission to make a phone call is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION);
        } else {
            // Permission already granted, initiate the call
            startCall();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, initiate the call
                startCall();
            }
        }
    }
    private void startCall() {
        // Replace "phoneNumber" with your friend's phone number
        String phoneNumber = "8973645887";
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}