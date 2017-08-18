package com.example.lenovo.test_sql;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

/**
 * Created by 越 on 2017/8/14.
 */

public class StartChatActivity extends AppCompatActivity{

    private EditText mChatIDView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!EMClient.getInstance().isLoggedInBefore())
        {
            Intent intent = new Intent(StartChatActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        else
        {
            setContentView(R.layout.activity_start_chat);
            mChatIDView = (EditText) findViewById(R.id.chat_id);

            findViewById(R.id.start_chat).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(mChatIDView.getText().toString().trim())){
                        Intent chat = new Intent(StartChatActivity.this,EaseChatActivity.class);
                        chat.putExtra(EaseConstant.EXTRA_USER_ID,mChatIDView.getText().toString().trim());
                        chat.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat); //单聊模式
                        startActivity(chat);
                    }else{
                        Toast.makeText(StartChatActivity.this, "请输入要聊天的对方的账号", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            findViewById(R.id.chat_back).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(StartChatActivity.this,UserActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


        }

    }
}
