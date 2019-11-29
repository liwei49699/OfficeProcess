package com.yvrun.officeprocess.core.primary.wechat;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.mvp.view.BaseFragment;
import com.yvrun.officeprocess.util.listener.DoubleClickListener;

import butterknife.BindView;

public class WeChatFragment extends BaseFragment {
    @BindView(R.id.btn_test)
    Button mBtnTest;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {

        mBtnTest.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

                Log.d("--TAG--" + Thread.currentThread().getName(), "onNoDoubleClick: " + "双击");
            }

            @Override
            public void onSingleClick(View v) {

                Log.d("--TAG--" + Thread.currentThread().getName(), "onSingleClick: " + "单击");
            }
        });
    }

    @Override
    protected void getData() {

    }
}
