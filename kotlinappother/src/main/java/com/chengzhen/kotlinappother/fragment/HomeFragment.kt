package com.chengzhen.kotlinappother

import android.util.Log

class HomeFragment : BaseFragment() {

    override fun initView() {
//        mLayoutId =
        mTitleBarEnable = true
        setCenterTitle("首页")
    }

    override fun getData() {
        Log.d(mTAG,"执行getData")
    }
}
