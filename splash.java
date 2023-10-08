package dhanush.com.firestoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null){
            Intent i = new Intent(splash.this, login.class);
            startActivity(i);
        }
        String k = auth.getCurrentUser().getEmail();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(k.contains(".amb")){
                    Intent i =new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Intent i =new Intent(splash.this, homepage.class);
                    startActivity(i);
                }

            }
        },3000);

    }
}