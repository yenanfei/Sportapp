package com.example.lenovo.test_sql;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.test_sql.R;
import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * Created by 越 on 2017/8/14.
 */

public class EaseChatActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ease_chat);

        EaseChatFragment easeChatFragment = new EaseChatFragment();
        easeChatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.layout_container,easeChatFragment).commit();
    }
}
