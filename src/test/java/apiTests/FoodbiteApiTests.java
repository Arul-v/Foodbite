package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FoodbiteApiTests extends DataProviders {
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

   @Test(dataProvider = "validDataForLogin")
   public void validDataForLogin(String username, String password) {
        response = postTest(jsonObject(username,password),login,"application/json");
        assertResponse(response,200);
   }

}
