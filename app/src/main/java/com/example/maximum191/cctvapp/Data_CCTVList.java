package com.example.maximum191.cctvapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Maximum191 on 13/12/2560.
 */

public class Data_CCTVList extends ArrayAdapter<Data_CCTV> {

    private Activity context;
    private List<Data_CCTV>Data_cctvList;



    public  Data_CCTVList(Activity context,List<Data_CCTV> Data_cctvList){
        super(context,R.layout.list_layout,Data_cctvList);
        this.context =context;
        this.Data_cctvList = Data_cctvList;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView TextViewAddress = listViewItem.findViewById(R.id.textviewAddress);



        Data_CCTV dataCctv = Data_cctvList.get(position);

        textViewName.setText(dataCctv.getDataName());
        TextViewAddress.setText(dataCctv.getDataAddress());

        return listViewItem;
    }
}
