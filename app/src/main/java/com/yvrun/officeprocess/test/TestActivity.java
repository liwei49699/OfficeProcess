
package com.yvrun.officeprocess.test;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yvrun.officeprocess.R;
import com.yvrun.officeprocess.global.GlideApp;
import com.yvrun.officeprocess.mvp.contract.TestContract;
import com.yvrun.officeprocess.mvp.model.bean.TestResponseBean;
import com.yvrun.officeprocess.mvp.view.BaseMvpActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseMvpActivity<TestContract.TestPresenterImp> implements TestContract.ITestView {

    @BindView(R.id.tv_content)
    TextView mTvContent;
    @BindView(R.id.iv_test)
    ImageView mIvTest;

    @Inject
    Object mTestDagger;
    @Inject
    Object mTestDagger1;
    @Inject
    Application mApplication;
    @Inject
    Application mApplication1;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {
        String imgPath = "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png";
        GlideApp.with(this).load(imgPath)
                .error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher)
                .into(mIvTest);
    }

    @Override
    protected void getData() {

        Log.d("--TAG--", mApplication + "");
        Log.d("--TAG--", mApplication1 + "");
    }

    @OnClick(R.id.btn_get_data)
    public void clickView(View view){
        mPresenter.getData();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("--TAG--", mApplication + "");
                Log.d("--TAG--", mApplication + "");

            }
        },4000);
    }

//    @Override
//    protected void setupActivityComponent(AppComponent appComponent) {
//        DaggerTestComponent.builder().appComponent(appComponent).testModule(new TestModule(this)).build().inject(this);
//    }

    @Override
    public void operateData(TestResponseBean responseBean) {
        mTvContent.setText(responseBean.toString());
    }

    @Override
    public void showBefore() {
        showProDialogHint();
    }

    @Override
    public void hideAfter() {
        hideProDialogHint();
    }

}
