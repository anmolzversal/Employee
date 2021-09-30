package com.zversal.employeeeportal.dao;

import com.zversal.employeeportal.db.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
public class EmployeeDao {
	DatabaseManager db=new DatabaseManager();
	public HashMap<Object,Object> read(int id)
	{
		HashMap<Object,Object> getDetails=new HashMap<Object,Object>();
		try {
			if (db.getConnection() != null) {
				PreparedStatement stmnt = db.getConnection().prepareStatement("select id,name,role from user where id=?");
				stmnt.setInt(1, id);
				ResultSet set =  stmnt.executeQuery();
				 if(set.next()) {
					 getDetails.put("id",set.getInt("id"));
					 getDetails.put("name",set.getString("name"));
					 getDetails.put("role",set.getString("role"));
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
	  public String create(int id, String name, String role) {
	    	
	    	try {
	            if(db.getConnection()!=null){
	                PreparedStatement stmnt = db.getConnection().prepareStatement("insert into user values(?,?,?) ");
	                stmnt.setInt(1, id);
	                stmnt.setString(2, name);
	                stmnt.setString(3, role);
	                stmnt.executeUpdate();
	                return "Insertion succesful";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return "Error"+e;
	    	}
			return "Error";
	    
	    	
	    }
	  public String update(int id,String name,String role)
	  {
		  try {
	            if(db.getConnection()!=null){
	                PreparedStatement stmnt = db.getConnection().prepareStatement("update user set name=?,role=? where id=? ");
	                stmnt.setInt(3, id);
	                stmnt.setString(1, name);
	                stmnt.setString(2, role);
	                stmnt.executeUpdate();
	                return "Update Successful";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return "Error";
	    	}
			return "Error";
	  }
	  public String delete(int id)
	  {
		  try {
	            if(db.getConnection()!=null){
	                PreparedStatement stmnt = db.getConnection().prepareStatement("delete from user where id=?");
	                stmnt.setInt(1, id);
	                stmnt.executeUpdate();
	                return "Deletion Successful";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return ""+e;
	    	}
			return "Error";
	  }
}