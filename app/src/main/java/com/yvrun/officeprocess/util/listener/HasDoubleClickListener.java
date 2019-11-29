package com.yvrun.officeprocess.util.listener;

import android.view.View;

import java.util.Calendar;

public abstract class HasDoubleClickListener implements View.OnClickListener {

    private static long lastClickTime = 0L;
    /**
     * 防止后面响应单击事件
     */
    private static long lastDoubleClickTime = 0L;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > 500) {

            onSingleClick(v);
            lastClickTime = currentTime;
        } else {

            if(currentTime - lastDoubleClickTime > 500) {
                onNoDoubleClick(v);
            }
            lastDoubleClickTime = currentTime;
            lastClickTime = currentTime;

        }
    }

    public abstract void onNoDoubleClick(View v);
    public abstract void onSingleClick(View v);
}
