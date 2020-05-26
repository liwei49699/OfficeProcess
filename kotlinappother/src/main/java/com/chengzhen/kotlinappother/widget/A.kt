package com.chengzhen.kotlinappother.widget

import com.chengzhen.kotlinappother.mvp.view.IBaseView
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory

open class A {


    internal open inner class B<T> : A() {

        private val mTList: List<T>? = null

    }

    internal inner class C : B<String>()
}
