package com.zversal.employeeportal.controller;
import static com.zversal.employeeportal.Main.employeedao;
import static com.zversal.employeeportal.Main.gson;
import static com.zversal.employeeportal.Main.loggerutil;
import java.util.HashMap;
import java.util.logging.Level;


import spark.Route;

public class Controller
{
	public static final Route readById = (req, res) -> {
		HashMap<String,Object> map=new HashMap<>();
		try
		{
			String uid = req.params("id");
			int id=Integer.parseInt(uid);
			map= employeedao.readUser(id);
			return map;
		}
		catch(Exception e)
		{
			loggerutil.logger.log(Level.WARNING,"::Exception::"+e);
			map.put("Error:", "Not Available" );
			return map;
		}
};
	public static final Route createUser = (req,res) -> {
		HashMap<String,Object> map=new HashMap<>();
		try 
        {
            String val = req.body();
            HashMap<String,String> getEmployeeDetails=gson.fromJson(val,HashMap.class);
            map.put("Addition Result:",employeedao.createUser(Integer.parseInt(getEmployeeDetails.get("id")), getEmployeeDetails.get("name"), getEmployeeDetails.get("role")));
            return map;

         }
        catch(Exception e) 
        {
        	loggerutil.logger.log(Level.WARNING,"::Exception::"+e);
        	map.put("Error:", "Cannot Create" );
        	return map;
        }
	};
	
	public static final Route deleteUser = (req,res) -> {
		HashMap<String,Object> map=new HashMap<>();
		try {
			String uid=req.params("id");
			int id = Integer.parseInt(uid);
			map.put("Deletion Result:",employeedao.deleteUser(id));
			return map.get("Deletion Result:");
		} 
		catch (Exception e)
		{
			loggerutil.logger.log(Level.WARNING,"::Exception::"+e);
			map.put("Error:", "Cannot Delete" );
			return map;
		}
		
	};
	
	public static final Route updateUser = (req,res) -> {
		HashMap<String,Object> map=new HashMap<>();
		try {
			String val = req.body();
			HashMap<String,String> getEmployeeDetails=gson.fromJson(val,HashMap.class);
            map.put("Updation Result:",employeedao.updateUser(Integer.parseInt(getEmployeeDetails.get("id")), getEmployeeDetails.get("name"), getEmployeeDetails.get("role")));
            return map;
		}
		 catch(Exception e)
		 {
			 loggerutil.logger.log(Level.WARNING,"::Exception::"+e);
			 map.put("Error:", "Cannot update" );
			 return map;
		 }
	};
}