package com.xray23.scan_code_login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

public class MainActivity extends AppCompatActivity {

    // 权限请求回调码
    public static final int CAMERA_REQ_CODE_ONE = 1;
    public static final int CAMERA_REQ_CODE_TWO = 2;
    // 扫码模式
    public static final int DECODE = 1;
    // 本次扫码回调约束码
    private static final int REQUEST_CODE_SCAN_ONE = 0X01;
    private static final int REQUEST_CODE_SCAN_TWO = 0X02;


    Button loginBtn1, loginBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("测试扫码登录");

        loginBtn1 = findViewById(R.id.to_login_1);
        loginBtn2 = findViewById(R.id.to_login_2);
        // 调用扫码
        loginBtn1.setOnClickListener(v -> {
            Toast.makeText(this, "扫码登录", Toast.LENGTH_SHORT).show();
            requestPermission(CAMERA_REQ_CODE_ONE, DECODE);
        });
        loginBtn2.setOnClickListener(v -> {
            Toast.makeText(this, "扫码登录带确认", Toast.LENGTH_SHORT).show();
            requestPermission(CAMERA_REQ_CODE_TWO, DECODE);
        });

    }

    //编辑请求权限
    private void requestPermission(int requestCode, int mode) {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE},
                requestCode);
    }

    // 权限请求回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions == null || grantResults == null) {
            return;
        }
        // 因为需要使用存储空间和相机两种权限，所以这里权限请求成功数要大于等于二
        if (grantResults.length < 2 || grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // 权限请求回调码，用于区分不同权限请求的回调
        if (requestCode == CAMERA_REQ_CODE_ONE) {
            //启动扫描Acticity
            // 表示仅扫码二维码
            HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE).setViewType(1).create();
            ScanUtil.startScan(this, REQUEST_CODE_SCAN_ONE, options);
        } else if (requestCode == CAMERA_REQ_CODE_TWO) {
            HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE).setViewType(1).create();
            ScanUtil.startScan(this, REQUEST_CODE_SCAN_TWO, options);
        }
    }

    // 扫码回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SCAN_ONE) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                // 扫码成功直接登录
                Toast.makeText(this, obj.originalValue, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "扫码失败", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_SCAN_TWO) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                // 扫码成功带确认后登录
                Toast.makeText(this, obj.originalValue, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "扫码失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}