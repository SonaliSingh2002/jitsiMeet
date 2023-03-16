package com.example.kunal_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class sign_up extends BaseActivity {
FirebaseFirestore database;
FirebaseAuth auth;
ProgressDialog dilog;

    EditText emailbox,passwordbox,namebox;
    Button loginbtn,signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        removeStatusBarWithWhiteIcon();
        dilog=new ProgressDialog(this);
        dilog.setMessage("Please Wait....");

        database=FirebaseFirestore.getInstance();

    auth=FirebaseAuth.getInstance();


        emailbox=findViewById(R.id.emailaddress);
        passwordbox=findViewById(R.id.password);
     namebox=findViewById(R.id.namebox);

        loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),screen2.class);
            }
        });
        signup_btn=findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilog.show();
                String email,pass,name;
                email=emailbox.getText().toString();
                pass=passwordbox.getText().toString();
                name=namebox.getText().toString();



                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dilog.dismiss();
                        if(task.isSuccessful()){
                            startActivity(new Intent(sign_up.this, DashboardActivity.class));
                          }
                        else {
                            Toast.makeText(sign_up.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}