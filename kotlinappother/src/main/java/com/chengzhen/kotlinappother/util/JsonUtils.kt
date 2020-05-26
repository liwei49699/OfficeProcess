package com.chengzhen.kotlinappother

import com.google.gson.Gson
import com.google.gson.JsonParser

import java.util.ArrayList

/**
 * Created by li on 2018/6/20.
 * WeChat 18571658038
 * author LiWei
 */

object JsonUtils {

    private val mGson = Gson()

    /**
     * 将json字符串转化成实体对象
     * @param json
     * @param classOfT
     * @return
     */
    fun stringToObject(json: String, classOfT: Class<*>): Any {
        return mGson.fromJson<Any>(json, classOfT)
    }

    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     * @param object
     * @param <T>
     * @return
    </T> */
    fun <T> objectToString(any: T): String {
        return mGson.toJson(any)
    }

    /**
     * 把json 字符串转化成list
     * @param json
     * @param cls
     * @param <T>
     * @return
    </T> */
    fun <T> stringToList(json: String, cls: Class<T>): List<T> {

        val list = ArrayList<T>()
        val asJsonArray = JsonParser.parseString(json).asJsonArray
        for (elem in asJsonArray) {
            list.add(mGson.fromJson(elem, cls))
        }
        return list
    }
}
