package com.example.maximum191.cctvapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private final static int MY_PERMISSION_FINE_LOCATION = 200;
    double lat = 16.430009;
    double lng = 102.829681;
    private DatabaseReference mdata;//Firebase
    Marker marker;//option Marker of database
    ArrayList<HashMap<String, String>> alMultiLocation = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> hmLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mdata = FirebaseDatabase.getInstance().getReference("dataCCtv");
        mdata.push().setValue(marker);

        GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
        final int status = gAPI.isGooglePlayServicesAvailable(MapsActivity.this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if (status == ConnectionResult.SUCCESS) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        Button cmdClear = (Button) findViewById(R.id.cmdClear);
        cmdClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mMap.clear();
            }
        });


        Button btnSreach = (Button) findViewById(R.id.btnSreach);
        btnSreach.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText etSreach = (EditText) findViewById(R.id.etdsreachlocat);
                String q = etSreach.getText().toString().trim();

                if (q != null && !q.equals("")) {
                    Geocoder geo = new Geocoder(MapsActivity.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geo.getFromLocationName(q, 5);
                        if (addressList.size() > 0) {
                            mMap.clear();

                            Address add = null;
                            LatLng CurrentAddress = null;
                            for (int i = 0; i < addressList.size(); i++) {
                                add = (Address) addressList.get(i);
                                CurrentAddress = new LatLng(add.getLatitude(), add.getLongitude());

                                String str = "";
                                for (int j = 0; j < add.getMaxAddressLineIndex(); j++) {
                                    str = str + add.getAddressLine(j) + "\n";
                                }
                                Marker m = mMap.addMarker(new MarkerOptions().position(CurrentAddress)
                                        .title(add.getAddressLine(0) + "(Lat: " + add.getLatitude() + "Long: " + add.getLongitude() + ")")
                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)).snippet(str));
                            }
                            CameraPosition cam = new CameraPosition.Builder()
                                    .target(CurrentAddress).zoom(5).build();
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cam));
                        } else {
                            Toast.makeText(getBaseContext(), "ไม่พบที่อยู่ตามที่คุณระบุ!!", Toast.LENGTH_LONG).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng jj = new LatLng(lat, lng);
        if (mMap != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mMap.getUiSettings().isMyLocationButtonEnabled();
                mMap.setMyLocationEnabled(true);
            }
            mMap.getUiSettings().setZoomControlsEnabled(true);

        }
        //Get Lat Lng From Firedatabase
        googleMap.setOnMarkerClickListener(this);
        mdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()){
                    Data_CCTV Mydata = s.getValue(Data_CCTV.class);
                    LatLng location = new LatLng(Mydata.dataLat,Mydata.dataLng);
                    mMap.addMarker(new MarkerOptions().position(location).title(Mydata.dataName)).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mMap.addMarker(new MarkerOptions().position(jj).title("ขอนแก่น"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jj, 12));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                String strAddress = "Lat: " + String.valueOf(latLng.latitude) + " " + "Lng: " + String.valueOf(latLng.longitude);
                MarkerOptions m = new MarkerOptions();
                m.position(latLng);
                m.title(strAddress);
                mMap.addMarker(m);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.mnuNone:
                if (mMap != null) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                    return true;
                }
            case R.id.mnuNormal:
                if (mMap != null) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    return true;
                }
            case R.id.mnuSatellite:
                if (mMap != null) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    return true;
                }
            case R.id.mnuTerrain:
                if (mMap != null) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    return true;
                }
            case R.id.mnuHybrid:
                if (mMap != null) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    return true;
                }

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
