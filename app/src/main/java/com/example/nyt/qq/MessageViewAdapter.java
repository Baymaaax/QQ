package com.example.nyt.qq;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    private View setBySendView(final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_send, null);
        final ImageView avatar = (ImageView) view.findViewById(R.id.my_avatar);
        TextView textSend = (TextView) view.findViewById(R.id.text_send);
        avatar.setImageResource(myAvatar);
        textSend.setText(messageList.get(position).getMessageText());
        textSend.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder withdrawDialog = new AlertDialog.Builder(mContext);
                withdrawDialog.setMessage("是否要撤回这条消息")
                        .setPositiveButton("撤回", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"消息已撤回", Toast.LENGTH_SHORT).show();
                                messageList.remove(position);
                                MessageViewAdapter.this.notifyDataSetInvalidated();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
                return true;
            }
        });
        avatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent =new Intent(mContext,ShowAvatarActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("avatar",myAvatar);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                return true;
            }
        });
        return view;
    }

    private View setByReceiveView(final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_receive, null);
        ImageView avatar = (ImageView) view.findViewById(R.id.friend_avatar);
        TextView textReceive = (TextView) view.findViewById(R.id.text_receive);
        avatar.setImageResource(friendAvatar);
        textReceive.setText(messageList.get(position).getMessageText());
        textReceive.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder withdrawDialog = new AlertDialog.Builder(mContext);
                withdrawDialog.setMessage("是否要撤回这条消息")
                        .setPositiveButton("撤回", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"消息已撤回", Toast.LENGTH_SHORT).show();
                                messageList.remove(position);
                                MessageViewAdapter.this.notifyDataSetInvalidated();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();


                return true;
            }
        });
        avatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent =new Intent(mContext,ShowAvatarActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("avatar",friendAvatar);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                return true;
            }
        });
        return view;
    }
}