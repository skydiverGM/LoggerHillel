package com.imaks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        FileLoggerConfiguration config = FileLoggerConfigurationLoader.loadConfig(new FileInputStream("files/log.config"));
        FileLogger logger = new FileLogger(config, config.getFilePath());
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