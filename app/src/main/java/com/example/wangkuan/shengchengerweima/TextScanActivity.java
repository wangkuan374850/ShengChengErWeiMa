package com.example.wangkuan.shengchengerweima;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;
                                                        //在扫描二维码的类中实现，这个接口，委托的意思
public class TextScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    //打log用的常量
    private static final String TAG = TextScanActivity.class.getSimpleName();
    private QRCodeView mQRCodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_scan);
        //在布局中引用这个控件，找到控件
        mQRCodeView = (ZBarView) findViewById(R.id.zbarview);
        //设置委托给本类
        mQRCodeView.setDelegate(this);
    }
    //在生命周期里面做销毁
    @Override
    protected void onStart() {
        super.onStart();
        //打开照相机
        mQRCodeView.startCamera();
    }

    @Override
    protected void onStop() {
        //关闭照相机
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //销毁
        mQRCodeView.onDestroy();
        super.onDestroy();
    }
    //震动的权限
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
    //这个方法是扫描完回传过来的结果
    @Override
    public void onScanQRCodeSuccess(String result) {
         Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
        i.putExtra("url", result);
        startActivity(i);
        vibrate();
        mQRCodeView.startSpot();
    }
    //打开照相机失败
    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
    //这些是QRCodeView提供的一些功能，打开源码可以看到，我们只需要点击事件的时候调用里面的方法即可
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_spots:
                mQRCodeView.startSpot();
                break;
            case R.id.stop_spots:
                mQRCodeView.stopSpot();
                break;
            case R.id.start_spot_showrect:
                mQRCodeView.startSpotAndShowRect();
                break;
            case R.id.stop_spot_hiddenrect:
                mQRCodeView.stopSpotAndHiddenRect();
                break;
            case R.id.show_rect:
                mQRCodeView.showScanRect();
                break;
            case R.id.hidden_rect:
                mQRCodeView.hiddenScanRect();
                break;

            case R.id.open_flashlight:
                mQRCodeView.openFlashlight();
                break;
            case R.id.close_flashlight:
                mQRCodeView.closeFlashlight();
                break;

        }
    }
}
