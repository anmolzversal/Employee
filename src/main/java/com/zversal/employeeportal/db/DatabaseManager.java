package com.zversal.employeeportal.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager
{

	public static Connection con = null;
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3308/employee","root","Anmol@3004");
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

}
