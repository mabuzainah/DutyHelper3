package com.example.mohammedabu.dutyhelper.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mohammedabu.dutyhelper.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private AppCompatButton btnSignup;
    private TextView loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d(TAG, "onCreate started");

        btnSignup = (AppCompatButton) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                signup();
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




    }


    public void signup(){
        Log.d(TAG, "Signup");

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

}


