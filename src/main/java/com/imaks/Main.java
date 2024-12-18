package com.imaks;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        FileLoggerConfiguration config = FileLoggerConfigurationLoader.loadConfig(new FileInputStream("files/log.config"));
        FileLogger logger = FileLogger.getInstance();
        logger.setConfig(config);


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