package com.jhworks.jhbase.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO utils
 *
 * @author Vladislav Bauer
 */

public class IOUtils {

    private IOUtils() {
        throw new AssertionError();
    }


    /**
     * Close closable object and wrap {@link IOException} with {@link RuntimeException}
     * @param closeable closeable object
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            }
        }
    }

    /**
     * Close closable and hide possible {@link IOException}
     * @param closeable closeable object
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }

    public static void close(Closeable... closeables) {
        if (closeables == null || closeables.length == 0) return;
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null)
                    closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
