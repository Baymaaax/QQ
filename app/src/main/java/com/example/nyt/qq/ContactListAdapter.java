package com.example.nyt.qq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by NYT on 2018/2/1.
 */

public class ContactListAdapter extends BaseExpandableListAdapter {
    private String[][] contactNickNames;
    private int[][] contactAvatars;
    private Context mContext;
    private String[] groupNames;


    public ContactListAdapter(Context mContext, String[] groupNames, int[][] contactAvatars,
                              String[][] contactNickNames) {
        this.mContext = mContext;
        this.groupNames = groupNames;
        this.contactAvatars = contactAvatars;
        this.contactNickNames = contactNickNames;
    }

    @Override
    public int getGroupCount() {
        return groupNames.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return contactNickNames[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupNames[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return contactNickNames[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.contactlist_parent, null);
        TextView groupName = (TextView) convertView.findViewById(R.id.group_name);
        groupName.setText(groupNames[groupPosition]);
        groupName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                renameGroup.rename(groupPosition);
                return true;
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.contactlist_child, null);
        final ImageView contactAvatar = (ImageView) convertView.findViewById(R.id.contactlist_avatar);
        TextView contactNickName = (TextView) convertView.findViewById(R.id.contactlist_nickname);
        contactAvatar.setImageResource(contactAvatars[groupPosition][childPosition]);
        contactNickName.setText(contactNickNames[groupPosition][childPosition]);
        contactAvatar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showAvater.show(groupPosition, childPosition);


                return true;
            }
        });
        contactNickName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                renameChild.rename(groupPosition, childPosition);

                return true;
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //重命名昵称
    public interface RenameChild {
        void rename(final int groupPosition, final int childPosition);
    }

    private RenameChild renameChild;

    public void setRenameChildListener(RenameChild renameChild) {
        this.renameChild = renameChild;
    }

    //重命名组名
    public interface RenameGroup {
        void rename(int groupPosition);
    }

    private RenameGroup renameGroup;

    public void setRenameGroupListener(RenameGroup renameGroup) {
        this.renameGroup = renameGroup;
    }

    //显示头像
    public interface ShowAvater {
        void show(final int groupPosition, final int childPosition);
    }

    private ShowAvater showAvater;

    public void setShowAvaterListener(ShowAvater showAvater) {
        this.showAvater = showAvater;
    }


}
