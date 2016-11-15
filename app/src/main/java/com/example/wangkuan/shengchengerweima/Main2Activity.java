package com.example.wangkuan.shengchengerweima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Main2Activity extends AppCompatActivity {
    private WebView wb;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wb = (WebView) findViewById(R.id.webview);
        pg = (ProgressBar) findViewById(R.id.progressbar);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        wb.setWebViewClient(new WebViewClient());
        WebSettings w = wb.getSettings();
        w.setJavaScriptEnabled(true);
        wb.loadUrl("http://weixin.qq.com/r/hkyehpnEc8QMrd3l9xmU");
        wb.setWebChromeClient(new WebChromeClient() {
            //   --有一个在改变进度的时候
            @Override

            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
                //  --显示进度条
                pg.setVisibility(View.VISIBLE);
                //  --显示
                if (newProgress == 100) {
                    pg.setVisibility(View.GONE);
                    // --隐藏
                }
            }
        });
    }

    @Override//--是按键的时候
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        //判断如果键是返回键，系统有主页面，加减音量很多键，所以要指定按键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //如果可以返回才返回
            if (wb.canGoBack()) {
                //返回
                wb.goBack();// 返回
            } else {
                finish();
            }
        }
        return true;
        //  --改成true
    }
}
