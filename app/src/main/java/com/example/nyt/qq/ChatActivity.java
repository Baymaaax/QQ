package com.example.nyt.qq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private String[] moreFunctionsNames = new String[]{"qq电话", "视频电话", "文件", "在线文档",
            "日程", "收藏", "发红包", "推荐好友", "转账", "位置"};
    private int[] moreFunctionsImages = new int[]{R.drawable.qq_phone, R.drawable.video, R.drawable.folder,
            R.drawable.cloud_file, R.drawable.calendar, R.drawable.collection, R.drawable.red_envelope,
            R.drawable.recommend_friend, R.drawable.transfer, R.drawable.location};
    private TextView nickNameChatting;
    private ImageButton returnButton, moreButton;
    private boolean viewPagerOpened = false;
    private ViewPager viewPager;
    private RelativeLayout inputBar;
    private ArrayList<GridView> gridViewList;
    private LinearLayout morePages;
    private EditText inputEditText;
    private ImageView pageOneCircle, pageTwoCircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        chatTitleBarInit();
        pageViewInit();
        inputEditText = (EditText) findViewById(R.id.input_edittext);
    }

    void chatTitleBarInit() {
        Intent intent = getIntent();
        nickNameChatting = (TextView) findViewById(R.id.nick_name_chatting);
        nickNameChatting.setText(intent.getStringExtra("nickname"));
        returnButton = (ImageButton) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity.this.finish();
            }
        });
        morePages = (LinearLayout) findViewById(R.id.more_pages);
        moreButton = (ImageButton) findViewById(R.id.more_button);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout inputBar = (RelativeLayout) findViewById(R.id.input_bar);
                if (viewPagerOpened) {
                    moreButton.setBackgroundResource(R.drawable.more_gray);
                    viewPagerOpened = false;
                    morePages.setVisibility(View.GONE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) inputBar.getLayoutParams();
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                } else {
                    moreButton.setBackgroundResource(R.drawable.more_blue);
                    viewPagerOpened = true;
                    morePages.setVisibility(View.VISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) inputBar.getLayoutParams();
                    params.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                }
            }
        });


    }

    void pageViewInit() {
        gridViewList = new ArrayList<GridView>();
//        初始化GridView
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> images = new ArrayList<Integer>();
        for (int i = 0; i < moreFunctionsNames.length; i++) {
            names.add(moreFunctionsNames[i]);
            images.add(moreFunctionsImages[i]);
            if (names.size() == 8) {
                GridView gridView = gridViewCreate(names, images);
                gridViewList.add(gridView);
                names = new ArrayList<String>();
                images = new ArrayList<Integer>();
            }
        }
        if (names.size() > 0) {
            GridView gridView = gridViewCreate(names, images);
            gridViewList.add(gridView);
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(gridViewList);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);

//       小圆点在翻页时改变
        pageOneCircle = (ImageView) findViewById(R.id.page_one_circle);
        pageTwoCircle = (ImageView) findViewById(R.id.page_two_circle);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    pageOneCircle.setImageResource(R.drawable.circle_selected);
                    pageTwoCircle.setImageResource(R.drawable.circle);
                } else if (position == 1) {
                    pageOneCircle.setImageResource(R.drawable.circle);
                    pageTwoCircle.setImageResource(R.drawable.circle_selected);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private GridView gridViewCreate(final ArrayList<String> names, ArrayList<Integer> images) {
        GridView gridView = new GridView(this);
        gridView.setBackgroundResource(R.color.white);
        gridView.setNumColumns(4);
        gridView.setPadding(dip2px(this, 35), dip2px(this, 20),
                dip2px(this, 35), dip2px(this, 20));
        gridView.setHorizontalSpacing(dip2px(this, 25));
        gridView.setVerticalSpacing(dip2px(this, 20));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setLayoutParams(params);
        PageGridViewAdapter gridViewAdapter = new PageGridViewAdapter(this, names, images);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ChatActivity.this, names.get(position) + "被点击", Toast.LENGTH_SHORT).show();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ChatActivity.this, "长按" + names.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return gridView;

    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
