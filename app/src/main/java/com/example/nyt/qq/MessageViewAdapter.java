package com.example.nyt.qq;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NYT on 2018/2/7.
 */

public class MessageViewAdapter extends BaseAdapter {
    private List<Message> messageList;
    private int friendAvatar;
    private int myAvatar;
    private Context mContext;
    private final int MYSELF = 1;
    private final int FRIEND = 0;

    public MessageViewAdapter(Context mContext, List<Message> messageList, int friendAvatar, int myAvatar) {
        this.mContext = mContext;
        this.messageList = messageList;
        this.friendAvatar = friendAvatar;
        this.myAvatar = myAvatar;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (messageList.get(position).getMessageSender()) {
            case MYSELF: {
                convertView = setBySendView(position);
            }
            break;
            case FRIEND: {
                convertView = setByReceiveView(position);
            }
            break;
            default:
                break;
        }
        return convertView;
    }

    private View setBySendView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_send, null);
        ImageView avatar = (ImageView) view.findViewById(R.id.my_avatar);
        TextView textSend = (TextView) view.findViewById(R.id.text_send);
        avatar.setImageResource(myAvatar);
        textSend.setText(messageList.get(position).getMessageText());
        return view;
    }

    private View setByReceiveView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_receive, null);
        ImageView avatar = (ImageView) view.findViewById(R.id.friend_avatar);
        TextView textReceive = (TextView) view.findViewById(R.id.text_receive);
        avatar.setImageResource(friendAvatar);
        textReceive.setText(messageList.get(position).getMessageText());
        return view;
    }
}