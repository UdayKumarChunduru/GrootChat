package com.v.grootchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.v.grootchat.Models.Users;
import com.v.grootchat.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    EditText name1, email1, password1;
    ImageView showPass1;

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        name1 = findViewById(R.id.name1);
        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.password1);

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        if (!isConnected()){
            Toast.makeText(this, "Internet Is Turned Off", Toast.LENGTH_SHORT).show();
        }

        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Groot Chat");
        progressDialog.setMessage("Creating Your Groot Account...");
        progressDialog.setIcon(R.drawable.whatsapplogo);
        progressDialog.setButton(ProgressDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setCancelable(false);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email1.getText().toString().trim();
                String name = name1.getText().toString();
                String password = password1.getText().toString();
                if (!isConnected()){
                    Snackbar.make(SignUpActivity.this,v, "Internet Is Turned Off",Snackbar.LENGTH_LONG);
                }
                if (name.isEmpty()|email.isEmpty()|password.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Fill All Required Fields", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.show();
                    auth.createUserWithEmailAndPassword
                            (binding.email1.getText().toString(), binding.password1.getText().toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        Users user = new Users(binding.name1.getText().toString(), binding.email1.getText().toString(),
                                                binding.password1.getText().toString());

                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(user);

                                        Toast.makeText(SignUpActivity.this, "User Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
        binding.alrlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this , SignInActivity.class);
                startActivity(intent);
            }
        });



    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager manager = (ConnectivityManager)
                    getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            connected = (info != null) && (info.isAvailable()) && (info.isConnected());
            return connected;
        } catch (Exception e) {

        }
        return connected;
    }

    public void ShowHidePass(View view) {
        if(view.getId()==R.id.showPass1){
            if(password1.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.passinvisible);
                //Show Password
                password1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.passvisible);
                //Hide Password
                password1.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}