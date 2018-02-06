package com.example.nyt.qq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowAvatarActivity extends AppCompatActivity {
    private ImageView showAvatar;
    private int groupPositon;
    private int childPosition;
    private int[][] contactAvatars = new int[][]{
            {R.drawable.bear, R.drawable.chicken, R.drawable.donkey},
            {R.drawable.elephant, R.drawable.frog, R.drawable.hippo},
            {R.drawable.monkey, R.drawable.panda, R.drawable.pig, R.drawable.rabbit, R.drawable.sheep,
                    R.drawable.tiger}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_avatar);
        showAvatar=(ImageView)findViewById(R.id.show_avatar);
        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        groupPositon=bundle.getInt("groupPosition");
        childPosition=bundle.getInt("childPosition");
        showAvatar.setImageResource(contactAvatars[groupPositon][childPosition]);
    }
}
