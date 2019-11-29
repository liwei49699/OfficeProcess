package com.yvrun.officeprocess.util.listener;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yvrun.officeprocess.R;

import java.util.Calendar;

/**
 * Created by sunweiguang on 2018/2/11.
 */
public abstract class NoDoubleNetClickListener implements View.OnClickListener {

    public static final int MIN_CLICK_DELAY_TIME = 1000;

    private long lastClickTime = 0;

    private Context mContext;
    private boolean mISshowDialog;

    public NoDoubleNetClickListener() {
    }
    public NoDoubleNetClickListener(Context mContext) {
        this.mISshowDialog =true;
        this.mContext = mContext;
    }
    public NoDoubleNetClickListener(Context context, boolean showDialog){
        this.mISshowDialog =showDialog;
        this.mContext =context;
    }
    @Override
    public void onClick(View v) {

        if (NetworkUtils.isConnected()) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            }
        } else {
                ToastUtils.showShort(R.string.net_disconnect_check);

        }
    }


    public abstract void onNoDoubleClick(View v);
}
