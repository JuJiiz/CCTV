package com.example.maximum191.cctvapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityHome extends AppCompatActivity {
    public Button btn_Add, btn_Out, btn_Edit, btnuser,btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        OnAddP();
        OnEditP();
        OnLogOutP();
        OnSearchP();
        OnUser();
    }

    public  void OnUser(){
        btnuser = (Button)findViewById(R.id.btnuser);
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUser = new Intent(ActivityHome.this,ActivityProfile.class);
                startActivity(intentUser);
            }
        });
    }

    public void OnLogOutP() {
        btn_Out = (Button) findViewById(R.id.btnout);
        btn_Out.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentut = new Intent(ActivityHome.this,MainActivityLogin.class);
                        startActivity(intentut);
                    }
                });
    }

    public void OnSearchP() {
    btn_search = (Button)findViewById(R.id.btnsea);
     btn_search.setOnClickListener(
        new View.OnClickListener() {
           @Override
          public void onClick(View view) {
             Intent intentsea = new Intent(ActivityHome.this,MapsActivity.class);
             startActivity(intentsea);
          }
      });
     }

    public void OnEditP() {
        btn_Edit = (Button) findViewById(R.id.btnEdit);
        btn_Edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentedit = new Intent(ActivityHome.this, ActivityUpdate.class);
                        startActivity(intentedit);
                    }
                });
    }

    public void OnAddP() {
        btn_Add = (Button) findViewById(R.id.btn_add);
        btn_Add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentadd = new Intent(ActivityHome.this, Activity_Add.class);
                        startActivity(intentadd);
                    }
                });
    }
}

