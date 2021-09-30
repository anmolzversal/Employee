package portal.main;

import static spark.Spark.*;

import portal.controller.Controller;


public class Main {

    public static void main(String[] args) {
    	path("/user",() -> {
        get("/read/:id",Controller.read );
        post("/create",Controller.create);
        put("/update",Controller.update);
        delete("/delete/:id",Controller.delete);

    });

}
}
