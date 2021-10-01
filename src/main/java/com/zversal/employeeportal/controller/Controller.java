package com.zversal.employeeportal.controller;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import com.zversal.employeeeportal.dao.EmployeeDao;
import spark.Route;

public class Controller
{
	public int id=0;
	public String name=null;
	public String role=null;
	public static Gson gson=new Gson();
	private static Logger logger = Logger.getLogger("log.txt"); 
	public static final EmployeeDao employeedao=new EmployeeDao();
	static HashMap<Object,Object> getDetails=new HashMap<>();
	
	public static void init(){
        FileHandler fh;
        try{
            fh=new FileHandler("log.txt");
            logger.addHandler(fh);
            logger.info("init");
        }
        catch(Exception e){
            logger.log(Level.WARNING,"::Exception::"+e);
            getDetails.put("Error:", "Not Available" );
        }
    }
	public static final Route read = (req, res) -> {
		init();
		String uid = req.params("id");
		try
		{
			int id=Integer.parseInt(uid);
			getDetails= employeedao.read(id);
			return getDetails;
		}
		catch(Exception e)
		{
			logger.log(Level.WARNING,"::Exception::"+e);
			getDetails.put("Error:", "Not Available" );
			return getDetails;
		}
};
	public static final Route create = (req,res) -> {
		init();
		try 
        {
            String val = req.body();
            Controller format=gson.fromJson(val,Controller.class);
            getDetails.put("Result:",employeedao.create(format.id, format.name, format.role));
            return getDetails;

         }
        catch(Exception e) 
        {
        	logger.log(Level.WARNING,"::Exception::"+e);
        	getDetails.put("Error:", "Not Available" );
        	return getDetails;
        }
	};
	
	public static final Route delete = (req,res) -> {
		init();
		try {
			String uid=req.params("id");
			int id = Integer.parseInt(uid);
			getDetails.put("Delete",employeedao.delete(id));
			return getDetails;
		} 
		catch (Exception e)
		{
			logger.log(Level.WARNING,"::Exception::"+e);
			getDetails.put("Error:", "Not Available" );
			return getDetails;
		}
		
	};
	
	public static final Route update = (req,res) -> {
		init();
		try {
			String val = req.body();
            Controller format=gson.fromJson(val,Controller.class);
			getDetails.put("Result:",employeedao.update(format.id, format.name, format.role));
            return getDetails;
		}
		 catch(Exception e)
		 {
			 logger.log(Level.WARNING,"::Exception::"+e);
			 getDetails.put("Error:", "Not Available" );
			 return getDetails;
		 }
	};
}