package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import static org.testng.AssertJUnit.assertEquals;

public class BaseClass {
    public String URL="http://localhost:8102";
    RequestSpecification request;
    JSONObject requestParams = new JSONObject();

    public Response postTest(String requestParam) {
         return RestAssured.given().formParams("money", requestParam).post(URL);
    }

    public Response getTest(String endPoint) {
        return request.get(URL+endPoint);
    }

    public Response postTest(JSONObject requestParams, String endPoint, String content_type) {
        request.header("Content-Type", content_type);
        request.body(requestParams.toJSONString());
        System.out.println(requestParams);
        return request.post(URL+endPoint);
    }

    public void assertGetResponse(Response response, int statusCode, String message)
    {
        assertEquals(response.getStatusCode(), statusCode);
        if(statusCode==200 && message!="")
        {
            assertEquals(response.asString(),message);
        }
    }

    public void assertResponse(Response response, int statusCode)
    {
        assertEquals(response.getStatusCode(), statusCode);
    }

    public void  assertResponse(Response response, int statusCode, String message)
    {
        assertEquals(response.getStatusCode(), statusCode);
        if(statusCode==200)
        {
            assertEquals(response.asString(),message);
        }
        else if(statusCode==415)
        {
            assertEquals(response.asString().contains(""),true);
        }
        else
            response.then().body("message", Matchers.is(message));
    }


    public JSONObject jsonObject(String username, String password)
    {
        requestParams.put("username",username);
        requestParams.put("password",password);
        return requestParams;
    }
}
