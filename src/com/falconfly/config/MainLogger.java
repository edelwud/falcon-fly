package com.falconfly.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MainLogger {

    public static Logger logger;
    FileHandler fileHandler;


    public MainLogger(String filePath, String className) throws SecurityException, IOException {

        File logFile = new File(filePath);
        if(!logFile.exists()) {
            logFile.createNewFile();
        }

        fileHandler = new FileHandler(filePath, true);
        logger = Logger.getLogger(className);
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
    }
}
