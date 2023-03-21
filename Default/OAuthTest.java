package Default;
import static io.restassured.RestAssured.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) {
		WebDriver driver;
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		
		String accesstokenresponse=given()
				.queryParam("code", "")
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code")
				.queryParam("code", "4%2F0ARtbsJr0QZkvRHZCMpIzIGhco6ZcbUL5gvLFf0uL0ABZhJ3NCOLlH8_w6ocOCVFn5HjbTA")
				.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js=new JsonPath(accesstokenresponse);
		String accesstoken=js.getString("access_token");
		
		String response=given().queryParam("access_token", "")
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		System.out.println(response);
		
	}
	
}
