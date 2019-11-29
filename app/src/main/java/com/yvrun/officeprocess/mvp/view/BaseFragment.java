package com.yvrun.officeprocess.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yvrun.officeprocess.event.EmptyEvent;
import com.yvrun.officeprocess.wight.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected BaseFragment mFragment;

    private LoadingDialog mLoadingDialog;
    Unbinder unbinder;

    @Override
    public void onAttach(@NonNull Context context) {
        bindMvp();
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mFragment = this;
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView;
        if(getLayoutId() != 0) {
            contentView = inflater.inflate(getLayoutId(), container, false);
        } else {
            contentView = bindView();
        }
        unbinder = ButterKnife.bind(this, contentView);
        return contentView;
    }

    protected abstract int getLayoutId();

    protected  View bindView(){

        return new View(getActivity());
    }


    /**
     * 强制子类重写，实现子类特有的ui
     * @return
     */
    protected abstract void initView();

    protected abstract void getData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化进度提示框
        if(mLoadingDialog == null){

            mLoadingDialog =new LoadingDialog.Builder(mContext)
                    .setMessage("加载中...")
                    .setCancelable(false)
                    .create();
        }
        initView();
        getData();
    }

    protected void bindMvp(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(EmptyEvent event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    protected void showProDialogHint(){

        if(!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    protected void showProDialogHint(String msg){

        if(!mLoadingDialog.isShowing()){
            mLoadingDialog.setMessage(msg);
            mLoadingDialog.show();
        }
    }

    protected void hideProDialogHint(){

        if(mLoadingDialog != null){
            if(mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        }
    }

    protected void startActivity(Class aClass){

        startActivity(aClass,null);
    }

    protected void startActivity(Class aClass, Bundle bundle){

        Intent intent = new Intent(mContext, aClass);
        if(bundle != null) {

            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
