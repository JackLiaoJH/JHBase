package com.jhworks.jhbasedemo.utils;


/**
 * desc:   Glide 个性化配置
 * author: jacksonliao
 * email: 583125288@qq.com
 * date: 17/5/8
 */
//@GlideModule
public class MyGlideModule /*extends AppGlideModule*/ {

//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//
//        String cacheDir = FileUtils.getCacheDirectory(context).getAbsolutePath();
//        int cacheSize = 100 * 1024 * 1024;
//        RequestOptions options = new RequestOptions();
//        options.format(DecodeFormat.PREFER_RGB_565);
//        builder.setDiskCache(
//                new DiskLruCacheFactory(cacheDir, "image_cache", cacheSize))
//                .setDefaultRequestOptions(options)
//        ;
//    }
//
//    @Override
//    public void registerComponents(Context context, Registry registry) {
//
//    }
//
//    // Disable manifest parsing to avoid adding similar modules twice.
//    @Override
//    public boolean isManifestParsingEnabled() {
//        return false;
//    }
}
