package com.example.maximum191.cctvapp;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_Add extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    EditText nameadd, numtype, Ownertype, TxvAddress;
    EditText latt, lngg;
    Spinner type;
    Button btnok;
    DatabaseReference databaseCCTV;
    GoogleApiClient ggAPIClient;
    Location CurrentLocation;
    private static final int PERMISSON_REQUEST_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add);
        nameadd = (EditText) findViewById(R.id.name_upet);
        type = (Spinner) findViewById(R.id.Type_upt);
        numtype = (EditText) findViewById(R.id.num_upet);
        Ownertype = (EditText) findViewById(R.id.owner_upet);
        TxvAddress = (EditText) findViewById(R.id.address_upet);
        latt = (EditText) findViewById(R.id.latti);
        lngg = (EditText) findViewById(R.id.lgti);
        btnok = (Button) findViewById(R.id.ok_add);
        databaseCCTV = FirebaseDatabase.getInstance().getReference("data_cctvs");

        ggAPIClient = new GoogleApiClient.Builder(Activity_Add.this)
                .addApi(LocationServices.API).addConnectionCallbacks(Activity_Add.this)
                .addOnConnectionFailedListener(Activity_Add.this).build();

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDataCCTV();
            }
        });

    }

    @Override
    protected void onStart() {
        ggAPIClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if(ggAPIClient.isConnected()){
            ggAPIClient.disconnect();
        }
        super.onStop();
    }

    private void AddDataCCTV() {
        String name = nameadd.getText().toString().trim();
        String num = numtype.getText().toString().trim();
        String Owner = Ownertype.getText().toString().trim();
        String Address = TxvAddress.getText().toString().trim();
        double lat = Double.parseDouble(latt.getText().toString().trim());
        double lng = Double.parseDouble(lngg.getText().toString().trim());
        String typec = type.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)) {
            String id = databaseCCTV.push().getKey();
            Data_CCTV data_cctvs = new Data_CCTV(id, name, num, Owner, Address, lat, lng, typec);
            databaseCCTV.child(id).setValue(data_cctvs);
            Toast.makeText(this, "Complete", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        GetCurrentLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSON_REQUEST_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(Activity_Add.this, "Add Complete",Toast.LENGTH_LONG).show();

                    GetCurrentLocation();
                }else {
                    Toast.makeText(Activity_Add.this,"Error",Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void GetCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            CurrentLocation = LocationServices.FusedLocationApi.getLastLocation(ggAPIClient);
            if (CurrentLocation != null){
                String strLat = " "+String.valueOf(CurrentLocation.getLatitude());
                String strLng = " "+String.valueOf(CurrentLocation.getLongitude());
                latt.setText(strLat);
                lngg.setText(strLng);
            }else {
                Toast.makeText(Activity_Add.this,"Error Location",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        ggAPIClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}


