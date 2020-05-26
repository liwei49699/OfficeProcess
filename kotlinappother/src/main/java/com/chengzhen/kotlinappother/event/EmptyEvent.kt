package com.chengzhen.kotlinappother

import org.greenrobot.eventbus.EventBus

class EmptyEvent {

    fun post() {
        EventBus.getDefault().post(this)
    }
}
