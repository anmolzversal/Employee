package portal.controller;
import java.util.HashMap;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import portal.dao.Dao;
import spark.Route;

public class Controller
{
	static Dao fun=new Dao();
	public static final Route read = (req, res) -> {
		String uid = req.params("id");
		HashMap<Object,Object> hm= new HashMap<>();
		int id=Integer.parseInt(uid);
		hm=fun.read(id);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String jsonString = gson.toJson(hm);
        
		if (uid != null) {
				return (jsonString);
		} else {
			return -1;
		}
};
	public static final Route create = (req,res) -> {
		try { String val = req.body();
		 JSONObject data = new JSONObject(val);
		 
		 String uid = (String) data.get("id");
		 String uname = (String) data.get("name");
		 String urole = (String) data.get("role");
		 
		 int id = Integer.parseInt(uid);
		 return ""+fun.create(id, uname, urole);
		}
		catch(Exception e)
		{
			return ""+e;
		}
	};
	
	public static final Route delete = (req,res) -> {
		String uid=req.params("id");
		int id=Integer.parseInt(uid);
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String jsonString = gson.toJson(fun.delete(id));
		
		return (jsonString);
	};
	
	public static final Route update = (req,res) -> {
		 String val = req.body();
		 JSONObject data = new JSONObject(val);
		 
		 String uid = (String) data.get("id");
		 String uname = (String) data.get("name");
		 String urole = (String) data.get("role");
		 int id = Integer.parseInt(uid);
		 return ""+fun.update(id, uname, urole);
	};
}