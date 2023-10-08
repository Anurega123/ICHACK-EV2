package dhanush.com.firestoreapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView tv;
    EditText email,pass;

    FirebaseAuth firebaseAuth;
    AppCompatButton btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginemail);
        pass = findViewById(R.id.loginpass);
        btn = findViewById(R.id.loginbtn);
        firebaseAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String k1 = email.getText().toString();
                String k2 = pass.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(k1,k2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            try {
                                if(k1.contains(".amb")){
                                    Intent i = new Intent(login.this, MainActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Intent i = new Intent(login.this, homepage.class);
                                    startActivity(i);
                                }

                            }
                            catch (Exception e){
                                Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(login.this,"error in your login acc",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        tv = findViewById(R.id.logintoreg);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,registration.class);
                startActivity(i);
            }
        });
    }
}