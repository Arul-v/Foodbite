package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers;

import static org.testng.AssertJUnit.assertEquals;

public class BaseClass {
    public String URL="http://localhost:8102";
    RequestSpecification request;

    public Response postTest(String requestParam) {
         return RestAssured.given().formParams("money", requestParam).post(URL);
    }

    public Response getTest(String endPoint) {
        return request.get(URL+endPoint);
    }

    public void assertGetResponse(Response response, int statusCode, String message)
    {
        assertEquals(response.getStatusCode(), statusCode);
        if(statusCode==200 && message!="")
        {
            assertEquals(response.asString(),message);
        }
    }

    public void  assertResponse(Response response, int statusCode, String message)
    {
        assertEquals(response.getStatusCode(), statusCode);
        if(statusCode==200)
        {
            System.out.println(response.asString());
            assertEquals(response.asString(),message);
        }
        else if(statusCode==415)
        {
            assertEquals(response.asString().contains(""),true);
        }
        else
            response.then().body("message", Matchers.is(message));
    }
}
