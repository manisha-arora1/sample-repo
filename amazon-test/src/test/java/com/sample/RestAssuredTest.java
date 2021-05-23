package com.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class RestAssuredTest {

	@SuppressWarnings("unchecked")
	@Test
	void test() throws ParseException {
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification request = RestAssured.given();
		Response respose = request.request(Method.GET, "/employee/1");
		assertEquals(200, respose.statusCode());
		assertEquals("HTTP/1.1 200 OK", respose.statusLine());
		System.out.println(" StatusLine: " + respose.statusLine() + " Response Body: \n " + respose.getBody().asPrettyString());
		JSONParser parser = new JSONParser();
		JSONObject topJson = (JSONObject) parser.parse(respose.getBody().asString());
		String dataElement = topJson.get("data").toString();
		JSONObject dataJson = (JSONObject) parser.parse(dataElement);
		System.out.println("employee_name : " + dataJson.get("employee_name"));
		assertEquals("Tiger Nixon", dataJson.get("employee_name"));

		// create operation
		JSONObject newEmp = new JSONObject();
		newEmp.put("name", "Dummy");
		newEmp.put("salary", "123");
		newEmp.put("age", "25");
		RequestSpecification newEmpRequest = RestAssured.given().body(newEmp.toString());
		respose = newEmpRequest.request(Method.POST, "/create");
		System.out.println(" StatusLine: " + respose.statusLine() + " Response Body: \n " + respose.getBody().asPrettyString());
		topJson = (JSONObject) parser.parse(respose.getBody().asString());
		dataElement = topJson.get("data").toString();
		dataJson = (JSONObject) parser.parse(dataElement);
		String id = dataJson.get("id").toString();
		System.out.println("employee_id : " + id);
		assertEquals("Successfully! Record has been added.", topJson.get("message"));
		
		
		// delete operation operation respose =
		respose = request.request(Method.DELETE, "/delete/"+id);
		System.out.println(" StatusLine: " + respose.statusLine() + " Response Body: \n " + respose.getBody().asPrettyString());
		assertEquals(200, respose.statusCode());
		topJson = (JSONObject) parser.parse(respose.getBody().asString());
		assertEquals("Successfully! Record has been deleted", topJson.get("message"));
	}

}
