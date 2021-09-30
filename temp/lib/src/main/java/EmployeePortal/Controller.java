package EmployeePortal;
import java.util.HashMap;
import spark.Route;

public class Controller
{
	public static final Route find = (req, res) -> {
		String uid = req.params("id");
		HashMap<Object,Object> hm= new HashMap<>();
		Func fun=new Func();
		int id=Integer.parseInt(uid);
		hm=fun.read(id);
		if (uid != null) {
				return ("[ Id: " + hm.get("id") + " ] [ Name: " + hm.get("name") + "] [ Role: " + hm.get("role"))+" ]";
		} else {
			return -1;
		}
};
}