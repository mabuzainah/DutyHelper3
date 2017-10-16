package com.example.mohammedabu.dutyhelper.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mohammedabu.dutyhelper.MainActivity;
import com.example.mohammedabu.dutyhelper.R;

public class Login extends AppCompatActivity{

    private static final String TAG = "LoginScreen";
    private EditText userEmail;
    private EditText userPassword;
    private  Button tvLogin;
    private TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userEmail = (EditText)findViewById(R.id.userEmail);
        userPassword = (EditText)findViewById(R.id.userPassword);
        tvLogin = (Button)findViewById(R.id.tvLogin);
        registerLink = (TextView)findViewById(R.id.tv_SignUp);

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(Login.this, RegisterActivity.class);
                Login.this.startActivity(registerIntent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login();
                Intent loginIntent = new Intent(Login.this, MainActivity.class);
                Login.this.startActivity(loginIntent);
            }
        });
    }

    public void login(){
        Log.d(TAG, "Signup");

        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait while we log you in...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
}
