package EmployeePortal;

import static spark.Spark.*;


public class Main {

    public static void main(String[] args) {

        get("/find/:id",Controller.find );

    }

}
