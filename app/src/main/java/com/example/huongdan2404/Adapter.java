package com.example.huongdan2404;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Student> data;
    private ArrayList<Student> databackup;
    private LayoutInflater inflater;

    public Adapter(Activity activity, ArrayList<Student> data) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = inflater.inflate(R.layout.activity_details,null);
            TextView name = v.findViewById(R.id.txtName);
            name.setText(data.get(position).getName());
            TextView sbd = v.findViewById(R.id.txtSBD);
            sbd.setText(data.get(position).getSbd());
            TextView sum = v.findViewById(R.id.txtSum);
            sum.setText(String.format("%.2f", data.get(position).getSum()));
        }
        return v;
    }
}
