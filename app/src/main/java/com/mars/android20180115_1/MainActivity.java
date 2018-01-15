package com.mars.android20180115_1;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//用volley下載圖片
public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        img = (ImageView) findViewById(R.id.imageView);
    }
    public void click1(View v)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        //一開始設定看不見進度條
        progressBar.setVisibility(View.VISIBLE);
        img.setVisibility(View.INVISIBLE);
        //七個參數
        /*
        可以看到，ImageRequest的构造函数接收六个参数，
        第一个参数就是图片的URL地址，这个没什么需要解释的。
        第二个参数是图片请求成功的回调，这里我们把返回的Bitmap参数设置到ImageView中。
        第三第四个参数分别用于指定允许图片最大的宽度和高度，如果指定的网络图片的宽度或高度大于这里的最大值，
        则会对图片进行压缩，指定成0的话就表示不管图片有多大，都不会进行压缩。
        第五个参数用于指定图片的颜色属性，Bitmap.Config下的几个常量都可以在这里使用，
        其中ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，
        而RGB_565则表示每个图片像素占据2个字节大小。第六个参数是图片请求失败的回调
         */
        ImageRequest request = new ImageRequest("https://5.imimg.com/data5/UH/ND/MY-4431270/red-rose-flower-500x500.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView img = (ImageView) findViewById(R.id.imageView);
                        img.setImageBitmap(response);
                        //完成下載後才顯示
                        progressBar.setVisibility(View.INVISIBLE);
                        img.setVisibility(View.VISIBLE);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) ;
        //把request傳給queue去執行
        queue.add(request);
        //開始
        queue.start();
    }
    /*
        //解決Volley下載中文出現亂碼的問題
    //自己建立一個類別 繼承StringRequest 然後複寫他的方法
    指定用utf-8來編碼
    //在使用自己寫的StringRequest來用
     */

    public void click2(View v)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new utf8Stringrequest("https://www.mobile01.com/rss/newtopics.xml", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("net",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       queue.add(request);
       queue.start();
    }
}
