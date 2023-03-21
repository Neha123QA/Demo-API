package Default;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoads;
import files.ReUsable_Mehtods;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider="bookdata")
	public void addbook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json").
		body(PayLoads.addbook(isbn,aisle)).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js=ReUsable_Mehtods.rawToJson(response);
		String ID=js.get("ID");
		System.out.println(ID);
	}
	
	@DataProvider(name="bookdata")
	public Object[][] getData() {
		
		return new Object[][] {{"asdasd","123411"},{"asdasf","123412"},{"asdasg","123413"}};
	}
	
}
