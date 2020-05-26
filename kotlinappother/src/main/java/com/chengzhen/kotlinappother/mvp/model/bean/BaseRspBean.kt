package com.chengzhen.kotlinappother.mvp.model.bean

import com.chengzhen.kotlinappother.JsonUtils

/**
 * Created by langying on 2017/8/3.
 */

open class BaseRspBean<T> {
    var errorCode: String? = null
    var errorMsg: String? = null
    var data: T? = null

    override fun toString(): String {
        return "BaseRspBean{" +
                "errorCode='" + errorCode + '\''.toString() +
                ", errorMsg='" + errorMsg + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }

    fun toJson(): String {
        return JsonUtils.objectToString(this)
    }
}
