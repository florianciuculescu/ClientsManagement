package com.example.clientsmanagement.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtil {

    private static Logger log = LoggerFactory.getLogger(LoggingUtil.class);

    public static void logInfo(String source, String message) {
        log.info(source + " - " + message);
    }

    public static void logDebug(String source, String message) {
        log.debug(source + " - " + message);
    }
}
