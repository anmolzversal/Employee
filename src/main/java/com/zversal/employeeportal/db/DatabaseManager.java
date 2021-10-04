package com.zversal.employeeportal.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager
{

	public static Properties loadPropertiesFile() throws Exception {
 
		Properties prop = new Properties();
		InputStream in = new FileInputStream("database.properties");
		prop.load(in);
		in.close();
		return prop;
	}
	
	public Connection con = null;
	public int port;
	private static HikariDataSource dataSource;
	public DatabaseManager()
	{
			try {
				 
				Properties prop = loadPropertiesFile();
				port=Integer.parseInt(prop.getProperty("port.con"));
				dataSource = new HikariDataSource();
				dataSource.setDriverClassName(prop.getProperty("test.jdbc.dev.driver"));
				
				dataSource.setJdbcUrl(prop.getProperty("test.jdbc.dev.url"));
				dataSource.setUsername(prop.getProperty("test.jdbc.dev.username"));
				dataSource.setPassword(prop.getProperty("test.jdbc.dev.password"));
		 
				con = dataSource.getConnection();
				
				}catch (SQLException e) {
				e.printStackTrace();
				} 
				catch (Exception e) {
				e.printStackTrace();
				} 
		}
	}

