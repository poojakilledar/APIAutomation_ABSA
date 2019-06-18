# APIAutomation_ABSA

# Project Title
Assessment QA Automation for ABSA. Sample results are attached in "Test_Results_ABSA.docx" file.

# Author
Pooja Killedar.

# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

# Prerequisites
- Java
- Maven
- Set up an environment variables.

# Installing
- Download project artifact from your GitHub. And unzip it.
- Import as Maven project into Eclipse workspace

# Running the tests
- Right click on Project. Click on Maven -> Update Project.
- Right click on Project -> Run As -> Run Config -> Select Suite ->Browse -> Select testng.xml -> Apply ->Run
- Or Right click on testng.xml -> Click on Run button
 
# Tests
com.absa.testcases package contains all test cases. Below class files are created to auromate pre-defined test flows. 

#vTC1_ListAllDogsData 
- Perform an API request to produce a list of all dog breeds.
# TC2_VerifyNodeIsPresent
- Using code, verify “retriever” breed is within the list.
# TC3_ListSubNodeValues
- Perform an API request to produce a list of sub-breeds for “retriever”.
# TC4_VerifyImageAPIBySubBreed
- Perform an API request to produce a random image / link for the sub-breed “golden”.
- Getting error response for sub-breed "golden", hence added one more row in test data file for sub-breed 'hound'.


# Automation Project
Input Data : 
TestNG Data provider was used to read data from excel.
Config/properties file is used to read AUT details. Here, file is empty.
Java classes are created to read constants.

# Test Execution
1. Run testng.xml to execute the testcases. AutomatonReport.html gets created when the suite is executed.

# Test Output:
Implemented extent report to log and document for test cases with steps along with results.
