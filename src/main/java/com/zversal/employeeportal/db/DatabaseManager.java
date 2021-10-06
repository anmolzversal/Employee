package com.zversal.employeeportal.db;
import static com.zversal.employeeportal.Main.propertyreader;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager
{
	private Connection con = null;
	private static HikariDataSource dataSource;
	public DatabaseManager()
	{
			try {
				dataSource = new HikariDataSource();
				dataSource.setDriverClassName(propertyreader.getDriverclass());
				
				dataSource.setJdbcUrl(propertyreader.getJdbcurl());
				dataSource.setUsername(propertyreader.getJdbcusername());
				dataSource.setPassword(propertyreader.getJdbcpassword());
		 
				this.con = dataSource.getConnection();
				
				}catch (SQLException e) {
				e.printStackTrace();
				} 
				catch (Exception e) {
				e.printStackTrace();
				} 
		}
	public Connection getConnection() {
		return this.con;
	}
	
	}

