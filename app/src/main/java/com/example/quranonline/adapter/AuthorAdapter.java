package com.example.quranonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quranonline.R;
import com.example.quranonline.data.local.AuthorClass;

import java.util.ArrayList;

public class AuthorAdapter extends BaseAdapter {
    ArrayList<AuthorClass> list;
    Context context;

    public AuthorAdapter(ArrayList<AuthorClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.authoritem , null);
        TextView name = (TextView) view.findViewById(R.id.tvauthorname);
        name.setText(list.get(position).server);
        return view;
    }
}
