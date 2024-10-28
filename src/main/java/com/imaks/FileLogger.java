package com.imaks;

import javax.naming.SizeLimitExceededException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements Logger{
    private FileLoggerConfiguration config;
    private String path;

    public FileLogger(FileLoggerConfiguration config, String path) {
        this.config = config;
        this.path = path;
    }

    @Override
    public void info(String message) throws Exception {
        if (config.getLevel() == LoggerLevel.INFO || config.getLevel() == LoggerLevel.DEBUG){
            writeLog(message, "INFO");
        }
    }

    @Override
    public void debug(String message) throws Exception {
        if (config.getLevel() == LoggerLevel.DEBUG){
            writeLog(message, "DEBUG");
        }
    }

    private void writeLog(String message, String level) throws Exception{
        File logFile = new File(path);

        if (logFile.length() >= config.getMaxSize()){
            throw new SizeLimitExceededException("File is full.");
        }

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String formattedMessage = String.format(config.getFormat(),timestamp,level,message);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))){
            writer.write(formattedMessage);
            writer.newLine();
        }
    }
}
