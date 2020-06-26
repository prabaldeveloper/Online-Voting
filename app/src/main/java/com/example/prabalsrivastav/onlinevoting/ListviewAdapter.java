package com.example.prabalsrivastav.onlinevoting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prabal Srivastav on 20-06-2020.
 */

public class ListviewAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ListviewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler {
        TextView Name ,Party ,City ,Vote;
    }
    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;
        if(row==null) {
            LayoutInflater layoutInflater =(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.customize_sqlite_data,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.Name = (TextView) row.findViewById(R.id.textView1);
            layoutHandler.Party = (TextView) row.findViewById(R.id.textView2);
            layoutHandler.City = (TextView) row.findViewById(R.id.textView3);
            layoutHandler.Vote = (TextView) row.findViewById(R.id.textView4);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }
        sqliteData sqlitedata = (sqliteData) this.getItem(position);
        layoutHandler.Name.setText(sqlitedata.getName());
        layoutHandler.Party.setText(sqlitedata.getParty());
        layoutHandler.City.setText(sqlitedata.getCity());
        layoutHandler.Vote.setText(sqlitedata.getVote());

        return row;

    }
}
