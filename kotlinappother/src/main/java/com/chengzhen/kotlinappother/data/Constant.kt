package com.chengzhen.kotlinappother.data

import android.os.Environment

import java.io.File

object Constant {

    val IMG_PATH = Environment.getExternalStorageDirectory().path + File.separator + "pic"

}
