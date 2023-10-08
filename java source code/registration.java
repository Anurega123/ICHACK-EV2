package dhanush.com.firestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class registration extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    EditText email,pass,phone,loca;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        phone = findViewById(R.id.phno);
        loca = findViewById(R.id.LOCA);
        btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                String phone1 = phone.getText().toString();
                String LOCA1 = loca.getText().toString();
                if(!(email1.contains("@gmail.com"))) {
                    Toast.makeText(registration.this,"Enter the valid email",Toast.LENGTH_LONG).show();
                }
                else if(pass1.length() < 6){
                    Toast.makeText(registration.this,"password character must be greater than 6",Toast.LENGTH_LONG).show();
                }
                else{
                        firebaseAuth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                String i = task.getResult().getUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("users").child(i);

                                if(task.isSuccessful()){
                                    model m = new model(phone1,LOCA1);
                                    databaseReference.setValue(m);
                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String k = snapshot.child("location").getValue().toString();
                                            Toast.makeText((Context) registration.this, k,Toast.LENGTH_LONG).show();
                                            if(email1.contains(".amb")){
                                                Intent in = new Intent(registration.this,MainActivity.class);
                                                startActivity(in);
                                            }
                                            else {
                                                Intent in = new Intent(registration.this, homepage.class);

                                                 startActivity(in);
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                    //Toast.makeText(registration.this,)

                                   //
                                }
                                else{
                                    Toast.makeText(registration.this,"error in auth",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                }
            }
        });

    }
}