package com.jhworks.jhbasedemo.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.jhworks.jhbase.utils.AppUtils;
import com.jhworks.jhbase.utils.FileUtils;

/**
 * desc:   Glide 个性化配置
 * author: jacksonliao
 * email: 583125288@qq.com
 * date: 17/5/8
 */
@GlideModule
public class MyGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        String cacheDir = FileUtils.getCacheDirectory(context).getAbsolutePath();
        int cacheSize = 100 * 1024 * 1024;
        RequestOptions options = new RequestOptions();
        options.format(DecodeFormat.PREFER_RGB_565);
        builder.setDiskCache(
                new DiskLruCacheFactory(cacheDir, "image_cache", cacheSize))
                .setDefaultRequestOptions(options)
        ;
    }

    @Override
    public void registerComponents(Context context, Registry registry) {

    }

    // Disable manifest parsing to avoid adding similar modules twice.
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
