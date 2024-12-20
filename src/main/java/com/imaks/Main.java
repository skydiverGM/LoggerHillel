package com.imaks;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        FileLoggerConfiguration config = FileLoggerConfigurationLoader.loadConfig(new FileInputStream("files/log.config"));
        FileLogger logger = FileLogger.getInstance();
        logger.setConfig(config);
        FileLogger logger2 = FileLogger.getInstance();
        FileLogger logger3 = FileLogger.getInstance();
        FileLogger logger4 = FileLogger.getInstance();
        FileLogger logger5 = FileLogger.getInstance();
        FileLogger logger123 = FileLogger.getInstance();
        FileLogger logger12 = FileLogger.getInstance();
        logger2.setConfig(config);

        if (logger == logger2) {
            System.out.println("same instance");
        } else {
            System.out.println("different instances");
        }

        logger.info("Application started");

        String h = hello(logger);

        logger.debug("Method hello() returned" + h);

        logger.info("Shutting down the application");

    }

    private static String hello(FileLogger logger) throws Exception {
        logger.debug("Calling hello() method");

        return "World";
    }

}