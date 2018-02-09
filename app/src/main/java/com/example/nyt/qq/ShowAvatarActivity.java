package com.example.nyt.qq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ShowAvatarActivity extends AppCompatActivity {
    private ImageView showAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_avatar);
        showAvatar = (ImageView) findViewById(R.id.show_avatar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        showAvatar.setImageResource(bundle.getInt("avatar"));
    }
}
