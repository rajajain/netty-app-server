package com.netty.logging.encoder;

import ch.qos.logback.core.rolling.RollingFileAppender;

/**
 * Created by raja.
 */
public class CustomRollingFileAppenderAppender<E> extends RollingFileAppender<E> {

    private static long lastRollOverTimeStamp = System.currentTimeMillis();

    // TODO : Get this property from Config file
    private int rollOverTimeInMinutes = 5;

    @Override
    public void rollover() {
        int maxIntervalSinceLastLoggingInMillis = rollOverTimeInMinutes * 60 * 1000;
        super.rollover();
//        if ((System.currentTimeMillis() - lastRollOverTimeStamp) >= maxIntervalSinceLastLoggingInMillis) {
//            super.rollover();
//            lastRollOverTimeStamp = System.currentTimeMillis();
//        }
    }
}

