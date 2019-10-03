package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FoodbiteApiTests extends BaseClass {
   Response response;
   String getHotelsEndPoint = "/getHotels";
   String login = "/login";


   @BeforeTest
    public void beforeTest()
    {
        request = RestAssured.given();
    }
    
   @Test
   public void getHotels() {
       System.out.println(getTest(getHotelsEndPoint).getStatusCode());
       assertGetResponse(getTest(getHotelsEndPoint),200,"");
   }

   @Test
   public void login() {
       assertGetResponse(getTest(getHotelsEndPoint),200,"");
   }

}
