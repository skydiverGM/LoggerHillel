package com.imaks;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileLoggerConfigurationLoader {
    public static FileLoggerConfiguration loadConfig(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        String filePath = properties.getProperty("FILE_PATH");
        LoggerLevel level = LoggerLevel.valueOf(properties.getProperty("LEVEL"));
        long maxSize = Long.parseLong(properties.getProperty("MAX_SIZE"));
        String format = properties.getProperty("MESSAGE_FORMAT");

        return new FileLoggerConfiguration(filePath, level, maxSize, format);
    }
}
