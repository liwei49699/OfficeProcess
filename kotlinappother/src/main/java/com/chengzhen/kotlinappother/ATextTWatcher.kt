package com.chengzhen.kotlinappother

import android.text.Editable
import android.text.TextWatcher
import android.util.Log

abstract class ATextTWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        Log.d("--TAG--", "beforeTextChanged: $s")
    }

    override fun afterTextChanged(s: Editable) {

        Log.d("--TAG--", "beforeTextChanged: $s")
    }

}
