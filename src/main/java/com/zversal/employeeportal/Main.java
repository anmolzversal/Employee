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


public class Main {
	public static Gson gs=new Gson();
	public static Gson gson=new GsonBuilder().create();
	public EmployeeDao employeedao=new EmployeeDao();
	public static Controller controller=new Controller();
	public static DatabaseManager portConnection=new DatabaseManager();
    public static void main(String[] args) {
		port(portConnection.port);
    	before((request, response) -> {
       	    boolean authenticated = true;
       	    if (!authenticated) {
       	        halt(401, "You are not welcome here");
       	    }
    	});
    	path("/user",() -> {
        get("/read/:id",Controller.readUser,gson::toJson);
        post("/create",Controller.createUser,gson::toJson);
        put("/update",Controller.updateUser,gson::toJson);
        delete("/delete/:id",Controller.deleteUser,gson::toJson);
    });
    	after((request, response) -> {
    	    response.type("application/json");
    	});
}
}
