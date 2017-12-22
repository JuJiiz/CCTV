package com.example.maximum191.cctvapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ActivityUpdate extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Data_CCTV> result;
    private DataAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("data_CCtv");

        result = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.data_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(llm);



        adapter = new DataAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 0:
                break;
            case 1:
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void updateList(){
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
                result.set(index,model);

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
    private  int getItemIndex(Data_CCTV data_CCtv){
        int index = -1;
        for (int i=0;i<result.size();i++){
            if (result.get(i).dataId.equals(data_CCtv.dataId)){
                index = i;
                break;
            }
        }

        return index;
    }
}


