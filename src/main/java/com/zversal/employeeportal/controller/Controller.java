package com.zversal.employeeportal.controller;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.zversal.employeeportal.Main;

import spark.Route;

public class Controller
{
	public int id=0;
	public String name=null;
	public String role=null;
	public static Gson gson=new Gson();
	private static Logger logger = Logger.getLogger("log.txt"); 
	public static Main main=new Main();
	static HashMap<String,Object> getEmployeeDetails=new HashMap<>();
	public static void init(){
        FileHandler fh;
        try{
            fh=new FileHandler("log.txt");
            logger.addHandler(fh);
            logger.info("init");
        }
        catch(Exception e){
            logger.log(Level.WARNING,"::Exception::"+e);
            getEmployeeDetails.put("Error:", "Not Available" );
        }
    }
	public static final Route readUser = (req, res) -> {
		init();
		String uid = req.params("id");
		try
		{
			int id=Integer.parseInt(uid);
			getEmployeeDetails= main.employeedao.readUser(id);
			return getEmployeeDetails;
		}
		catch(Exception e)
		{
			logger.log(Level.WARNING,"::Exception::"+e);
			getEmployeeDetails.put("Error:", "Not Available" );
			return getEmployeeDetails.get("Error:");
		}
};
	public static final Route createUser = (req,res) -> {
		init();
		try 
        {
            String val = req.body();
            Controller format=gson.fromJson(val,Controller.class);
            getEmployeeDetails.put("Addition Result:",main.employeedao.createUser(format.id, format.name, format.role));
            return getEmployeeDetails.get("Addition Result:");

         }
        catch(Exception e) 
        {
        	logger.log(Level.WARNING,"::Exception::"+e);
        	getEmployeeDetails.put("Error:", "Not Available" );
        	return getEmployeeDetails.get("Error:");
        }
	};
	
	public static final Route deleteUser = (req,res) -> {
		init();
		try {
			String uid=req.params("id");
			int id = Integer.parseInt(uid);
			getEmployeeDetails.put("Deletion Result:",main.employeedao.deleteUser(id));
			return getEmployeeDetails.get("Deletion Result:");
		} 
		catch (Exception e)
		{
			logger.log(Level.WARNING,"::Exception::"+e);
			getEmployeeDetails.put("Error:", "Not Available" );
			return getEmployeeDetails.get("Error:");
		}
		
	};
	
	public static final Route updateUser = (req,res) -> {
		init();
		try {
			String val = req.body();
            Controller format=gson.fromJson(val,Controller.class);
			getEmployeeDetails.put("Updation Result:",main.employeedao.updateUser(format.id, format.name, format.role));
            return getEmployeeDetails.get("Updation Result:");
		}
		 catch(Exception e)
		 {
			 logger.log(Level.WARNING,"::Exception::"+e);
			 getEmployeeDetails.put("Error:", "Not Available" );
			 return getEmployeeDetails.get("Error:");
		 }
	};
}