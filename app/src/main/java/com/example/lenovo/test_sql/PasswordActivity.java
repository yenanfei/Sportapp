package com.example.lenovo.test_sql;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PasswordActivity extends AppCompatActivity {
    EditText name,phone, password, confirm, age, height, weight;
    RadioGroup sex;
    Button regist;
    String sexType;
    //String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        init();
        //Intent intent = getIntent();
        //phone = intent.getStringExtra("phone");
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sex.getCheckedRadioButtonId() == R.id.boy){
                    sexType = "男";
                }else{
                    sexType = "女";
                }
                if(TextUtils.isEmpty(name.getText())){
                    Toast.makeText(PasswordActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(MainActivity.myDB.getUserbyName(name.getText().toString(), new String[]{"name"}).getCount() != 0){
                    Toast.makeText(PasswordActivity.this,"用户名已被占用",Toast.LENGTH_SHORT).show();
                }
                else if(name.getText().toString().length() > 20) {
                    Toast.makeText(PasswordActivity.this, "用户名不得多于20个字符", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password.getText())){
                    Toast.makeText(PasswordActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().length() < 4 || password.getText().toString().length() > 16){
                    Toast.makeText(PasswordActivity.this, "密码不得少于4个字符，多于16个字符", Toast.LENGTH_SHORT).show();
                }
                else if(!TextUtils.equals(password.getText(), confirm.getText())){
                    Toast.makeText(PasswordActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(age.getText())){
                    Toast.makeText(PasswordActivity.this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(height.getText())){
                    Toast.makeText(PasswordActivity.this, "身高不能为空", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(height.getText())){
                    Toast.makeText(PasswordActivity.this, "体重不能为空", Toast.LENGTH_SHORT).show();
                }
                else{
                    signup();
                }
            }
        });
    }
    private void signup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    EMClient.getInstance().createAccount(name.getText().toString().trim(),password.getText().toString().trim());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Log.v("MyDebug",name.getText().toString()+ " "+password.getText().toString()+" "+phone.getText().toString()+" "+sexType + " "+
                                    Integer.parseInt(age.getText().toString())+" "+Integer.parseInt(height.getText().toString())+" "+
                                    Integer.parseInt(weight.getText().toString())+" ");
                            MainActivity.myDB.insert2User(name.getText().toString(),password.getText().toString(),phone.getText().toString(),
                                    sexType,Integer.parseInt(age.getText().toString()),Integer.parseInt(height.getText().toString()),
                                    Integer.parseInt(weight.getText().toString()),5000);

                            new Thread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                RequestBody body=RequestBody.create(MediaType.parse("text/html"),"1");
                                                OkHttpClient okHttpClient=new OkHttpClient();
                                                //String latitude=sb.toString().substring(sb.toString().indexOf("latitude : ")+11,sb.toString().indexOf("lontitude"));
                                                // System.out.println(latitude);
                                                //String lontitude=sb.toString().substring(sb.toString().indexOf("lontitude : ")+12,sb.toString().indexOf("radius"));
                                                //System.out.println(lontitude);
                                                Request request=new Request.Builder()
                                                        .url("http://10.4.20.132:8080/hhlab/user_s?na="+name.getText().toString()+"&pa="+password.getText().toString()+"&ph="+phone.getText().toString()+"&ge="+sexType+"&ag="+age.getText().toString()+"&he="+height.getText().toString()+"&we="+weight.getText().toString())
                                                        .post(body)
                                                        .build();
                                                try {
                                                    Response response=okHttpClient.newCall(request).execute();
                                                    //response.body().string();
                                                    System.out.println(response.body().string());
                                                    //这里没有用call.enqueue方式连接，用了execute方式，都一样的，写法不一样而已，看个人喜欢
                                                } catch (IOException e) {
                                                    e.printStackTrace();//响应失败了，进行响应操作
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            ).start();
                            Toast.makeText(PasswordActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            //储存用户名
                            SharedPreferences saveUser = getSharedPreferences("User", MODE_PRIVATE);
                            SharedPreferences.Editor editor = saveUser.edit();
                            editor.putString("username",name.getText().toString());
                            editor.apply();

                            startActivity(new Intent(PasswordActivity.this,MainActivity.class));
                            finish();
                        }
                    });
                }  catch (final HyphenateException e){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            int errorCode=e.getErrorCode();
                            if(errorCode==EMError.NETWORK_ERROR){
                                Toast.makeText(getApplicationContext(), "网络错误", Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ALREADY_EXIST){
                                Toast.makeText(getApplicationContext(), "用户名已存在", Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_AUTHENTICATION_FAILED){
                                Toast.makeText(getApplicationContext(), "用户名验证失败", Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ILLEGAL_ARGUMENT){
                                Toast.makeText(getApplicationContext(),"参数不合法", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).start();
    }
    public void init(){
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        sex = (RadioGroup) findViewById(R.id.sex);
        regist = (Button) findViewById(R.id.submit);
    }
}
