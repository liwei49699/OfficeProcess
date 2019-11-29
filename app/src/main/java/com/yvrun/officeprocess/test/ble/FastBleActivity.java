package com.yvrun.officeprocess.test.ble;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.gyf.immersionbar.ImmersionBar;
import com.ruffian.library.widget.RTextView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.yvrun.officeprocess.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class FastBleActivity extends AppCompatActivity {

    @BindView(R.id.rtv_start_scan)
    RTextView mRtvStartScan;
    @BindView(R.id.btn_start_connect)
    Button mBtnStartConnect;
    @BindView(R.id.btn_stop_scan)
    Button mBtnStopScan;
    @BindView(R.id.btn_test)
    Button btnTest;
    private BleManager mBleManager;
    private List<BleDevice> mScanResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_ble);
        ButterKnife.bind(this);

        ImmersionBar.with(this)
//                .fitsSystemWindows(true)
//                .statusBarColor("#ffffff")
                .statusBarDarkFont(true)
                .init();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                        .from(this, Lifecycle.Event.ON_DESTROY)))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {

                        Log.d("--TAG--",aBoolean + "");
                    }
                });

        mBleManager = BleManager.getInstance();
        //配置扫描规则
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
//                .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
//                .setDeviceName(true, "test")         // 只扫描指定广播名的设备，可选
//                .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
                .setAutoConnect(true)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒；小于等于0表示不限制扫描时间
                .build();
        mBleManager.initScanRule(scanRuleConfig);

        mRtvStartScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //扫描
                mBleManager.scan(new BleScanCallback() {

                    @Override
                    public void onScanFinished(List<BleDevice> scanResultList) {
                        mScanResultList = scanResultList;

                        // 扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）
                        for (BleDevice bleDevice : scanResultList) {
                            if(!TextUtils.isEmpty(bleDevice.getName())) {
                                Log.d("--TAG--", "name:" + bleDevice.getName());
                                Log.d("--TAG--", "mac:" + bleDevice.getMac());
                            }
                        }
                    }

                    @Override
                    public void onScanStarted(boolean success) {

                        // 开始扫描（主线程）
                        Log.d("--TAG--" + Thread.currentThread().getName(), "onScanStarted: " + success);
                    }

                    @Override
                    public void onScanning(BleDevice bleDevice) {

                        // 扫描到一个符合扫描规则的BLE设备（主线程）

                    }
                });
            }
        });

        mBtnStartConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mScanResultList.size() > 0) {
                    startConnect(mScanResultList.get(0));
                }
            }
        });


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean supportBle = mBleManager.isSupportBle();
                boolean blueEnable = mBleManager.isBlueEnable();
                Log.d("--TAG--",supportBle + ""+ blueEnable + "");

//                mBleManager.enableBluetooth();
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 0x01);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 0x01 && resultCode == RESULT_OK){

            Log.d("--TAG--","RESULT_OK");
        }
    }

    private void startConnect(BleDevice bleDevice) {

        mBleManager.connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                // 开始连接
                Log.d("--TAG--", "开始连接" + Thread.currentThread().getName());
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                // 连接失败
                Log.d("--TAG--", "连接失败" + Thread.currentThread().getName());

            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接成功，BleDevice即为所连接的BLE设备
                Log.d("--TAG--", "连接成功" + Thread.currentThread().getName());
            }


            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
                // 连接中断，isActiveDisConnected表示是否是主动调用了断开连接方法
                Log.d("--TAG--", "连接中断" + Thread.currentThread().getName());
            }
        });
    }
}
