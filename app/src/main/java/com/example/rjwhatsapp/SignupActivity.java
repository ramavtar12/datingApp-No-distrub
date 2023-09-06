package com.example.rjwhatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rjwhatsapp.Models.user;
import com.example.rjwhatsapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();



       auth = FirebaseAuth.getInstance();
       database = FirebaseDatabase.getInstance();

       progressDialog = new ProgressDialog(SignupActivity.this);
       progressDialog.setTitle("Creating Account");
       progressDialog.setMessage("We are Creating Your Account");

       if(auth.getCurrentUser() != null)
        {
            startActivity(new Intent(SignupActivity.this , MainActivity.class));
        }


       binding.btnSignup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               progressDialog.show();
               auth.createUserWithEmailAndPassword
                       (binding.etemail.getText().toString(), binding.etpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                       if(task.isSuccessful()){
                           user users = new user(
                                   binding.etusername.getText().toString() , binding.etemail.getText().toString() ,
                                   binding.etpassword.getText().toString()
                           );
                           String id = task.getResult().getUser().getUid();
                           database.getReference().child("Users").child(id).setValue(users);
                           Toast.makeText(SignupActivity.this, "user created successfully", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }

                   }
               });

           }
       });


        binding.tvalreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignupActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}
