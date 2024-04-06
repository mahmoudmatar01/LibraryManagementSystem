package org.example.bookslibrary.logger;

import org.slf4j.LoggerFactory;

public class Logger {

    private static Logger INSTANCE=null;
    private final org.slf4j.Logger logger;

    // To enable singleton design pattern
    private Logger() {
        //Initialize Logger
        logger = LoggerFactory.getLogger(Logger.class);
    }

    public static Logger getInstance() {
        if (INSTANCE == null) {
            synchronized (Logger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Logger();
                }
            }
        }
        return INSTANCE;
    }

    public void logInfo(Class<?> className,String message) {
        logger.info("LogMsg in class <"+ className.getSimpleName() + ">: "+ message);
    }
}
