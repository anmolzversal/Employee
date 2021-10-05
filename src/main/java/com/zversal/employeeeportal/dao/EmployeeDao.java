package com.zversal.employeeeportal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import static com.zversal.employeeportal.Main.loggerutil;
import static com.zversal.employeeportal.Main.db;
public class EmployeeDao {
	public HashMap<String,Object> readUser(int id)
	{
		
		HashMap<String,Object> getDetails=new HashMap<String,Object>();
		try {
			if (db.getConnection() != null) {
				PreparedStatement stmnt = db.getConnection().prepareStatement("select id,name,role from user where id=?");
				stmnt.setInt(1, id);
				ResultSet set =  stmnt.executeQuery();
				 if(set.next()) {
					 getDetails.put("id",set.getInt("id"));
					 getDetails.put("name",set.getString("name"));
					 getDetails.put("role",set.getString("role"));
					 loggerutil.logger.info(stmnt.toString());
					 return  getDetails;
				}
				 
			}
	}
		catch(Exception e)
		{
			getDetails.put("Error",e);
			return getDetails;
		}
		getDetails.put("Error",null);
		return getDetails;
}
	  public String createUser(int id, String name, String role) {
	    	
	    	try {
	            if(db.getConnection()!=null){
	                PreparedStatement stmnt = db.getConnection().prepareStatement("insert into user values(?,?,?) ");
	                stmnt.setInt(1, id);
	                stmnt.setString(2, name);
	                stmnt.setString(3, role);
	                stmnt.executeUpdate();
	                loggerutil.logger.info(stmnt.toString());
	                return "Insertion succesful";
	                
	            }
	       }
	    	catch(Exception e){
	    		return "Error";
	    	}
			return "Error";
	    
	    	
	    }
	  public String updateUser(int id,String name,String role)
	  {
		  try {
	            if(db.getConnection()!=null){
	                PreparedStatement stmnt = db.getConnection().prepareStatement("update user set name=?,role=? where id=? ");
	                stmnt.setInt(3, id);
	                stmnt.setString(1, name);
	                stmnt.setString(2, role);
	                stmnt.executeUpdate();
	                loggerutil.logger.info(stmnt.toString());
	                return "Update Successful";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return "Error";
	    	}
			return "Error";
	  }
	  public String deleteUser(int id)
	  {
		  try {
	            if(db.getConnection()!=null){
	                PreparedStatement stmnt = db.getConnection().prepareStatement("delete from user where id=?");
	                stmnt.setInt(1, id);
	                stmnt.executeUpdate();
	                loggerutil.logger.info(stmnt.toString());
	                return "Deletion Successful";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return "Error";
	    	}
			return "Error";
	  }
}