package Modules;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ElementsPath.Elements;
import Listeners.BaseClass;

public class SignUp extends BaseClass {
	Elements elementPath=new Elements();
	WebDriver driver;
	
	@Test
	public void Signup()  
	{
		
		try
		{
		//click sign up btn in landing screen
		ClickBtn(elementPath.signup);
		Thread.sleep(5000);
		//Enter data and click sign up
		SetText(elementPath.fname,"Yangjee");
		SetText(elementPath.lname,"Rai");
		SetText(elementPath.email,"yangjee8@getnada.com");
		SetText(elementPath.pwd,"password1");
		ClickBtn(elementPath.dob); 
		ClickBtn(elementPath.date); 
		ClickBtn(elementPath.ok); 
		//verticalSwipe();
		ClickBtn(elementPath.signup);
		Thread.sleep(10000);
		
		locator=elementPath.alert1;
		
		String alert_title=GetText();
		System.out.println("Alert : " + alert_title);
		
		if(alert_title.equalsIgnoreCase("Registration Successful"))
		{
			System.out.println("New user has been successfully registered.");
			ClickBtn(elementPath.ok1); 
			Thread.sleep(5000);
			VerifyEmailLink();
		}
		
		else
			
		{
			System.out.println("Unable to register new user.");
		}
		
		
		
	} catch (InterruptedException e) {
		 System.out.println("Unable to test sign up page");
	     System.out.println(e.getMessage());
	 }
		
	 }
	
	
	public void VerifyEmailLink() throws InterruptedException
	{

		 
    String email="yangjee8";
	System.setProperty("webdriver.chrome.driver","C:\\Users\\yangjee\\Desktop\\chromedriver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://getnada.com/");
	driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).click();
	driver.findElement(By.xpath("//input[@class='user_name']")).clear();//enter email
	driver.findElement(By.xpath("//input[@class='user_name']")).sendKeys(email);//enter email
	driver.findElement(By.xpath("//*[@id='app']/div/div[1]/footer/a[2]")).click();//click inbox
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/ul/li/div/div[2]")).click();
	Thread.sleep(5000);
    driver.switchTo().frame("idIframe");
    driver.findElement(By.linkText("I verify this is my email")).click(); 
    System.out.println(email + "@getnada.com" + " Email has been successfully verified.");
   
	}
}
