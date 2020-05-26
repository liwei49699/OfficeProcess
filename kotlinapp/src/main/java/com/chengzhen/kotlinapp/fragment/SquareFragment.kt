package com.chengzhen.kotlinapp.fragment

import com.chengzhen.kotlinapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun setLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        tv_content.text = "home"
    }

    override fun getData() {
    }
}