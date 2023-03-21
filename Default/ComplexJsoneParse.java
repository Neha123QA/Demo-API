package Default;

import org.testng.Assert;

import files.PayLoads;
import io.restassured.path.json.JsonPath;

public class ComplexJsoneParse {
	
	public static void main(String args[]) {
		
		JsonPath js=new JsonPath(PayLoads.jsonBody());
		
		//Print price of courses array
		int coursecount=js.getInt("courses.size()");
		System.out.println(coursecount);
		
		//print purchase amount
		int purchaseamount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseamount);
		
		//Print title of first index course
		String title1=js.get("courses[0].title");
		System.out.println(title1);
		
		//Print each courses title and their respective price
		for(int i=0;i<coursecount;i++) {
			String title=js.get("courses["+ i +"].title");
			String price=js.get("courses["+ i +"].price").toString();
			System.out.println(title);
			System.out.println(price);
		
			
		}
		
		//Print Number of copies sold by RPA
		System.out.println("Print number of copies sold by RPA");
		for(int i=0;i<coursecount;i++) {
			String coursetitle=js.getString("courses["+ i +"].title");
			if(coursetitle.equalsIgnoreCase("RPA"))
			{
				int coursecopies=js.get("courses[" +i+ "].copies");
				System.out.println(coursecopies);
				int courseprice=js.getInt("courses[" +i+"].price");
				System.out.println(courseprice);
			}
		
		}
		
		//Compare total copies sold with purchase amount
		System.out.println("Compare total copies sold with purchase amount");
		int mul1=0;
		int mul=0;
		for(int i=0;i<coursecount;i++) {
			
			int coursecopies=js.get("courses[" +i+ "].copies");
			int courseprice=js.getInt("courses[" +i+"].price");
			mul=coursecopies*courseprice;
			mul1=mul1+mul;
		}
		Assert.assertEquals(mul1,purchaseamount);
		System.out.println("Assert Passed");
	}
	

}
