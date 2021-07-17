package com.v.grootchat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.v.grootchat.databinding.ActivityOtpverificationBinding;
import com.v.grootchat.databinding.ActivitySignUpBinding;

import java.util.concurrent.TimeUnit;

public class OTPVerificationActivity extends AppCompatActivity {

    ActivityOtpverificationBinding binding;
    FirebaseDatabase database;

    TextInputEditText EnterPhoneNumber;
    EditText OTP1, OTP2, OTP3, OTP4, OTP5, OTP6;
    ImageView PhoneNextArrow;
    Button VerifyOTP;
    ProgressBar progressBar1;
    CountryCodePicker ccp;
    FirebaseAuth auth;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpverificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_otpverification);

        EnterPhoneNumber = (TextInputEditText) findViewById(R.id.EnterPhoneNumber);
        PhoneNextArrow = (ImageView) findViewById(R.id.PhoneNextArrow);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        auth.useAppLanguage();

        TextView textMobile = findViewById(R.id.textMobile);
        textMobile.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        OTP1 = (EditText) findViewById(R.id.OTP1);
        OTP2 = (EditText) findViewById(R.id.OTP2);
        OTP3 = (EditText) findViewById(R.id.OTP3);
        OTP4 = (EditText) findViewById(R.id.OTP4);
        OTP5 = (EditText) findViewById(R.id.OTP5);
        OTP6 = (EditText) findViewById(R.id.OTP6);

        setupOTPInputs();

        VerifyOTP = (Button) findViewById(R.id.VerifyOTP);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);

        verificationId = getIntent().getStringExtra("verificationId");

        VerifyOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OTP1.getText().toString().trim().isEmpty()
                || OTP2.getText().toString().trim().isEmpty()
                || OTP3.getText().toString().trim().isEmpty()
                || OTP4.getText().toString().trim().isEmpty()
                || OTP5.getText().toString().trim().isEmpty()
                || OTP6.getText().toString().trim().isEmpty()){
                    Toast.makeText(OTPVerificationActivity.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code =
                        OTP1.getText().toString() +
                                OTP2.getText().toString() +
                                OTP3.getText().toString() +
                                OTP4.getText().toString() +
                                OTP5.getText().toString() +
                                OTP6.getText().toString();

                if (verificationId != null){
                    progressBar1.setVisibility(View.VISIBLE);
                    VerifyOTP.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar1.setVisibility(View.GONE);
                                    VerifyOTP.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);

                                    }else {
                                        Toast.makeText(OTPVerificationActivity.this, "Entered Verification Code Was Invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });
        findViewById(R.id.textResendOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ getIntent().getStringExtra("mobile"),
                        60L,
                        TimeUnit.SECONDS,
                        OTPVerificationActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OTPVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = newVerificationId;
                                Toast.makeText(OTPVerificationActivity.this, "OTP Sent Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        );
            }
        });
    }

    private void setupOTPInputs() {
        OTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    OTP2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    OTP3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    OTP4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    OTP5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        OTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    OTP6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}