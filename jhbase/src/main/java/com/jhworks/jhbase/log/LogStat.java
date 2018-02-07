package com.jhworks.jhbase.log;

import android.text.TextUtils;

import cn.jhworks.utilscore.utils.TimeUtils;

import java.io.File;
import java.io.IOException;

import cn.jhworks.utilscore.utils.CloseUtils;
import cn.jhworks.utilscore.utils.CoreLog;
import okio.BufferedSink;
import okio.Okio;

/**
 * <p>  日志记录,一天一个日志文件 </p>
 *
 * @author jiahui
 * @date 2018/2/7
 */
public final class LogStat {
    private File mLogDir;
    private File mLogFile;

    public LogStat setLogDir(String logDir) {
        if (TextUtils.isEmpty(logDir)) throw new RuntimeException("logDir can not null");
        mLogDir = new File(logDir);
        if (!mLogDir.exists() && mLogDir.isDirectory()) {
            boolean mkdirs = mLogDir.mkdirs();
            if (mkdirs)
                CoreLog.info("创建存放日志目录文件成功: %s", mLogDir.getAbsoluteFile());
        }
        return this;
    }

    public void write(String content, boolean append) {
        if (mLogDir == null) throw new RuntimeException("请在application初始化,调用setLogDir设置。");
            mLogFile = new File(mLogDir, TimeUtils.getCurrentTimeInString())
        BufferedSink buffer = null;
        try {
            buffer = Okio.buffer(append ? Okio.appendingSink(mLogFile) : Okio.sink(mLogFile));
            buffer.writeUtf8(content)
                    .flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(buffer);
        }
    }
}
