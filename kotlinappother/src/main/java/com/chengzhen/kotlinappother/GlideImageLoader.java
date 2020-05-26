package com.yvrun.officeprocess.util.loader;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;
import com.yvrun.officeprocess.global.GlideApp;

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        GlideApp.with(context).load(o).into(imageView);
    }
}