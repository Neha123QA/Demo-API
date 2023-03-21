package files;

import io.restassured.path.json.JsonPath;

public class ReUsable_Mehtods {

	public static JsonPath rawToJson(String response){
		
		JsonPath js=new JsonPath(response);
		return js;
	}
	
}
