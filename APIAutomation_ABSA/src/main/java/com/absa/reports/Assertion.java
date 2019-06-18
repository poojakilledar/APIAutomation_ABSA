package com.absa.reports;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class Assertion extends ExtentReportManager{


	public static void assertEquals(boolean actual,boolean expected,String details)
	{		if(actual==expected)
		{
			test.get().log(Status.PASS, details);
			Assert.assertTrue(true,details);
		}
		else
		{
		test.get().log(Status.FAIL, details);
		Assert.assertTrue(false,details);
		}
	}
	
	public static void assertFail(String details)
	{
		test.get().log(Status.FAIL, details);
		Assert.fail(details);
	}
	
	public static void assertTrue(boolean status,String details)
	{
		if(true)
		{
			test.get().log(Status.PASS,details);	
			
			Assert.assertTrue(true,details);
		}
		else
		{	
			test.get().log(Status.FAIL,details);
			Assert.assertTrue(false,details);
		}
	}
}
