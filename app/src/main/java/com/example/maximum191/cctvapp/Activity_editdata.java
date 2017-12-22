package com.example.maximum191.cctvapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Activity_editdata extends AppCompatActivity {
    EditText nameedit,owneredit,numedit,addressedit;
    Spinner typeedit;
    Button btnokedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        nameedit= (EditText)findViewById(R.id.name_edit);
        owneredit = (EditText)findViewById(R.id.owner_edit);
        numedit = (EditText)findViewById(R.id.num_edit);
        addressedit = (EditText)findViewById(R.id.address_edit);
        typeedit =(Spinner)findViewById(R.id.Type_edit);
        btnokedit = (Button)findViewById(R.id.ok_edit);

        btnokedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
