package com.example.kunal_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class screen2 extends BaseActivity {

    AppCompatEditText emailbox,passwordbox;
    AppCompatButton loginbtn,signup_btn;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        removeStatusBarWithWhiteIcon();
        dialog=new ProgressDialog(this);
        dialog.setMessage("please wait.....");

        firebaseAuth=FirebaseAuth.getInstance();
        emailbox=findViewById(R.id.emailaddress);
        passwordbox=findViewById(R.id.password);

        loginbtn=findViewById(R.id.loginbtn);
        signup_btn=findViewById(R.id.signup_btn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email= Objects.requireNonNull(emailbox.getText()).toString();
                password= Objects.requireNonNull(passwordbox.getText()).toString();
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                           // startActivity(new Intent());
                            Toast.makeText(screen2.this,"Logging in",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(screen2.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        signup_btn.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                startActivity(new Intent(screen2.this,sign_up.class));


            }
        });

    }
}