package com.example.maximum191.cctvapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityUpdate extends AppCompatActivity {
    private ListView recyclerView;
    private List<Data_CCTV> result;
    private DataAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    Data_CCTV Mydata;
    String mName = "name", xName;
    String mAddress = "address",xAddress;
    String mOwner = "owner",xOwner;
    String mType = "type",xType;
    ArrayList<HashMap<String, String>> LIST = new ArrayList<HashMap<String, String>>();;
    HashMap<String, String> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        result = new ArrayList<>();

        recyclerView = (ListView) findViewById(R.id.data_list);
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(llm);

        adapter = new DataAdapter(result);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    Mydata = s.getValue(Data_CCTV.class);
                    if (Mydata.dataName != null && Mydata.dataNumber != null && Mydata.dataOwner != null && Mydata.dataType != null) {
                        temp = new HashMap<String, String>();
                        temp.put(mName, Mydata.dataName);
                        temp.put(mAddress, Mydata.dataNumber);
                        temp.put(mOwner, Mydata.dataOwner);
                        temp.put(mType, Mydata.dataType);

                        LIST.add(temp);
                        Log.d("MYLOG", "LIST: " + LIST);
                    }
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(), LIST, R.layout.inside_listview,
                        new String[]{mName, mAddress, mOwner, mType},
                        new int[]{R.id.tvName, R.id.tvAddress, R.id.tvOwner, R.id.tvType}
                );
                recyclerView.setAdapter(simpleAdapter);

                Log.d("MYLOG", "SUCCESS");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        updateList();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                break;
            case 1:
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void updateList() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(Data_CCTV.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Data_CCTV model = dataSnapshot.getValue(Data_CCTV.class);
                int index = getItemIndex(model);
                result.set(index, model);

                adapter.notifyItemChanged(index);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Data_CCTV model = dataSnapshot.getValue(Data_CCTV.class);
                int index = getItemIndex(model);
                result.remove(index);

                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(Data_CCTV data_CCtv) {
        int index = -1;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).dataId.equals(data_CCtv.dataId)) {
                index = i;
                break;
            }
        }

        return index;
    }
}


