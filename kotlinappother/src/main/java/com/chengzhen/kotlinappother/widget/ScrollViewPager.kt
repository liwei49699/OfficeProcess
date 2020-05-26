package com.chengzhen.kotlinappother

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class ScrollViewPager(context: Context) : ViewPager(context) {

    private val mCanScroll = true

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (!mCanScroll) {
            false
        } else {
            super.onTouchEvent(ev)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (!mCanScroll) {
            false
        } else {
            super.onInterceptTouchEvent(ev)
        }
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }
}
