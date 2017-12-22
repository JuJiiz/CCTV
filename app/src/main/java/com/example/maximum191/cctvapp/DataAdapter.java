package com.example.maximum191.cctvapp;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Maximum191 on 16/12/2560.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<Data_CCTV> list;

    public DataAdapter(List<Data_CCTV> list) {
        this.list = list;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false));

    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, int position) {
        Data_CCTV data = list.get(position);

        holder.textName.setText(data.dataName);
        holder.textCCTV.setText(data.dataType);
        holder.textAddress.setText(data.dataAddress);

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(),0,0,"Name");
                menu.add(holder.getAdapterPosition(),1,0,"Address");
            }
        });
    }



    @Override
    public int getItemCount() {
        return 0;
    }

    class  DataViewHolder extends RecyclerView.ViewHolder{

        TextView textCCTV,textName,textAddress;
        public DataViewHolder(View itemView) {
            super(itemView);

            textCCTV = (TextView)itemView.findViewById(R.id.textViewCCTV);
            textName =(TextView)itemView.findViewById(R.id.textViewName);
            textAddress = (TextView)itemView.findViewById(R.id.textviewAddress);
        }
    }
}
