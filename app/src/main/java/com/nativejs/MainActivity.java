package com.nativejs;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView openWeb;
    TextView nativeJs;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        openWeb =(TextView)findViewById(R.id.tv_open_web);
        nativeJs =(TextView)findViewById(R.id.tv_natvie_js);
        webView= (WebView)findViewById(R.id.wv);
        WebSettings webSettings = webView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        openWeb();
        nativeJs();
        jsNative();

    }

    @SuppressLint("AddJavascriptInterface")
    private void  jsNative(){
        //  注意这里的"kc"，即与JS约定的方法调用名
        webView.addJavascriptInterface(new JSCallBack(),"kc");
    }
    private void openWeb(){
        openWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过Handler发送消息
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        // 先载入JS代码
                        // 格式规定为:file:///android_asset/文件名.html
                        webView.loadUrl("file:///android_asset/1.html");
                    }
                });

            }
        });
    }
    private void nativeJs(){
        nativeJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过Handler发送消息
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        // 注意调用的JS方法名要对应上
                        // 调用javascript的callJS()方法
                        webView.loadUrl("javascript:callJS(123)");
                    }
                });
            }
        });
    }
}
