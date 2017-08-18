package com.example.lenovo.test_sql;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by master on 2017/8/12.
 */
public class pathActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.path_soprt);

        String img_path = "/storage/emulated/0/Tencent/QQ_Images/123456.jpg";
        Bitmap bmp= BitmapFactory.decodeFile(img_path);
        ImageView iv= (ImageView)findViewById(R.id.imageView1);
        iv.setImageBitmap(bmp);





    }


    // public static Bitmap getLoacalBitmap(String url) {
    //     try {
    //         FileInputStream fis = new FileInputStream(url);
    //         return BitmapFactory.decodeStream(fis);
    //     } catch (FileNotFoundException e) {
    //        e.printStackTrace();
    //        return null;
    //    }
    //  }

}


