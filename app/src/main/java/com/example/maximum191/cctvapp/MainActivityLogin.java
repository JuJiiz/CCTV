package com.example.maximum191.cctvapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityLogin extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText input_email, input_password;
    TextView textViewForget,textViewSignup;
    RelativeLayout layout_login;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnLogin = (Button)findViewById(R.id.btnLogi);
        input_email = (EditText)findViewById(R.id.user);
        input_password = (EditText)findViewById(R.id.password);
        textViewForget = (TextView)findViewById(R.id.login_btn_forgot_password);
        textViewSignup = (TextView)findViewById(R.id.btn_SignUp);
        layout_login = (RelativeLayout)findViewById(R.id.layout_login);

        btnLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewForget.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivityLogin.this, ActivityHome.class));
            Log.d("MYLOG", "AUTH NOT NULL");
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn_forgot_password)
        {
          startActivity(new Intent(MainActivityLogin.this, ForgetPassword.class));
            finish();
        }
        else if (view.getId() == R.id.btn_SignUp)
        {
            startActivity(new Intent(MainActivityLogin.this, SignUp.class));
            finish();
        }else if (view.getId() == R.id.btnLogi)
        {
            loginUser(input_email.getText().toString(),input_password.getText().toString());
            Log.d("MYLOG", "CLICK!!!");
        }
    }

    private void loginUser(String email, final String password) {
        Log.d("MYLOG", "INTRO LOGINUSER");
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            if (password.length()<6)
                            {
                                Snackbar snackbar = Snackbar.make(layout_login,"Password length must br over 6",Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }else {
                                Log.d("MYLOG", "WHAT!!!");
                            }
                        }
                        else {
                            startActivity(new Intent(MainActivityLogin.this,ActivityHome.class));
                            Log.d("MYLOG", "LETS GO!!!");
                        }
                    }
                });
    }
}
