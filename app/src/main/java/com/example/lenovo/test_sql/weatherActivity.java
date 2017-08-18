package com.example.lenovo.test_sql;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
//import com.baidu.mapapi.search.route.IndoorRouteResult;
//import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.lenovo.test_sql.StepCount.config.Constant;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.File;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

import android.content.Intent;
import android.view.View;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import 	org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class weatherActivity extends AppCompatActivity implements View.OnClickListener{
    //后台线程
/*    class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.pm25.in/api/querys/pm2_5.json?city=beijing&token=5j1znBVAsnSf5xQyNQyq").build();
                response = client.newCall(request).execute();
                System.out.println(response.body().string());
                //infotmp = response.body().string();
                //setContentView(R.layout.weatherActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    private ImageButton UserMain = null;
    private ImageButton imageButtonStepCountMain = null;
    private ImageButton photo = null;
    //private Button getweather = null;
    private ImageButton my_button = null;

    TextView weathertest;
    String infotmp=null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            if(msg.what==1){
                weathertest=(TextView)findViewById(R.id.weathertext);
                //System.out.println("这是主线程");
                weathertest.setText((String)msg.obj);
            }
        }
    };

    Response response;
    //private EditText lblTitle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new Thread(new MyThread()).start();
        setContentView(R.layout.weather);
       new Thread(

                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder().url("http://www.pm25.in/api/querys/pm2_5.json?city=beijing&token=5j1znBVAsnSf5xQyNQyq").build();
                            response = client.newCall(request).execute();
                            //System.out.println(response.body().string());
                            infotmp=response.body().string();
                            //infotmp.indexOf("\"pm2_5\":")
                            //String substr=infotmp.toString().substring(31, 34);
                            String substr=infotmp.toString().substring(31,infotmp.toString().indexOf(",\"pm2_5_24h\""));
                            System.out.println(substr);
                            //setContentView(R.layout.weatherActivity);
                            Message message = Message.obtain();
                            message.obj = "北京当前的pm2.5指数为："+substr+"，适合运动";
                            message.what = 1;
                            handler.sendMessage(message);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();


        my_button = (ImageButton) findViewById(R.id.my_button);
        my_button.setOnClickListener(this);


        UserMain = (ImageButton) findViewById(R.id.UserMain);
        UserMain.setOnClickListener(this);

        imageButtonStepCountMain =(ImageButton) findViewById(R.id.imageButtonStepCountMain);
        imageButtonStepCountMain.setOnClickListener(this);

        photo = (ImageButton) findViewById(R.id.photo);
        photo.setOnClickListener(this);

/*        getweather = (Button) findViewById(R.id.getweather);
       // getweather.setOnClickListener(this);
        getweather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new Thread(new MyThread()).start();
            }
        });*/

        try {
            Log.v("weatherActivity", response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.UserMain:
                Intent intent = new Intent(weatherActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.photo:
                intent = new Intent(weatherActivity.this, PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButtonStepCountMain:
                startActivity(new Intent(weatherActivity.this, NavigateActivity.class));
                break;
            default: break;
        }
    }
}
