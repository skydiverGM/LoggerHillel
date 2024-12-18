package com.imaks;

import javax.naming.SizeLimitExceededException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements Logger {
    private static volatile FileLogger instance;
    private FileLoggerConfiguration config;
    private String path;

    private FileLogger() {
    }

    public static FileLogger getInstance() {
        if (instance == null){
            synchronized (FileLogger.class){
                if (instance == null){
                    instance = new FileLogger();
                }
            }
        }
        return instance;
    }

    public void setConfig(FileLoggerConfiguration config) {
        this.config = config;
        this.path = config.getFilePath();
    }

    @Override
    public void info(String message) throws Exception {
        if (config.getLevel() == LoggerLevel.INFO || config.getLevel() == LoggerLevel.DEBUG) {
            writeLog(message, "INFO");
        }
    }

    @Override
    public void debug(String message) throws Exception {
        if (config.getLevel() == LoggerLevel.DEBUG) {
            writeLog(message, "DEBUG");
        }
    }

    private void writeLog(String message, String level) throws Exception {
        File logFile = new File(path);

        if (logFile.length() >= config.getMaxSize()) {
            throw new SizeLimitExceededException("File is full.");
        }

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String formattedMessage = String.format(config.getFormat(), timestamp, level, message);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(formattedMessage);
            writer.newLine();
        }
    }
}
