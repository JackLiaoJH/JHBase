package com.jhworks.jhbase.log;

import okio.Okio;

/**
 * <p>  </p>
 *
 * @author jiahui
 * @date 2018/2/7
 */
public final class LogStatService {
    public static void writeLog(String content) {
        Okio.buffer(Okio.appendingSink())
    }
}
