package com.absa.testcases;

import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.absa.constants.DataPath;
import com.absa.reports.Assertion;
import com.absa.reports.ExtentReportManager;
import com.absa.utilities.ExcelUtility;
import com.absa.utilities.RestUtilities;

public class TC3_ListSubNodeValues extends ExtentReportManager{
	
	private RequestSpecification reqSpec;
	private RestAssuredResponseImpl response;
	
	@BeforeSuite
	public void init() throws IOException {
	}
	
	@BeforeClass
	public void setup() {
		reqSpec = RestUtilities.getRequestSpecification();
	}

	@DataProvider(name = "TC3_DOGAPI")
	public Object[][] getSearchActiveCustomerData() throws Exception {
		ExcelUtility readXlsx = new ExcelUtility();
		String filePath = DataPath.TESTDATA_BASE_DIR+DataPath.DOGAPI_DATA;
		return readXlsx.readFileAndSheet(filePath, "TC3_DOGAPI");
	}

	@Test(dataProvider = "TC3_DOGAPI", testName = "Dog API Test Automation.")
	public void listSubNodeDetails(Method method, Map<String, String> key) {
	
		String testdatafieldToVerify = key.get("DatafieldToVerify");
		
		RestUtilities.setEndPoint("breed/"+testdatafieldToVerify+"/list");
		
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
			 Assertion.assertTrue(bodyStringValue.contains(testdatafieldToVerify), "Verify '"+"testdatafieldToVerify"+"' is in within API response." );
			 
			//Checking node data is not null.
			 List<String> dataNode = jsonPathEvaluator.get("message");
			 Assertion.assertTrue(dataNode.size()>0, "Verify API response is not empty." );
			 
			 Assertion.assertTrue(true, "Verify API response."+bodyStringValue);
		
		} catch (Exception e) {
			System.out.println("ERROR occured while processing request. " + e.getMessage());
		}

	}
}
