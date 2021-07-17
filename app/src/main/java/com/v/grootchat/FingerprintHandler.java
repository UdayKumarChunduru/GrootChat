package com.v.grootchat;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FingerprintHandler(Context context){
        this.context=context;
    }
    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);

        Toast.makeText(context, "Authentication Error", Toast.LENGTH_SHORT).show();

        this.update("Unable To Verify Your Fingerprint" +errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Mismatch" , false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error(:" +helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Authentication Successful", false);
    }


    private void update(String s, boolean b) {
        TextView Label = (TextView) ((Activity)context).findViewById(R.id.Label);
        ImageView Biometric = (ImageView) ((Activity)context).findViewById(R.id.Biometric);
        Label.setText(s);

        if (b=false){
            Label.setTextColor(ContextCompat.getColor(context, R.color.teal_200));
        }else {
            Label.setTextColor(ContextCompat.getColor(context, R.color.purple_700));
            Biometric.setImageResource(R.drawable.checkdone);
        }
    }
}