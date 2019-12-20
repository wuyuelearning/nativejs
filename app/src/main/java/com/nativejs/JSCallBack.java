package com.nativejs;

import android.webkit.JavascriptInterface;

/**
 * Created by wuyue on 2019/12/20.
 * description:
 */
public class JSCallBack {
    @JavascriptInterface
    public void callback(){
        System.out.println("js  native ......");
    }
}
