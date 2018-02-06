package com.example.nyt.qq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NYT on 2018/2/6.
 */

public class PageGridViewAdapter extends BaseAdapter {
    private ArrayList<String>names;
    private ArrayList<Integer> images;
    private Context mContext;
    public PageGridViewAdapter(Context mContext, ArrayList<String>names, ArrayList<Integer> images){
        super();
        this.mContext = mContext;
        this.names=names;
        this.images=images;
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(mContext).inflate(R.layout.gridview_item_lagout,null);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.grid_image);
        TextView textView=(TextView)convertView.findViewById(R.id.grid_text);
        imageView.setImageResource(images.get(position));
        textView.setText(names.get(position));
        return convertView;
    }
}
