package com.jhworks.jhbase.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ImageUtils
 * <ul>
 * convert between Bitmap, byte array, Drawable
 * <li>{@link #bitmapToByte(Bitmap)}</li>
 * <li>{@link #bitmapToDrawable(Bitmap)}</li>
 * <li>{@link #byteToBitmap(byte[])}</li>
 * <li>{@link #byteToDrawable(byte[])}</li>
 * <li>{@link #drawableToBitmap(Drawable)}</li>
 * <li>{@link #drawableToByte(Drawable)}</li>
 * </ul>
 * <ul>
 * scale image
 * <li>{@link #scaleImageTo(Bitmap, int, int)}</li>
 * <li>{@link #scaleImage(Bitmap, float, float)}</li>
 * </ul>
 * <ul>
 * compress image
 * <li>{@link #compressPicture(String, String, float, float)}</li>
 * <li>{@link #compressPicture(String, String, float, float, int)}</li>
 * </ul>
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2012-6-27
 */
public class ImageUtils {

    private ImageUtils() {
        throw new AssertionError();
    }

    /**
     * convert Bitmap to byte array
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * convert Drawable to byte array
     *
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * scale image
     *
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     *
     * @param org
     * @param scaleWidth  sacle of width
     * @param scaleHeight scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * 压缩图片大小，包括质量压缩，  压缩以图片最小边为准
     *
     * @param srcPath    原图片地址
     * @param desPath    目标图片地址
     * @param descWidth  目标图片宽 ，默认720
     * @param descHeight 目标图片高 ，默认720
     * @return true:压缩成功  false：压缩失败
     */
    public static boolean compressPicture(String srcPath, String desPath, float descWidth, float descHeight) {
        if (descWidth <= 0) descWidth = 720;
        if (descHeight <= 0) descHeight = 720;

        FileOutputStream fos;
        BitmapFactory.Options op = new BitmapFactory.Options();

        op.inJustDecodeBounds = true;
        op.inJustDecodeBounds = false;

        float w = op.outWidth;
        float h = op.outHeight;
        float be = 1.0f;
        if (w > h && w > descWidth) {
            be = h / descHeight;
        } else if (w < h && h > descHeight) {
            be = w / descWidth;
        }
        if (be <= 0) {
            be = 1.0f;
        }
        op.inSampleSize = (int) be;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);

        int desWidth = (int) (w / be);
        int desHeight = (int) (h / be);
        bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
        try {
            fos = new FileOutputStream(desPath);
            if (bitmap != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                try {
                    baos.writeTo(fos);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(baos);
                    IOUtils.close(fos);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 压缩图片大小，包括质量压缩与大小压缩，  压缩以图片最小边为准
     *
     * @param srcPath     原图片地址
     * @param desPath     目标图片地址
     * @param descWidth   目标图片宽 ，默认720
     * @param descHeight  目标图片高 ，默认720
     * @param maxFileSite 文件最大大小，默认100k
     * @return true:压缩成功  false：压缩失败
     */
    public static boolean compressPicture(String srcPath, String desPath,
                                          float descWidth, float descHeight, int maxFileSite) {

        if (maxFileSite <= 0) maxFileSite = 100;
        if (descWidth <= 0) descWidth = 720;
        if (descHeight <= 0) descHeight = 720;

        FileOutputStream fos;
        BitmapFactory.Options op = new BitmapFactory.Options();

        // 开始读入图片，此时把options.inJustDecodeBounds 设置为true了
        op.inJustDecodeBounds = true;
        op.inJustDecodeBounds = false;

        // 缩放图片的尺寸
        float w = op.outWidth;
        float h = op.outHeight;
        float be = 1.0f;
        if (w > h && w > descWidth) {
            be = h / descHeight;
        } else if (w < h && h > descHeight) {
            be = w / descWidth;
        }
        if (be <= 0) {
            be = 1.0f;
        }
        op.inSampleSize = (int) be;// 设置缩放比例,这个数字越大,图片大小越小.
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);

        int desWidth = (int) (w / be);
        int desHeight = (int) (h / be);
        bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
        try {
            fos = new FileOutputStream(desPath);
            if (bitmap != null) {

                //质量压缩
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int options = 100;
                // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
                while (baos.toByteArray().length / 1024 > maxFileSite) {
                    // 重置baos即清空baos
                    baos.reset();
                    // 这里压缩options%，把压缩后的数据存放到baos中
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    options -= 10;// 每次都减少10
                }

                try {
                    baos.writeTo(fos);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.close(baos);
                    IOUtils.close(fos);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
