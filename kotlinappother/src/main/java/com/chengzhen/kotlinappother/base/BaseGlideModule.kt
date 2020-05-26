package com.chengzhen.kotlinappother.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.chengzhen.kotlinappother.data.Constant

import java.io.File

@GlideModule
class BaseGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, "pic", cacheSize100MegaBytes.toLong()))
        var permission = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permission = context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !permission) {
            builder.setDiskCache(InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes.toLong()))
        } else {
            val cacheLocation = File(Constant.IMG_PATH)
            if (!cacheLocation.exists()) {
                cacheLocation.mkdirs()
            }
            val read = cacheLocation.canRead()
            if (read) {
                builder.setDiskCache(DiskLruCacheFactory(cacheLocation.path, cacheSize100MegaBytes.toLong()))
            } else {
                builder.setDiskCache(InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes.toLong()))
            }

        }


    }

    companion object {

        private val cacheSize100MegaBytes = 100 * 1024 * 1024
    }
}
