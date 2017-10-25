package com.example.mohammedabu.dutyhelper.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private AppCompatButton btnSignup;
    private TextView loginLink;

    public ProgressDialog mProgressDialog;

    @Bind(R.id.input_name) EditText name;
    @Bind(R.id.input_address) EditText address;
    @Bind(R.id.input_email) EditText email;
    @Bind(R.id.input_mobile) EditText number;
    @Bind(R.id.input_password) EditText password;
    @Bind(R.id.input_reEnterPassword) EditText reEnterPassword;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d(TAG, "onCreate started");

        mAuth = FirebaseAuth.getInstance();
        name= (EditText)findViewById(R.id.input_name);
        email= (EditText)findViewById(R.id.input_email);
        address= (EditText)findViewById(R.id.input_address);
        password= (EditText)findViewById(R.id.input_password);
        number= (EditText)findViewById(R.id.input_mobile);

        btnSignup = (AppCompatButton) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createAccount(email.getText().toString(), password.getText().toString());
                Intent registerIntent = new Intent(RegisterActivity.this, Login.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });

        loginLink = (TextView)findViewById(R.id.link_login);
        loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, Login.class);
                RegisterActivity.this.startActivity(registerIntent);
                finish();
            }
        });

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Unable to create account.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String Name = name.getText().toString();
        String Address = address.getText().toString();
        String Email = email.getText().toString();
        String mobile = number.getText().toString();
        String rePassword = reEnterPassword.getText().toString();
        String Password = password.getText().toString();


        if (TextUtils.isEmpty(Name) || name.length() < 3) {
            name.setError("at least 3 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (TextUtils.isEmpty(Address)) {
            address.setError("Enter Valid Address");
            valid = false;
        } else {
            address.setError(null);
        }


        if (TextUtils.isEmpty(Email)) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (TextUtils.isEmpty(mobile) || mobile.length()!=10) {
            number.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            number.setError(null);
        }

        if (TextUtils.isEmpty(Password) || password.length() < 4 || password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        if (TextUtils.isEmpty(rePassword) || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            reEnterPassword.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPassword.setError(null);
        }

        return valid;
    }



    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }



}


