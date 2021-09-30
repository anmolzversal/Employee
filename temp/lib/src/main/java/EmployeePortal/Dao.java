package EmployeePortal;
import java.sql.*;
import java.sql.DriverManager;
import java.util.HashMap;
public class Dao {
	static Connection con = null;
	static PreparedStatement stmnt=null;
    public static void main(String[] args)
    {
    	Func fun=new Func();
    	System.out.println(fun.read(101));
    }
}    
class Func extends Dao
{
	public HashMap<Object,Object> read(int id)
	{
		HashMap<Object,Object> hm=new HashMap<Object,Object>();
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/employee","root","Anmol@3004");
			if (con != null) {
				PreparedStatement stmnt = (PreparedStatement) con.prepareStatement("select * from user where id=?");
				stmnt.setInt(1, id);
				ResultSet set =  stmnt.executeQuery();
				 if(set.next()) {
					 hm.put("id",set.getInt("id"));
					 hm.put("name",set.getString("name"));
					 hm.put("role",set.getString("role"));
					 return  hm;
				}
				 
			}
	}
		catch(Exception e)
		{
			hm.put("Error",e);
			return hm;
		}
		hm.put("Error",null);
		return hm;
}
	  public String create(int id, String name, String role) {
	    	
	    	try {
	       	    con = DriverManager.getConnection("jdbc:mysql://localhost:3308/employee","root","Anmol@3004");
	            if(con!=null){
	                PreparedStatement stmnt = con.prepareStatement("insert into user values(?,?,?) ");
	                stmnt.setInt(1, id);
	                stmnt.setString(2, name);
	                stmnt.setString(3, role);
	                stmnt.executeUpdate();
	                return "Insertion Successfull";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return ""+e;
	    	}
			return "Error";
	    
	    	
	    }
	  public String update(int id,String name,String role)
	  {
		  try {
	       	    con = DriverManager.getConnection("jdbc:mysql://localhost:3308/employee","root","Anmol@3004");
	            if(con!=null){
	                PreparedStatement stmnt = con.prepareStatement("update user set name=?,role? where id=? ");
	                stmnt.setInt(3, id);
	                stmnt.setString(1, name);
	                stmnt.setString(2, role);
	                stmnt.executeUpdate();
	                return "Update Successfull";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return ""+e;
	    	}
			return "Error";
	  }
	  public String delete(int id)
	  {
		  try {
	       	    con = DriverManager.getConnection("jdbc:mysql://localhost:3308/employee","root","Anmol@3004");
	            if(con!=null){
	                PreparedStatement stmnt = con.prepareStatement("delete from user where id=?");
	                stmnt.setInt(1, id);
	                stmnt.executeUpdate();
	                return "Deletion Successfull";
	                
	                
	            }
	       }
	    	catch(Exception e){
	    		return ""+e;
	    	}
			return "Error";
	  }
}