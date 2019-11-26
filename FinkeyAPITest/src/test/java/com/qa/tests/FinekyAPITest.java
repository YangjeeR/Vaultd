package com.qa.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Login;
import com.qa.data.Register;
import com.qa.data.VerifyEmail;
import com.qa.data.UpdateProfile;
import com.qa.data.RefreshToken;
import com.qa.data.ChangePassword;
import com.qa.listeners.BaseClass;
import com.qa.listeners.ExtentTestManager;
import com.qa.utils.ExcelDataConfig;
import com.qa.utils.ExcelReader;
import com.relevantcodes.extentreports.LogStatus;


public class FinekyAPITest extends BaseClass{
	
	TestBase testBase;
	String serviceUrl;
	String url;
	String apiUrl;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;
	String accessToken;
	String refreshToken;
	int i;

	  
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = testBase.prop.getProperty("URL");

	}
	
	String currentDir=System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\utils\\FinkeyData.xlsx";
		
	@DataProvider(name="LoginData")
		public Object[][] LoginData() 
		{
			ExcelDataConfig config=new ExcelDataConfig(currentDir);
			int row=config.GetRowCount(0);
			
			//System.out.println(row);
			
			Object[][] data=new Object[row][4];
			for(i=0;i<row;i++)
			{
			data[i][0]=config.ExcelGetData(0, i, 0);
			data[i][1]=config.ExcelGetData(0, i, 1);
			data[i][2]=config.ExcelGetData(0, i, 2);
			data[i][3]=config.ExcelGetData(0, i, 3);
			
			}
			
			return data;
		
		}
		
		
		@Test(dataProvider = "LoginData",priority=1)
		public void login(String email,String password,String testCase,String code) throws JsonGenerationException, JsonMappingException, IOException {
	
			 sheet= book.getSheetAt(0); 
			restClient = new RestClient();

			apiUrl = "/login";
			url = serviceUrl + apiUrl;
			
			int code1=Integer.parseInt(code);
			// jackson API:
			ObjectMapper mapper = new ObjectMapper();
			Login login = new Login(email,password); // expected users obejct

			// object to json file:
			mapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/login.json"),
					login);

			// java object to json in String:
			String loginJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(login);
			System.out.println(loginJsonString);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case Name: " + testCase);
			closebaleHttpResponse = restClient.postWithoutHeaders(url, loginJsonString); // call the API
			System.out.println("The URL for postLogin Method is : " + url);
			ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
			ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + loginJsonString);
			
			// 1. JsonString:

			String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("The response from API is:" + responseString);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseString);
	
	

			// validate response from API:
			// 2. status code:
			int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
	
			System.out.println("The Status Code is " + statusCode);	
			ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
			Assert.assertEquals(statusCode, code1);
			
			int i=0;

			sheet.getRow(i).getCell(4).setCellValue(statusCode);	
			sheet.getRow(i).getCell(5).setCellValue(responseString);	

	 
		   if(statusCode==code1)
		   {
		
			sheet.getRow(i).getCell(6).setCellValue("Pass");
		   }
		   
		   else
		   {
			   sheet.getRow(i).getCell(6).setCellValue("Fail");
		   }
		 //  i++;
	
		  
			if(statusCode==200)
			{
			// 3. All Headers
			Header[] headersArray = closebaleHttpResponse.getAllHeaders();
			// HashMap<String, String> allHeaders = new HashMap<String, String>();
			for (Header header : headersArray) {
				System.out.println("Key : " + header.getName() + " ,Value : " + header.getValue());
			}
			
			accessToken = closebaleHttpResponse.getFirstHeader("accessToken").getValue();
			System.out.println("The Access Token Id is : " + accessToken);
			refreshToken = closebaleHttpResponse.getFirstHeader("refreshToken").getValue();
			System.out.println("The refresh Token Id is : " + refreshToken);
			ExtentTestManager.getTest().log(LogStatus.INFO, "The Access Token Id is : " + accessToken);

			ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
			}
			

	
		}

	

	//@DataProvider(name="RegisterData")
	public Object[][] RegisterData() throws Exception
	{
		ExcelReader excel=new ExcelReader();
		return excel.readFilenSheet(currentDir, "Register");
	}
	
		
	
	//@Test(dataProvider = "RegisterData",priority=2)
	public void Register(Map mObj) throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();

		apiUrl = "/register";
		url = serviceUrl + apiUrl;
		
		String firstName=(String) mObj.get("firstName");
		String lastName=(String) mObj.get("lastName");
		String email=(String) mObj.get("email");
		String password=(String) mObj.get("password");
		String testCase=(String) mObj.get("testCase");
		String Code=(String) mObj.get("Code");

		int code1=Integer.parseInt(Code);

		 sheet= book.getSheetAt(1); 
		 
		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Register register = new Register(firstName,lastName,email,password); // expected users obejct

		// object to json file:
		mapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/register.json"),
				register);

		// java object to json in String:
		String registerJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(register);
		System.out.println(registerJsonString);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case Name: " + testCase);
		closebaleHttpResponse = restClient.postWithoutHeaders(url, registerJsonString); // call the API
		System.out.println("The URL for Register Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + registerJsonString);
		

		// 1. JsonString:

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseString);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseString);

		// validate response from API:
		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, code1);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		
		sheet.getRow(1).getCell(6).setCellValue(statusCode);	
		sheet.getRow(1).getCell(7).setCellValue(responseString);	
		
		   if(statusCode==code1)
		   {
		
			sheet.getRow(1).getCell(8).setCellValue("Pass");
		   }
		   
		   else
		   {
			   sheet.getRow(1).getCell(6).setCellValue("Fail");
		   }

		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");

	}

	
	@Test(priority=3)
	public void RefreshToken() throws ClientProtocolException, IOException, URISyntaxException
	{
		restClient = new RestClient();

		apiUrl = "/refreshToken";
		url = serviceUrl + apiUrl;
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", accessToken);

		// jackson API:
				ObjectMapper mapper = new ObjectMapper();
				RefreshToken refreshToken1=new RefreshToken(refreshToken);
		

				// object to json file:
				mapper.writeValue(new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/refreshToken.json"),
						refreshToken1);

				// java object to json in String:
				String refreshJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(refreshToken1);
				System.out.println(refreshJsonString);
				closebaleHttpResponse = restClient.postWithHeaders(url, refreshJsonString, headerMap);  // call the API
				System.out.println("The URL for RefreshToken Method is : " + url);
				ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
				ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + refreshJsonString);
				

		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
	}
		
	@Test(priority = 4)
		public void getUserProfile() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
			restClient = new RestClient();

			apiUrl = "/viewProfile";
			url = serviceUrl + apiUrl;
			
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Authorization", accessToken);
	
	
			closebaleHttpResponse = restClient.getWithHeaders(url, headerMap); // call the API
			System.out.println("The URL for getPrivacySettings Method is : " + url);
			ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);

			// validate response from API:
			// 1. JsonString:
			String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("The response from API is:" + responseJson);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseJson);

			// 2. status code:
			int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
			System.out.println("The Status Code is " + statusCode);
			Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
			ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);

			ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
		}
	
	@Test(priority=5)
	public void UpdateProfile() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/updateProfile";
		url = serviceUrl + apiUrl;

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", accessToken);

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		UpdateProfile updateProfile=new UpdateProfile("Yangjee","Rai");
		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/updateProfile.json"),
				updateProfile);
		// java object to json in String:
		String ProfileJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateProfile);
		System.out.println(ProfileJsonString);
		closebaleHttpResponse = restClient.postWithHeaders(url, ProfileJsonString, headerMap); // call the API
		System.out.println("The URL for postStoriesCommentAsText Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + ProfileJsonString);

		// validate response from API:
		// 1. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseJson);

		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
	

}
	
	
	@Test(priority=6)
	public void VerificationEmail() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/resendVerificationEmail";
		url = serviceUrl + apiUrl;

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		VerifyEmail verifyEmail=new VerifyEmail("yangjee@getnada.com");
		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/verifyEmail.json"),
				verifyEmail);
		// java object to json in String:
		String VerifyEmailJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(verifyEmail);
		System.out.println(VerifyEmailJsonString);
		closebaleHttpResponse = restClient.postWithoutHeaders(url, VerifyEmailJsonString); 
		System.out.println("The URL for postStoriesCommentAsText Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + VerifyEmailJsonString);

		// validate response from API:
		// 1. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseJson);

		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
	

}
	
	@Test(priority=7)
	public void ForgotPassword() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/forgotPassword";
		url = serviceUrl + apiUrl;

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		VerifyEmail forgotPassword=new VerifyEmail("yangjee@getnada.com");
		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/verifyEmail.json"),
				forgotPassword);
		// java object to json in String:
		String forgotPasswordlJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(forgotPassword);
		System.out.println(forgotPasswordlJsonString);
		closebaleHttpResponse = restClient.postWithoutHeaders(url, forgotPasswordlJsonString); 
		System.out.println("The URL for postStoriesCommentAsText Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + forgotPasswordlJsonString);

		// validate response from API:
		// 1. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseJson);

		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
	

}
	
	@Test(priority=8)
	public void ChangePassword() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/changePassword";
		url = serviceUrl + apiUrl;

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", accessToken);

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		ChangePassword changePassword=new ChangePassword("Password1","Password123");
		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/changePassword.json"),
				changePassword);
		// java object to json in String:
		String changePasswordJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(changePassword);
		System.out.println(changePasswordJsonString);
		closebaleHttpResponse = restClient.postWithHeaders(url, changePasswordJsonString, headerMap); 
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + changePasswordJsonString);

		// validate response from API:
		// 1. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseJson);

		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
	}
	
	
	
	@Test(priority = 9)
	public void GetPages() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/getPage";
		url = serviceUrl + apiUrl;
		
		String pages[]= {"terms-and-conditions","privacy-policy"};
		
		for(String getpage:pages)
		{

		
		// jackson API:
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> getPages = new HashMap<String, Object>();
				getPages.put("slug", getpage);

		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/getPage.json"),
				getPages);

		// java object to json in String:
		String getPagesJsonString = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(getPages);
		System.out.println(getPagesJsonString);
		closebaleHttpResponse = restClient.postWithoutHeaders(url, getPagesJsonString); 
		System.out.println("The URL for postDatingPostSettings Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + getPagesJsonString);

		// 1. JsonString:

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

		// JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseString);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseString);

		// validate response from API:
		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
		}

	}
	
	
	@Test(priority = 10)
	public void getProjectStatus() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();

		apiUrl = "/getProjectStatus";
		url = serviceUrl + apiUrl;

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", accessToken);

		closebaleHttpResponse = restClient.getWithHeaders(url, headerMap); // call the API
		System.out.println("The URL for getPrivacySettings Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);

		// validate response from API:
		// 1. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseJson);

		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);

		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
	}
	
	@Test(priority = 11)
	public void CreateProject() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/createUpdateProject";
		url = serviceUrl + apiUrl;
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", accessToken);
		
		// jackson API:
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> createProject = new HashMap<String, Object>();
				createProject.put("id", "0");
				createProject.put("name", "Four Sisters Travels");
				createProject.put("firstName", "Yangjee");
				createProject.put("middleName", "Rai");
				createProject.put("lastName", "Shrestha");
				createProject.put("gender", "Female");
				createProject.put("dateOfBirth", "2000-01-01");
				createProject.put("SSN", "420215972");
				createProject.put("annualIncome", "$3000");
				createProject.put("email", "yangjee.ebpearls@gmail.com");
				createProject.put("contactNumber", "1234657");

		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/createProject.json"),
				createProject);

		// java object to json in String:
		String createProjectJsonString = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(createProject);
		System.out.println(createProjectJsonString);
		closebaleHttpResponse = restClient.postWithHeaders(url, createProjectJsonString,headerMap); 
		System.out.println("The URL for postDatingPostSettings Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + createProjectJsonString);

		// 1. JsonString:

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

		// JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseString);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseString);

		// validate response from API:
		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
		

	}
	
	@SuppressWarnings("serial")
	@Test(priority = 12)
	public void AddPropertyDetails() throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException {
		restClient = new RestClient();
		apiUrl = "/addPropertyDetails";
		url = serviceUrl + apiUrl;
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Authorization", accessToken);
		
		// jackson API:
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> addProperty = new HashMap<String, Object>();
				addProperty.put("projectId", "5dd3cb3b260b1572132b9a15");
				addProperty.put("propertyDetails",new HashMap<String,String>(){
					{
				put("address1", "14 Henry Avenue Brooklyn, NY 11213");
				put("address2", "14 Henry Avenue Brooklyn, NY 11213");
				put("streetNum", "14");
				put("streetName", "Henry Avenue");
				put("streetType", "Henry Avenue");
				put("city", "Brooklyn");
				put("stateProv","New York");
				put("postalCode", "11213");
				put("country", "United States");
				
					}
				});

		// object to json file:
		mapper.writeValue(
				new File(System.getProperty("user.dir") + "/src/main/java/com/qa/data/addProperty.json"),
				addProperty);

		// java object to json in String:
		String addPropertyJsonString = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(addProperty);
		System.out.println(addPropertyJsonString);
		closebaleHttpResponse = restClient.postWithHeaders(url, addPropertyJsonString,headerMap); 
		System.out.println("The URL for postDatingPostSettings Method is : " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " + url);
		ExtentTestManager.getTest().log(LogStatus.INFO, "The Input Data is: " + addPropertyJsonString);

		// 1. JsonString:

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

		// JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseString);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + responseString);

		// validate response from API:
		// 2. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is " + statusCode);
		Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code is: " + statusCode);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
		

	}
	
	
}
