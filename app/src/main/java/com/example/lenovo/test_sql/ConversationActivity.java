package com.example.lenovo.test_sql;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * Created by 越 on 2017/8/14.
 */

public class ConversationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ease_chat);
        EaseConversationListFragment conversationListFragment;
        conversationListFragment = new EaseConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(ConversationActivity.this,EaseChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID,conversation.conversationId()));
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.layout_container,conversationListFragment).commit();

    }
}
