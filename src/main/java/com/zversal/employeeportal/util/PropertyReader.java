package com.zversal.employeeportal.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader 
{
	String driverclass, jdbcurl, jdbcusername, jdbcpassword;
	int port;

	public static Properties loadPropertiesFile() throws Exception {
		 
        Properties prop = new Properties();
        InputStream in = new FileInputStream("database.properties");
        prop.load(in);
        in.close();
        return prop;
    }
	
	public PropertyReader() {
		try 
		{
			Properties prop = loadPropertiesFile();
			this.driverclass = prop.getProperty("test.jdbc.dev.driver");
			this.jdbcurl = prop.getProperty("test.jdbc.dev.url");
			this.jdbcusername = prop.getProperty("test.jdbc.dev.username");
			this.jdbcpassword = prop.getProperty("test.jdbc.dev.password");
			this.port = Integer.parseInt(prop.getProperty("port.con"));
		}
		catch(Exception e)
		{
			System.out.println("Not working");
		}
	}

	public String getDriverclass() {
		return this.driverclass;
	}

	

	public String getJdbcurl() {
		return this.jdbcurl;
	}

	

	public String getJdbcusername() {
		return  this.jdbcusername;
	}

	

	public String getJdbcpassword() {
		return  this.jdbcpassword;
	}

	

	public int getPort() {
		return  this.port;
	}
}
