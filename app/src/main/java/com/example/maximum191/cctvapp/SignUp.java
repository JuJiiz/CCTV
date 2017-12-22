package com.example.maximum191.cctvapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener  {
    Button btnsignup;
    EditText inputmailSignup, inputpasswordSignup;
    TextView loginme , forgetme;
    RelativeLayout activity_signup;
    private FirebaseAuth auth;
    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsignup = (Button)findViewById(R.id.btnsign_up);
        inputmailSignup = (EditText)findViewById(R.id.signup_email);
        inputpasswordSignup = (EditText)findViewById(R.id.signup_password);
        forgetme = (TextView)findViewById(R.id.signup_btn_forgot_password);
        loginme = (TextView)findViewById(R.id.btn_loginMe);
        activity_signup = (RelativeLayout)findViewById(R.id.activity_signup);

        btnsignup.setOnClickListener(this);
        forgetme.setOnClickListener(this);
        loginme.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.signup_btn_forgot_password){
            startActivity(new Intent(SignUp.this, ForgetPassword.class));
            finish();
        }
        else if (view.getId()== R.id.btn_loginMe){
            startActivity(new Intent(SignUp.this, MainActivityLogin.class));
            finish();
        }
        else if (view.getId()== R.id.btnsign_up){
            signupUser(inputmailSignup.getText().toString(),inputpasswordSignup.getText().toString());
        }
    }

    private void signupUser(String email, String pass) {
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            snackbar = Snackbar.make(activity_signup,"Error:"+task.getException(),Snackbar.LENGTH_SHORT);
                            snackbar.show();

                        }
                        else {
                            snackbar = Snackbar.make(activity_signup,"Register: ",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }
                });
    }
}
