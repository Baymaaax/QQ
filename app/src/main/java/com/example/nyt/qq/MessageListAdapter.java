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

public class MessageListAdapter extends BaseAdapter {
    private String[] nickNames;
    private int[] avatars;
    private Context mContext;

    public MessageListAdapter(Context mContext, String[] nickNames, int[] avatars) {
        super();
        this.mContext = mContext;
        this.nickNames = nickNames;
        this.avatars = avatars;
    }

    @Override
    public int getCount() {
        return nickNames.length;
    }

    @Override
    public Object getItem(int position) {
        return nickNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.message_list, null);
        ImageView avatar = convertView.findViewById(R.id.avatar);
        TextView nickname = convertView.findViewById(R.id.nick_name);
        avatar.setImageResource(avatars[position]);
        nickname.setText(nickNames[position]);
        return convertView;
    }
}

