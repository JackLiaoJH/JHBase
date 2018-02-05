package cn.jhworks.utilscore.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * <p> 关闭相关</p>
 *
 * @author jiahui
 * date 2018/2/1
 */
public final class CloseUtils {
    private CloseUtils() {
        throw new UnsupportedOperationException("can't instantiate CloseUtils...");
    }

    /**
     * 关闭 IO [集合]
     *
     * @param closeables 要关闭io集合
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
