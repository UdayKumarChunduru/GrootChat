package com.v.grootchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    TextInputEditText EnterPhoneNumber;
    ImageView PhoneNextArrow;
    CountryCodePicker ccp;
    FirebaseAuth auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        EnterPhoneNumber = (TextInputEditText) findViewById(R.id.EnterPhoneNumber);
        PhoneNextArrow = (ImageView) findViewById(R.id.PhoneNextArrow);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        PhoneNextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EnterPhoneNumber.getText().toString().trim().isEmpty() | EnterPhoneNumber.length() < 10 | ccp.toString().isEmpty()){
                    Toast.makeText(PhoneActivity.this, "Fill All Required Fields", Toast.LENGTH_SHORT).show();
                    EnterPhoneNumber.setError("Cannot Be Empty.");
                    EnterPhoneNumber.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                PhoneNextArrow.setVisibility(View.VISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ EnterPhoneNumber.getText().toString(),
                        60L,
                        TimeUnit.SECONDS,
                        PhoneActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                PhoneNextArrow.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                PhoneNextArrow.setVisibility(View.VISIBLE);
                                Toast.makeText(PhoneActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                PhoneNextArrow.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(getApplicationContext(), com.v.grootchat.OTPVerificationActivity.class);
                                intent.putExtra("mobile", EnterPhoneNumber.getText().toString());
                                intent.putExtra("verificationId",verificationId);
                                startActivity(intent);
                            }
                        }
                );

            }
        });

    }
}