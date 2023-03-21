package Default;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import files.PayLoads;
import files.ReUsable_Mehtods;

public class Basics {

	/*
	 * public static void main(String args[]){
	 * 
	 * //Check add place API working as expected //API is working on 3 principles.
	 * //given - all input values //when - submit api //then -validate api(check
	 * status)
	 * 
	 * RestAssured.baseURI="https://rahulshettyacademy.com";
	 * given().log().all().queryParam("key",
	 * "qaclick123").header("Content-Type","application/json").body(PayLoads.
	 * addplace()) .when().post("/maps/api/place/add/json")
	 * .then().log().all().assertThat().statusCode(200).body("scope",
	 * equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)");
	 * 
	 * }
	 */
	
	
	//Add Place->Update Place with new address->Validate new place using get method 
	
public static void main(String args[]) throws IOException{
		
		//Check add place API working as expected
		//API is working on 3 principles.
		//given - all input values
		//when - submit api
		//then -validate api(check status)
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("E:\\Documents\\Project\\API\\AddPlace.json"))))
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		//Update Place
		
		String newadd="123 Summer walk Park,";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{ \r\n"
				+ "\r\n"
				+ "\"place_id\":\""+placeid+"\", \r\n"
				+ "\r\n"
				+ "\"address\":\""+newadd+"\", \r\n"
				+ "\r\n"
				+ "\"key\":\"qaclick123\" \r\n"
				+ "\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//Get Place
		String getplaceresponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReUsable_Mehtods.rawToJson(getplaceresponse);
		String actualadd=js1.getString("address");
		
		System.out.println(actualadd);
		
		Assert.assertEquals(actualadd,newadd);
		
	}
	
}
