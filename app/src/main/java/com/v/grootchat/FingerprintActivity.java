package com.v.grootchat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class FingerprintActivity extends AppCompatActivity {

    private TextView mHeadingLabels, mparalabel;
    private ImageView mfingerprintImages;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    private KeyStore keyStore;
    private Cipher cipher;
    private String KEY_NAME = "AndroidKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        mHeadingLabels = findViewById(R.id.BiometricHeading);
        mfingerprintImages = findViewById(R.id.Biometric);
        mparalabel = findViewById(R.id.Label);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                mparalabel.setText("Fingerprint Scanner Not Detected In This Device");
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {

                mparalabel.setText("Permission Is Not Granted For Fingerprint Scanner");
            } else if (!keyguardManager.isKeyguardSecure()) {
                mparalabel.setText("Enable Fingerprint Authentication FOr Your Mobile In Settings");
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                mparalabel.setText("You Should Atleast Register One / Two Fingerprints");
            } else {
                mparalabel.setText("Place Your Finger On The Scanner");
                generateKey();
                if (cipherinit()) {
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    com.v.grootchat.FingerprintHandler fingerprintHandler = new com.v.grootchat.FingerprintHandler(this);
                    fingerprintHandler.startAuth(fingerprintManager,cryptoObject);
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder
                    (KEY_NAME, KeyProperties.PURPOSE_ENCRYPT
                            | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());

            keyGenerator.generateKey();

        }catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchProviderException e){
            e.printStackTrace();
        }
    }

    public boolean cipherinit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {

            throw new RuntimeException("Failed To Get Cipher", e);

        }
        try {
            keyStore.load(null);

            SecretKey secretKey = (SecretKey)keyStore.getKey(KEY_NAME,null);
            cipher.init(Cipher.ENCRYPT_MODE,secretKey);
            return true;
        }catch (KeyPermanentlyInvalidatedException e){
            return  false;

        }catch (KeyStoreException|CertificateException| UnrecoverableKeyException |IOException | NoSuchAlgorithmException | InvalidKeyException e){
            throw  new RuntimeException("Failed To init Cipher", e);

        }
    }
}
