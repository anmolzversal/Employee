package com.zversal.employeeportal;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.path;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import static spark.Spark.delete;
import static spark.Spark.port;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zversal.employeeeportal.dao.EmployeeDao;
import com.zversal.employeeportal.controller.Controller;
import com.zversal.employeeportal.db.DatabaseManager;
import com.zversal.employeeportal.service.RemoteService;
import com.zversal.employeeportal.util.LoggerUtil;
import com.zversal.employeeportal.util.PropertyReader;
import com.zversal.employeeportal.client.ClientTest;


public class Main {
	public static Gson gs=new Gson();
	public static Gson gson=new GsonBuilder().create();
	public static EmployeeDao employeedao=new EmployeeDao();
	public static PropertyReader propertyreader = new PropertyReader();
	public static Controller controller=new Controller();
	public static LoggerUtil loggerutil=new LoggerUtil();
	public static DatabaseManager db=new DatabaseManager();
	public static ClientTest clienttest=new ClientTest();
	static RemoteService remoteService=new RemoteService();
	public static void result()
	{
		path("/user",() -> {
 	    	 get("/read/:id",Controller.readById,gson::toJson);
 	         post("/create",Controller.createUser,gson::toJson);
 	         put("/update",Controller.updateUser,gson::toJson);
 	         delete("/delete/:id",Controller.deleteUser,gson::toJson);
 	     });
	}
    public static void main(String[] args) {
    	loggerutil.getLog();
		port(propertyreader.getPort());
    	before((request, response) -> {
    		 if(ClientTest.service()==false) 
             {
                  halt(401,"No result");
             }
    		 else {
    			 result();
    		 }
    	});
    	
    	after((request, response) -> {
    	    response.type("application/json");
    	});
}
}
