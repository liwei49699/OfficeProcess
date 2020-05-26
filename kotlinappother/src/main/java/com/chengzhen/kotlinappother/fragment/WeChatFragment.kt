package com.chengzhen.kotlinappother.fragment

import android.util.Log
import com.chengzhen.kotlinappother.BaseFragment

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
