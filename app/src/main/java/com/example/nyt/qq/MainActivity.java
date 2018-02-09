package com.example.nyt.qq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private String[] nickNames = new String[]{"bear", "chicken", "donkey", "elephant", "frog",
            "hippo", "monkey", "panda", "pig", "rabbit", "sheep", "tiger"};
    private int[] avatars = new int[]{R.drawable.bear, R.drawable.chicken, R.drawable.donkey,
            R.drawable.elephant, R.drawable.frog, R.drawable.hippo, R.drawable.monkey,
            R.drawable.panda, R.drawable.pig, R.drawable.rabbit, R.drawable.sheep, R.drawable.tiger};
    private String[] popupText = new String[]{"发起多人聊天", "加好友", "扫一扫", "面对面快传"};
    private int[] popupImage = new int[]{R.drawable.chat, R.drawable.add_friends, R.drawable.scan,
            R.drawable.file_transfer};
    private String[][] contactNickNames = new String[][]{
            {"bear", "chicken", "donkey"},
            {"elephant", "frog", "hippo"},
            {"monkey", "panda", "pig", "rabbit", "sheep", "tiger"}
    };
    private int[][] contactAvatars = new int[][]{
            {R.drawable.bear, R.drawable.chicken, R.drawable.donkey},
            {R.drawable.elephant, R.drawable.frog, R.drawable.hippo},
            {R.drawable.monkey, R.drawable.panda, R.drawable.pig, R.drawable.rabbit, R.drawable.sheep,
                    R.drawable.tiger}
    };
    private String[] groupNames = new String[]{"aaa", "bbb", "ccc"};
    private ListView messageList, popUpList;
    private ImageButton messageTab, contactTab, workTab;
    private ImageButton messagePagePopUpList;
    private static boolean PopuoWindowPopped = false;
    private RelativeLayout messagePage, contactPage, workPage;
    private ExpandableListView contactList;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        listViewInit();
        popupWindowInit();
        tabInit();
        contactListInit();
    }

    private void listViewInit() {
        MessageListAdapter messageListAdapter = new MessageListAdapter(MainActivity.this,
                nickNames, avatars);
        messageList = (ListView) findViewById(R.id.message_list);
        messageList.setAdapter(messageListAdapter);
        // 点击进入聊天页面
        messageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nickname", nickNames[position]);
                bundle.putInt("friend avatar", avatars[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        messageList.setLongClickable(true);
        messageList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "长按" + nickNames[position],
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void popupWindowInit() {
        popUpList = (ListView) findViewById(R.id.popup_list);
        PopUpAdapter popUpAdapter = new PopUpAdapter(MainActivity.this, popupText, popupImage);
        popUpList.setAdapter(popUpAdapter);
        messagePagePopUpList = (ImageButton) findViewById(R.id.message_page_popuplist);
        messagePagePopUpList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PopuoWindowPopped) {
                    popUpList.setVisibility(View.GONE);
                    PopuoWindowPopped = false;
                } else {
                    popUpList.setVisibility(View.VISIBLE);
                    PopuoWindowPopped = true;
                }
            }
        });
    }

    private void tabInit() {
        messagePage = (RelativeLayout) findViewById(R.id.message_page);
        contactPage = (RelativeLayout) findViewById(R.id.contact_page);
        workPage = (RelativeLayout) findViewById(R.id.work_page);
        messageTab = (ImageButton) findViewById(R.id.message_tab);
        contactTab = (ImageButton) findViewById(R.id.contact_tab);
        workTab = (ImageButton) findViewById(R.id.work_tab);
        title = (TextView) findViewById(R.id.title);
        messageTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageTab.setBackgroundResource(R.drawable.message_blue);
                contactTab.setBackgroundResource(R.drawable.file_gray);
                workTab.setBackgroundResource(R.drawable.work_gray);
                messagePage.setVisibility(View.VISIBLE);
                contactPage.setVisibility(View.GONE);
                workPage.setVisibility(View.GONE);
                title.setText("消息");
                popUpList.setVisibility(View.GONE);
                PopuoWindowPopped = false;
            }
        });
        contactTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageTab.setBackgroundResource(R.drawable.message_gray);
                contactTab.setBackgroundResource(R.drawable.file_blue);
                workTab.setBackgroundResource(R.drawable.work_gray);
                messagePage.setVisibility(View.GONE);
                contactPage.setVisibility(View.VISIBLE);
                workPage.setVisibility(View.GONE);
                title.setText("联系人");
                popUpList.setVisibility(View.GONE);
                PopuoWindowPopped = false;
            }
        });
        workTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageTab.setBackgroundResource(R.drawable.message_gray);
                contactTab.setBackgroundResource(R.drawable.file_gray);
                workTab.setBackgroundResource(R.drawable.work_blue);
                messagePage.setVisibility(View.GONE);
                contactPage.setVisibility(View.GONE);
                workPage.setVisibility(View.VISIBLE);
                title.setText("办公");
                popUpList.setVisibility(View.GONE);
                PopuoWindowPopped = false;
            }
        });
    }

    private void contactListInit() {
        final ContactListAdapter contactListAdapter = new ContactListAdapter(MainActivity.this,
                groupNames, contactAvatars, contactNickNames);
        contactList = (ExpandableListView) findViewById(R.id.contact_list);
        contactList.setAdapter(contactListAdapter);
// 重命名组名
        contactListAdapter.setRenameGroupListener(new ContactListAdapter.RenameGroup() {
            @Override
            public void rename(final int groupPosition) {
                final RenameDialogFragment renameGroup = new RenameDialogFragment(groupPosition,
                        groupNames, contactNickNames, contactListAdapter);
                renameGroup.show(getFragmentManager(), "dialog_fragment");
            }
        });
// 重命名昵称
        contactListAdapter.setRenameChildListener(new ContactListAdapter.RenameChild() {
            @Override
            public void rename(final int groupPosition, final int childPosition) {
                final RenameDialogFragment renameChild = new RenameDialogFragment(groupPosition,
                        childPosition, groupNames, contactNickNames, contactListAdapter);
                renameChild.show(getFragmentManager(), "dialog_fragment");
            }
        });
// 显示头像
        contactListAdapter.setShowAvaterListener(new ContactListAdapter.ShowAvater() {
            @Override
            public void show(final int groupPosition, final int childPosition) {
                Intent intent = new Intent(MainActivity.this, ShowAvatarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("avatar", contactAvatars[groupPosition][childPosition]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
