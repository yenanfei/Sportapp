package com.example.lenovo.test_sql;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by 越 on 2017/8/10.
 */

public class EaseUIApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options=new EMOptions();
        options.setAutoLogin(true);
        EaseUI.getInstance().init(this,null);
    }
}
