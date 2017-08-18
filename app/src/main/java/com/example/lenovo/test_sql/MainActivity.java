package com.example.lenovo.test_sql;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    Button login, register;
    public static MyDB myDB;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MyDB(getApplicationContext());
        init();
    }

    public void init(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.registerButton);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }
    private void login(){
        EMClient.getInstance().login(username.getText().toString().trim(), password.getText().toString().trim(), new EMCallBack() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Intent intent = new Intent(MainActivity.this,StepCountActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "聊天服务器登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
    @Override
    public void onClick(View v){
        Log.v("MyDebug",""+v.getId());
        switch(v.getId()){
            case R.id.loginButton:
                Cursor cursor = myDB.getUserbyName(username.getText().toString(), new String[]{"name","password"});
                Cursor cursorPhone = myDB.getUserbyPhone(username.getText().toString(), new String[]{"name","password"});
                String error = "";
                if(cursor.getCount() == 0){
                    error = "账号不存在";
                    Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
                }
                if(cursor.getCount() != 0 && cursor.moveToFirst()){
                    if(password.getText().toString().equals(cursor.getString(cursor.getColumnIndex("password")))){
                        //储存用户名
                        SharedPreferences saveUser = getSharedPreferences("User", MODE_PRIVATE);
                        SharedPreferences.Editor editor = saveUser.edit();
                        editor.putString("username",username.getText().toString());
                        editor.apply();
                        login();
                    } else{
                        error = "密码错误";
                        Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.registerButton:
//                String phone = "123";
                Intent intent = new Intent(MainActivity.this,PasswordActivity.class);
//                intent.putExtra("phone",phone);
                startActivity(intent);
                break;
            default:
                Log.v("Mydebug","id not found");
                break;
        }
    }
}
