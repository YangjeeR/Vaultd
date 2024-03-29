package Library;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.WriterOutputStream;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.testng.Reporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import listeners.ExtentTestManager;
import java.io.File;
import java.io.FileWriter;
import org.json.JSONException;


public class MainAPI {
	public  StringWriter requestWriter;
	public  PrintStream requestCapture;
	String family_id;
	String familyid1;
	String familyfin;

	public MainAPI()
	{
		
	requestWriter=new StringWriter();
	requestCapture=new PrintStream(new WriterOutputStream(requestWriter),true);
	}
	
	public void PostAPI(String accessToken,String endpoint,FileInputStream fileInputStream) throws IOException
	{
		ValidatableResponse res =
				given()
				.log().all()
				.filter(new RequestLoggingFilter(requestCapture))
				.contentType(ContentType.JSON)
				.header("Authorization",accessToken)
				.body(IOUtils.toString(fileInputStream,"UTF-8"))
			.when()   
				.post(endpoint)
			.then();

		   System.out.println("accessToken" + accessToken);
		    System.out.println("endpoint" + endpoint);
		    System.out.println("FileInputStream" + fileInputStream);
		Reporter.log("Response is: " + res.extract().asString(), true);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Request  is: " + requestWriter.toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + res.extract().asString());	

		res.statusCode(200);
		res.body("message", Matchers.notNullValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");		
					
	}

	public void GetAPI(String accessToken,String endpoint,FileInputStream fileInputStream) throws IOException
	{
		
		ValidatableResponse res =
				given()
				.log().all()
				.filter(new RequestLoggingFilter(requestCapture))
				.contentType(ContentType.JSON)
				.header("Authorization",accessToken)
				.body(IOUtils.toString(fileInputStream,"UTF-8"))
			.when()   
				.post(endpoint)
			.then();

		
		Reporter.log("Response is: " + res.extract().asString(), true);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Request  is: " + requestWriter.toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + res.extract().asString());	

		res.statusCode(200);
		res.body("message", Matchers.notNullValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");		
					
	}
	
	
	public void PutAPI(String accessToken,String endpoint,FileInputStream fileInputStream) throws IOException
	{	
		ValidatableResponse res =
				given()
				.log().all()
				.filter(new RequestLoggingFilter(requestCapture))
				.contentType(ContentType.JSON)
				.header("Authorization",accessToken)
				.body(IOUtils.toString(fileInputStream,"UTF-8"))
			.when()   
				.post(endpoint)
			.then();

		
		Reporter.log("Response is: " + res.extract().asString(), true);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Request  is: " + requestWriter.toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + res.extract().asString());	
	
		res.statusCode(200);
		res.body("message", Matchers.notNullValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");		
					
	}

	public void GetAPIWithParam(String accessToken,String endpoint,String param_name,String param) throws IOException
	{
			
		ValidatableResponse res =
				given()
				.log().all()
				.filter(new RequestLoggingFilter(requestCapture))
				.contentType(ContentType.JSON)
				.header("Authorization",accessToken)
				.queryParam(param_name, param)
			.when()   
				.get(endpoint)
			.then();

		
		Reporter.log("Response is: " + res.extract().asString(), true);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Parameter value:" + param);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Request  is: " + requestWriter.toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Response is: " + res.extract().asString());	

		res.statusCode(200);
		res.body("message", Matchers.notNullValue());
		ExtentTestManager.getTest().log(LogStatus.INFO, "Have verified the Status Code Successfully !!");
		
    	
        if(param=="family")
        {
        	family_id=res.extract().body().path("data._id").toString();
        	System.out.println("family_id" + family_id); 
        	ExtentTestManager.getTest().log(LogStatus.INFO, "family_id value:" + family_id);
        	String strArray[] = family_id.split(",");
        	familyid1=strArray[0];
        	familyfin=familyid1.substring(1);
        	ExtentTestManager.getTest().log(LogStatus.INFO, "family_id1 value:" + familyfin);
        	//UpdateJsonfile();
        }
        
		
	}
	
	
	public void UpdateJsonfile() throws IOException
	{
		
		JSONObject obj = new JSONObject();
		obj.put("localId", "1234");
		obj.put("relation", "Spouse");
		obj.put("_id", familyfin);
		obj.put("firstName", "Pratik");
		obj.put("lastName", "Shrestha");
		obj.put("middleName", "");
		obj.put("email", "pratik@gmail.com");
		obj.put("dob", "24/10/2019");
		obj.put("address", "Thaiba");
		obj.put("isDeleted", false);
		obj.put("contactNumber", "9841151757");
		obj.put("nickName", "15353556");
	
		
		
		JSONArray family=new JSONArray();
		family.add(obj);

	
			try (FileWriter file = new FileWriter("C:\\Users\\yangjee\\new_eclipse\\vaultdApiTest\\src\\main\\java\\JsonFiles\\familySync.json"))
			{
				file.write(family.toJSONString());
				
				System.out.println("Successfully Copied JSON Object to File...");
			}
	}
	
}
