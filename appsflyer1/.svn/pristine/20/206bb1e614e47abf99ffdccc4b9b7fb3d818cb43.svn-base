package com.ami.api.init;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log4jConfig
{
    
    public static void enableInfo(String target)
    {
        Logger logger = LogManager.getLogger(target);
        logger.getLevel();
        logger.setLevel(Level.INFO);
    }
    
    public static void enableWarn(String target)
    {
        LogManager.getLogger(target).setLevel(Level.WARN);
    }
    
    public static void enableError(String target)
    {
        LogManager.getLogger(target).setLevel(Level.ERROR);
    }
    
    public static void enableDebug(String target)
    {
        LogManager.getLogger(target).setLevel(Level.DEBUG);
    }
    
}