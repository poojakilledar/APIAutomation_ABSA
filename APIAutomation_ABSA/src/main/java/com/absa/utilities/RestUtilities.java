package com.absa.utilities;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Map;

import com.absa.base.BaseSetup;
import com.absa.constants.GlobalParameter;


public class RestUtilities extends BaseSetup{
	
	public static String ENDPOINT;
	public static RequestSpecBuilder REQUEST_BUILDER;
	public static RequestSpecification REQUEST_SPEC;
	
	public void init() throws IOException {
		super.init();
	}
	
	public static void setEndPoint(String endPoint){
		ENDPOINT = endPoint;
	}
	
	public static RequestSpecification getRequestSpecification(){
		
		REQUEST_BUILDER = new RequestSpecBuilder();
		REQUEST_BUILDER.setBaseUri(GlobalParameter.DOGAPI_BASE_URI);
		REQUEST_BUILDER.setBasePath(GlobalParameter.DOG_BASE_PATH);
		REQUEST_BUILDER.setContentType("application/json");
		
		REQUEST_SPEC = REQUEST_BUILDER.build().log().all();
		
		return REQUEST_SPEC;	
	}
	

	
	public static RequestSpecification createRequestBody(RequestSpecification reqSpec, String reqBody) {
		return reqSpec.body(reqBody);	
	}
	
	public static RequestSpecification createQueryParam(RequestSpecification reqSpec, String param, String value){
		return reqSpec.queryParam(param, value);	
	}
	
	public static RequestSpecification createQueryParam(RequestSpecification reqSpec, Map<String, String> queryMap){
		return reqSpec.queryParams(queryMap);
	}
	
	public static RequestSpecification createPathParam(RequestSpecification reqSpec, String param, String value){
		return reqSpec.pathParam(param, value);
	}
	
	public static RestAssuredResponseImpl getResponse(RequestSpecification reqSpec, String reqType){
		REQUEST_SPEC.spec(reqSpec);
		RestAssuredResponseImpl response = null;
		
		if(reqType.equalsIgnoreCase("get")){
			System.out.println("Request Specs2: "+ENDPOINT);
			response = (RestAssuredResponseImpl) given().spec(REQUEST_SPEC).when().get(ENDPOINT);
		} else if (reqType.equalsIgnoreCase("post")){
			response = (RestAssuredResponseImpl) given().spec(REQUEST_SPEC).post(ENDPOINT);
		} else if (reqType.equalsIgnoreCase("put")){
			response = (RestAssuredResponseImpl) given().spec(REQUEST_SPEC).put(ENDPOINT);
		} else if (reqType.equalsIgnoreCase("delete")){
			response = (RestAssuredResponseImpl) given().spec(REQUEST_SPEC).delete(ENDPOINT);
		} else {
			System.out.println("Type is not supported");
		}
		return response;
	}
	
	public static String getJsonPath(Response res, String element){
		String jsonPath = res.asString();
		JsonPath path = new JsonPath(jsonPath);
		String appID = path.get(element);
		return appID;
	}
	
	public static XmlPath getXmlPath(Response res){
		String xmlPath = res.asString();
		return new XmlPath(xmlPath);
	}

}
