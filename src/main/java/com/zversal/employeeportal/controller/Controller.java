package com.zversal.employeeportal.controller;
import java.util.HashMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zversal.employeeeportal.dao.EmployeeDao;
import spark.Route;

public class Controller
{
	public static final EmployeeDao employeedao=new EmployeeDao();
	static HashMap<Object,Object> getDetails=new HashMap<>();
	public static final Route read = (req, res) -> {
		String uid = req.params("id");
		try
		{
			int id=Integer.parseInt(uid);
			getDetails= employeedao.read(id);
			return getDetails;
		}
		catch(NumberFormatException e)
		{
			return getDetails.put("Error:","Not Available");
		}
};
	public static final Route create = (req,res) -> {
		try 
        {
            String val = req.body();
            JsonObject data = new JsonParser().parse(val).getAsJsonObject();
            JsonElement uid =  data.get("id");
            JsonElement uname = data.get("name");
            JsonElement urole = data.get("role");
            int id = uid.getAsInt();
            String name = uname.getAsString();
            String role = urole.getAsString();
            getDetails.put("Result:",employeedao.create(id, "name", "role"));
            return getDetails;

         }
        catch(Exception e) 
        {
            getDetails.put("Error:", "Not Available" );
        }
		return "Error";
	};
	
	public static final Route delete = (req,res) -> {
		String uid=req.params("id");
		
		int id;
		try {
			
			id = Integer.parseInt(uid);
		} 
		catch (NumberFormatException e)
		{
			return getDetails.put("Error:","Not Available");
		}
		return employeedao.delete(id);
		
	};
	
	public static final Route update = (req,res) -> {
		try {
			String val = req.body();
			JsonObject data = new JsonParser().parse(val).getAsJsonObject();
		 
			JsonElement uid =  data.get("id");
			JsonElement uname =  data.get("name");
			JsonElement urole =  data.get("role");
			int id = uid.getAsInt();
			String name = uname.getAsString();
			String role = urole.getAsString();
			getDetails.put("Result:",employeedao.update(id, "name", "role"));
            return getDetails;
		}
		 catch(NumberFormatException e)
		 {
			 getDetails.put("Error:","Not Available");
		 }
		return "Error";
	};
}