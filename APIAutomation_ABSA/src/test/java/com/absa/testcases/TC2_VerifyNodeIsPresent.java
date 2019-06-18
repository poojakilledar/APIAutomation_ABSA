package com.absa.testcases;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.absa.constants.DataPath;
import com.absa.constants.Endpoint;
import com.absa.reports.Assertion;
import com.absa.reports.ExtentReportManager;
import com.absa.utilities.ExcelUtility;
import com.absa.utilities.RestUtilities;

public class TC2_VerifyNodeIsPresent extends ExtentReportManager{
	
	private RequestSpecification reqSpec;
	private RestAssuredResponseImpl response;
	
	@BeforeClass
	public void setup() {
		reqSpec = RestUtilities.getRequestSpecification();
		RestUtilities.setEndPoint(Endpoint.LIST_DOG_DATA);
		
	}

	@DataProvider(name = "TC2_DOGAPI")
	public Object[][] getSearchActiveCustomerData() throws Exception {
		ExcelUtility readXlsx = new ExcelUtility();
		String filePath = DataPath.TESTDATA_BASE_DIR+DataPath.DOGAPI_DATA;
		return readXlsx.readFileAndSheet(filePath, "TC2_DOGAPI");
	}

	@Test(dataProvider = "TC2_DOGAPI", testName = "Dog API Test Automation.")
	public void verifyNodeIsPresent(Method method, Map<String, String> key) {
	
		String testdatafieldToVerify = key.get("DatafieldToVerify");
		
		try {
			response = RestUtilities.getResponse(reqSpec, "GET");
			
			// Get Response Body 
			ResponseBody responseBody = response.getBody();
			String bodyStringValue = responseBody.asString();
			System.out.println("JSON Response: "+bodyStringValue);
			
			// First get the JsonPath object instance from the Response interface
			 JsonPath jsonPathEvaluator = response.jsonPath();
			 
			 // Then simply query the JsonPath object to get a String value of the node
			 // specified by JsonPath
			 String responseStatus = jsonPathEvaluator.get("status");
			 Assertion.assertTrue(responseStatus.equals("success"),"Verify Status code is 200" );
			 
			//Checking particular data-field value/key is present or not
			 Assertion.assertTrue(bodyStringValue.contains(testdatafieldToVerify), "Verify '"+testdatafieldToVerify+"' is in within API response.");
			 
			 Assertion.assertTrue(true, "Verify API response."+bodyStringValue);
			 
		} catch (Exception e) {
			System.out.println("ERROR occured while processing request. " + e.getMessage());
		}

	}
}
