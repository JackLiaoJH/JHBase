package com.jhworks.jhbase.utils;

import android.text.TextUtils;

import org.net.rxnet.utils.RxNetLog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p> 解压工具类 </p>
 *
 * @author jiahui
 * @date 2018/2/5
 */
public final class ZipUtils {

    private static final String ZIP = "zip";
    private static final int MAX_BUFFER = 1024 * 8;

    private ZipUtils() {
        throw new AssertionError();
    }

    /**
     * 解压zip到指定的路径
     * <p><b>
     * 1，当文件不在磁盘上，比如从网络接收的数据，想边接收边解压，因ZipInputStream是顺序按流的方式读取文件，这种场景实现起来毫无压力。
     * <p>
     * 2，如果顺序解压ZIP前面的一小部分文件， ZipFile也不是最佳选择，因为ZipFile读CentralDirectory会带来额外的耗时。
     * <p>
     * 3，如果ZIP中CentralDirectory遭到损坏，只能通过ZipInputStream来按顺序解压。</b></p>
     *
     * @param srcFile  ZIP的名称
     * @param destFile 要解压缩路径
     * @return true:成功，false：失败
     */
    public static boolean unZipFolderByZipInputStream(String srcFile, String destFile) {
        ZipInputStream inZip = null;
        BufferedOutputStream bos = null;
        try {
            inZip = new ZipInputStream(new BufferedInputStream(new FileInputStream(srcFile)));
            ZipEntry zipEntry;
            String szName;
            while ((zipEntry = inZip.getNextEntry()) != null) {
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    szName = szName.substring(0, szName.length() - 1);
                    File folder = new File(destFile + File.separator + szName);
                    folder.mkdirs();
                } else {
                    String destFileName = destFile + File.separator + szName;
                    LogUtils.i(destFileName);
                    File file = new File(destFileName);
                    if (!file.exists()) {
                        LogUtils.i("create file: " + destFileName);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    bos = new BufferedOutputStream(new FileOutputStream(file));
                    int len;
                    byte[] buffer = new byte[MAX_BUFFER];
                    while ((len = inZip.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);
                        bos.flush();
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(inZip, bos);
        }
        return false;
    }

    /**
     * 压缩文件夹
     *
     * @param srcFile  源文件路径
     * @param destFile 输出文件路径,如果是.zip结尾，则使用输出的路径，否则在后面加源文件最后文件名， ../xxx.zip
     * @return 成功返回最终输出文件，否则返回null
     */
    public static String zipFolder(String srcFile, String destFile) {
        ZipOutputStream outZip = null;
        try {
            String extensionName = FileUtils.getExtensionName(destFile);
            if (TextUtils.isEmpty(extensionName) || !ZIP.equals(extensionName.toLowerCase())) {
                String lastFileNameNoEx = FileUtils.getLastFileNameNoEx(srcFile);
                if (!TextUtils.isEmpty(lastFileNameNoEx)) {
                    destFile = destFile + File.separator + lastFileNameNoEx + "." + ZIP;
                    LogUtils.i("构建zip完整路径成功: %s", destFile);
                }
            }
            outZip = new ZipOutputStream(new FileOutputStream(destFile));
            File file = new File(srcFile);
            zipFiles(file.getParent() + File.separator, file.getName(), outZip);
            outZip.finish();
            return destFile;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(outZip);
        }
        return null;
    }

    /**
     * 压缩文件
     *
     * @param folderName 文件夹
     * @param fileName   文件名
     * @param outZip     输出流
     * @throws IOException 异常
     */
    private static void zipFiles(String folderName, String fileName, ZipOutputStream outZip) throws IOException {
        if (outZip == null) return;
        File file = new File(folderName + fileName);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            FileInputStream fis = new FileInputStream(file);
            outZip.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[MAX_BUFFER];
            while ((len = fis.read(buffer)) != -1) {
                outZip.write(buffer, 0, len);
            }
            outZip.closeEntry();
        } else {
            String[] fileList = file.list();
            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(fileName + File.separator);
                outZip.putNextEntry(zipEntry);
                outZip.closeEntry();
            }
            for (String name : fileList) {
                zipFiles(folderName, fileName + File.separator + name, outZip);
            }
        }
    }

    /**
     * 解压zip到指定的路径,
     * <p><b>注意：文件已经在磁盘中存在，且需全部解压出ZIP中的文件，ZipFile效果比ZipInputStream效果好</b></p>
     *
     * @param srcFile  ZIP的名称
     * @param destFile 要解压缩路径
     * @return true:成功，false：失败
     */
    public static boolean unZipFolderByZipFile(String srcFile, String destFile) {
        InputStream is = null;
        OutputStream os = null;
        try {
            ZipFile zipFile = new ZipFile(srcFile);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            ZipEntry zipEntry;
            while (entries.hasMoreElements()) {
                zipEntry = entries.nextElement();
                if (zipEntry.isDirectory()) {
                    File folder = new File(destFile + File.separator + zipEntry.getName());
                    folder.mkdirs();
                } else {
                    String destFileName = destFile + File.separator + zipEntry.getName();
                    LogUtils.i(destFileName);
                    File file = new File(destFileName);
                    if (!file.exists()) {
                        LogUtils.i("create file: " + destFileName);
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    is = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                    os = new BufferedOutputStream(new FileOutputStream(file));
                    int len;
                    byte[] buffer = new byte[MAX_BUFFER];
                    while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                        os.write(buffer, 0, len);
                        os.flush();
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(is, os);
        }
        return false;
    }

}

