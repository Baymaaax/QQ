package com.example.nyt.qq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by NYT on 2018/1/31.
 */

public class PopUpAdapter extends BaseAdapter {
    private String[] popupText;
    private int[] popupImage;
    private Context mContext;

    public PopUpAdapter(Context mContext, String[] popupText, int[] popupImage) {
        super();
        this.mContext = mContext;
        this.popupText = popupText;
        this.popupImage = popupImage;
    }

    @Override
    public int getCount() {
        return popupText.length;
    }

    @Override
    public Object getItem(int position) {
        return popupText[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.popuplist_layout, null);
        ImageView img = (ImageView) convertView.findViewById(R.id.popup_image);
        TextView tv = (TextView) convertView.findViewById(R.id.popup_text);
        img.setImageResource(popupImage[position]);
        tv.setText(popupText[position]);
        return convertView;
    }

}
