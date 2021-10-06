package com.zversal.employeeportal.util;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class LoggerUtil 
{
	public Logger logger=Logger.getLogger("log.txt");
	public void getLog()
	{
		FileHandler filehandler;
        try{
            filehandler=new FileHandler("log.txt");
            logger.addHandler(filehandler);
            logger.info("init");
        }
        catch(Exception e){
            logger.log(Level.WARNING,"::Exception::"+e);
        }
	}

}
