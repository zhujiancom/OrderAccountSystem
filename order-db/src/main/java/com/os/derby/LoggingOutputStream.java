package com.os.derby;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;

public class LoggingOutputStream extends OutputStream {
    private static final int DEFAULT_BUFFER_LENGTH = 2048;

    private boolean hasBeenClosed = false;

    private byte[] buf;

    private int count;

    private int curBufLength;

    private Logger logger;

    private Level level;

    public LoggingOutputStream(Logger logger, Level level) {
        if(logger == null || level == null){
            throw new IllegalArgumentException("Logger or Log Level must be not null");
        }
        this.logger = logger;
        this.level = level;
        curBufLength = DEFAULT_BUFFER_LENGTH;
        buf = new byte[curBufLength];
        count = 0;
    }

    @Override
    public void write(int b) throws IOException {
        if(hasBeenClosed){
            throw new IOException("The stream has been closed.");
        }
        // don't log nulls
        if(b == 0){
            return;
        }
        if(count == curBufLength){
            // grow the buffer
            final int newBufLength = curBufLength + DEFAULT_BUFFER_LENGTH;
            final byte[] newBuf = new byte[newBufLength];
            System.arraycopy(buf,0,newBuf,0,curBufLength);
            buf = newBuf;
            curBufLength = newBufLength;
        }
        buf[count] = (byte) b;
        count++;
    }

    public void flush(){
        if(count == 0){
            return ;
        }
        final byte[] bytes = new byte[count];
        System.arraycopy(buf,0,bytes,0,count);
        String str = new String(bytes);
        logger.log(level,str);
        count = 0;
    }

    public void close(){
        flush();
        hasBeenClosed = true;
    }
}
