package cn.jhworks.utilscore.utils;

import android.os.Environment;
import android.os.StatFs;
import android.os.UserManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.jhworks.utilscore.UtilsCore;


/**
 * <p> SD卡相关类</p>
 *
 * @author jiahui
 *         date 2018/2/1
 */
public final class SdCardUtils {

    private SdCardUtils() {
        throw new UnsupportedOperationException("can't instantiate SdCardUtils...");
    }

    /**
     * 判断ＳＤ卡是否挂载
     */
    public static boolean isSdCardMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * Returns the absolute path of this file. An absolute path is a path that starts at a root
     * of the file system. On Android, there is only one root: {@code /}.
     * <p>
     * <p>A common use for absolute paths is when passing paths to a {@code Process} as
     * command-line arguments, to remove the requirement implied by relative paths, that the
     * child must have the same working directory as its parent.
     *
     * @return The absolute pathname string denoting the same file or
     * directory as this abstract pathname
     * @see File#isAbsolute()
     */
    public static String getSdCardBaseDir() {
        if (isSdCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    /**
     * Get a top-level shared/external storage directory for placing files of a
     * particular type. This is where the user will typically place and manage
     * their own files, so you should be careful about what you put here to
     * ensure you don't erase their files or get in the way of their own
     * organization.
     * <p>
     * On devices with multiple users (as described by {@link UserManager}),
     * each user has their own isolated shared storage. Applications only have
     * access to the shared storage for the user they're running as.
     * </p>
     * <p>
     * Here is an example of typical code to manipulate a picture on the public
     * shared storage:
     * </p>
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/content/ExternalStorage.java
     * public_picture}
     *
     * @param type The type of storage directory to return. Should be one of
     *             {@link Environment#DIRECTORY_MUSIC},
     *             {@link Environment#DIRECTORY_PODCASTS},
     *             {@link Environment#DIRECTORY_RINGTONES},
     *             {@link Environment#DIRECTORY_ALARMS},
     *             {@link Environment#DIRECTORY_NOTIFICATIONS},
     *             {@link Environment#DIRECTORY_PICTURES},
     *             {@link Environment#DIRECTORY_MOVIES},
     *             {@link Environment#DIRECTORY_DOWNLOADS},
     *             {@link Environment#DIRECTORY_DCIM}, or
     *             {@link Environment#DIRECTORY_DOCUMENTS}. May not be null.
     * @return Returns the File path for the directory. Note that this directory
     * may not yet exist, so you must make sure it exists before using
     * it such as with {@link File#mkdirs File.mkdirs()}.
     */
    public static String getSdCardPublicDir(String type) {
        if (isSdCardMounted()) {
            return Environment.getExternalStoragePublicDirectory(type).toString();
        }
        return null;
    }

    /**
     * 获取sdcard私有cache的目录的路径
     *
     * @return 私有cache的目录的路径
     */
    public static String getSdCardPrivateCacheDir() {
        if (isSdCardMounted()) {
            return UtilsCore.get().getContext().getExternalCacheDir().getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取sdcard私有file目录的路径
     *
     * @param type The type of storage directory to return. Should be one of
     *             {@link Environment#DIRECTORY_MUSIC},
     *             {@link Environment#DIRECTORY_PODCASTS},
     *             {@link Environment#DIRECTORY_RINGTONES},
     *             {@link Environment#DIRECTORY_ALARMS},
     *             {@link Environment#DIRECTORY_NOTIFICATIONS},
     *             {@link Environment#DIRECTORY_PICTURES},
     *             {@link Environment#DIRECTORY_MOVIES},
     *             {@link Environment#DIRECTORY_DOWNLOADS},
     *             {@link Environment#DIRECTORY_DCIM}, or
     *             {@link Environment#DIRECTORY_DOCUMENTS}. May not be null.
     * @return 对应type的sdcard私有file目录的路径
     */
    public static String getSdCardPrivateFilesDir(String type) {
        if (isSdCardMounted()) {
            return UtilsCore.get().getContext().getExternalFilesDir(type).getAbsolutePath();
        }
        return null;
    }

    /**
     * 获取sdcard的完整空间大小 ,返回MB
     *
     * @return sdcard的完整空间大小
     */
    public static long getSdCardSize() {
        if (isSdCardMounted()) {
            StatFs fs = new StatFs(getSdCardBaseDir());
            int count = fs.getBlockCount();
            int size = fs.getBlockSize();
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取sdcard的剩余空间的大小,MB
     *
     * @return sdcard的剩余空间的大小
     */
    public static long getSdCardFreeSize() {
        if (isSdCardMounted()) {
            StatFs fs = new StatFs(getSdCardBaseDir());
            int count = fs.getFreeBlocks();
            int size = fs.getBlockSize();
            return count * size / 1024 / 1024;
        }
        return 0;
    }

    /**
     * 获取sdcard的可用空间的大小,MB
     *
     * @return sdcard的可用空间的大小
     */
    public static long getSDCardAvailableSize() {
        if (isSdCardMounted()) {
            StatFs fs = new StatFs(getSdCardBaseDir());
            int count = fs.getAvailableBlocks();
            int size = fs.getBlockSize();
            return count * size / 1024 / 1024;

        }
        return 0;
    }

    /**
     * 往sdcard的公有目录下保存文件
     *
     * @param data     数据
     * @param type     The type of storage directory to return. Should be one of
     *                 {@link Environment#DIRECTORY_MUSIC},
     *                 {@link Environment#DIRECTORY_PODCASTS},
     *                 {@link Environment#DIRECTORY_RINGTONES},
     *                 {@link Environment#DIRECTORY_ALARMS},
     *                 {@link Environment#DIRECTORY_NOTIFICATIONS},
     *                 {@link Environment#DIRECTORY_PICTURES},
     *                 {@link Environment#DIRECTORY_MOVIES},
     *                 {@link Environment#DIRECTORY_DOWNLOADS},
     *                 {@link Environment#DIRECTORY_DCIM}, or
     *                 {@link Environment#DIRECTORY_DOCUMENTS}. May not be null.
     * @param fileName 文件名
     * @return true or false
     */
    public static boolean saveFileToSdCardPublicDir(byte[] data, String type, String fileName) {
        BufferedOutputStream bos = null;
        if (isSdCardMounted()) {
            File file = Environment.getExternalStoragePublicDirectory(type);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtils.closeIO(bos);
            }

        }
        return false;
    }

    /**
     * 往sdcard的自定义目录下保存文件
     *
     * @param data     数据
     * @param dir      目录
     * @param fileName 文件名
     * @return true or false
     */
    public static boolean saveFileToSdCardCustomDir(byte[] data, String dir, String fileName) {
        BufferedOutputStream bos = null;
        if (isSdCardMounted()) {
            File file = new File(getSdCardBaseDir() + File.separator + dir);
            if (!file.exists()) {
                //递归创建自定义目录
                file.mkdirs();
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                    bos.write(data);
                    bos.flush();
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    CloseUtils.closeIO(bos);
                }
            }
        }
        return false;
    }

    /**
     * 往sdcard的私有files目录下保存文件
     *
     * @param data     数据
     * @param type     The type of files directory to return. May be {@code null}
     *                 for the root of the files directory or one of the following
     *                 constants for a subdirectory:
     *                 {@link Environment#DIRECTORY_MUSIC},
     *                 {@link Environment#DIRECTORY_PODCASTS},
     *                 {@link Environment#DIRECTORY_RINGTONES},
     *                 {@link Environment#DIRECTORY_ALARMS},
     *                 {@link Environment#DIRECTORY_NOTIFICATIONS},
     *                 {@link Environment#DIRECTORY_PICTURES}, or
     *                 {@link Environment#DIRECTORY_MOVIES}.
     * @param fileName 文件名
     * @return true：成功，false：失败
     */
    public static boolean saveFlieToSdCardPrivateFileDir(byte[] data, String type, String fileName) {
        BufferedOutputStream bos = null;
        if (isSdCardMounted()) {
            File file = UtilsCore.get().getContext().getExternalFilesDir(type);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtils.closeIO(bos);
            }
        }
        return false;
    }

    /**
     * 往sdcard的私有cache目录下保存文件
     *
     * @param data     数据
     * @param fileName 文件名
     * @return true or false
     */
    public static boolean saveFileToSdCardPrivateCacheDir(byte[] data, String fileName) {
        BufferedOutputStream bos = null;
        if (isSdCardMounted()) {
            File file = UtilsCore.get().getContext().getExternalCacheDir();
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtils.closeIO(bos);
            }
        }
        return false;
    }

    /**
     * 从SD卡获取文件内容
     *
     * @param fileDir 文件路径
     * @return 内容
     */
    public static byte[] loadFileFromSdCard(String fileDir) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            bis = new BufferedInputStream(new FileInputStream(new File(fileDir)));
            byte[] buffer = new byte[8 * 1024];
            int c;
            while ((c = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, c);
                baos.flush();
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(bis, baos);
        }
        return null;
    }

    public static boolean isFileExist(String filePath) {
        return new File(filePath).isFile();
    }

    /**
     * 从sdcard中删除文件
     *
     * @param filePath 文件路径
     * @return true or false
     */
    public static boolean removeFileFromSdCard(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
