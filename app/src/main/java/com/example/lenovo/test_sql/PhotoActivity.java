package com.example.lenovo.test_sql;
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

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int NONE = 0;
    private static final int PHOTO_GRAPH = 1;// 拍照
    private static final int PHOTO_ZOOM = 2; // 缩放
    private static final int PHOTO_RESOULT = 3;// 结果
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private ImageView imageView = null;
    private Button btnPhone = null;
    private Button btnTakePicture = null;

    private ImageButton UserMain = null;
    private ImageButton imageButtonStepCountMain = null;
    private ImageButton photo = null;
    private ImageButton weather = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo);



        imageView = (ImageView) findViewById(R.id.imageView);

        btnPhone = (Button) findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(this);

        btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(this);

        UserMain = (ImageButton) findViewById(R.id.UserMain);
        UserMain.setOnClickListener(this);

        imageButtonStepCountMain =(ImageButton) findViewById(R.id.imageButtonStepCountMain);
        imageButtonStepCountMain.setOnClickListener(this);

        photo = (ImageButton) findViewById(R.id.photo);
        photo.setOnClickListener(this);

        weather = (ImageButton) findViewById(R.id.weather);
        weather.setOnClickListener(this);

    }




    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnPhone:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTO_ZOOM);
                break;
            case R.id.btnTakePicture:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(),"temp.jpg")));
                startActivityForResult(intent, PHOTO_GRAPH);
                break;
            case R.id.UserMain:
                intent = new Intent(PhotoActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.photo:
                intent = new Intent(PhotoActivity.this, PhotoActivity.class);
                startActivity(intent);
                break;
            case R.id.weather:
                intent = new Intent(PhotoActivity.this,weatherActivity.class);
                startActivity(intent);
                break;
            case R.id.imageButtonStepCountMain:
                startActivity(new Intent(PhotoActivity.this,StepCountActivity.class));
                break;
            default: break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;
        // 拍照
        if (requestCode == PHOTO_GRAPH) {
            // 设置文件保存路径
            File picture = new File(Environment.getExternalStorageDirectory()
                    + "/temp.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null)
            return;

        // 读取相册缩放图片
        if (requestCode == PHOTO_ZOOM) {
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTO_RESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                //此处可以把Bitmap保存到sd卡中，具体请看：http://www.cnblogs.com/linjiqin/archive/2011/12/28/2304940.html
                imageView.setImageBitmap(photo); //把图片显示在ImageView控件上
                shareMsg("/storage/emulated/0/temp.jpg");
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESOULT);
    }


    public void shareMsg(String imgPath) {

  /*      intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));*/
        File f = new File(imgPath);
        Intent wechatIntent = new Intent(Intent.ACTION_SEND);
        wechatIntent.setPackage("com.tencent.mm");
        wechatIntent.setType("image/jpg");
        Uri u = Uri.fromFile(f);
        wechatIntent.putExtra(Intent.EXTRA_STREAM, u);
       // wechatIntent.putExtra(Intent.EXTRA_TEXT, "分享到微信的内容");
        startActivity(wechatIntent);
    }


}
