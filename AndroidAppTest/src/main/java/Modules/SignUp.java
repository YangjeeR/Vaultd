package Modules;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ElementsPath.Elements;
import Listeners.BaseClass;


public class SignUp extends BaseClass {
	
	WebDriver webdriver=null;
	Elements elementPath=new Elements();
	Login loginObj=new Login();

	String email="yangjee15";
	
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
		SetText(elementPath.email,email+"@getnada.com");
		SetText(elementPath.pwd,"password1");
		ClickBtn(elementPath.dob); 
		ClickBtn(elementPath.date); 
		ClickBtn(elementPath.ok); 
		SwipeVertical();
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
			tearDown();
		}
		
		else
			
		{
			
			ClickBtn(elementPath.ok1); 
			System.out.println("Unable to register new user.");
			ClickBtn(elementPath.backbtn);
			Thread.sleep(5000);
			loginObj.LoginTest();
		}
		
	}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	

		
	 }
	
	
	public void VerifyEmailLink() throws InterruptedException
	{

	System.setProperty("webdriver.chrome.driver","C:\\Users\\yangjee\\Desktop\\chromedriver\\chromedriver.exe");
	webdriver=new ChromeDriver();
	webdriver.manage().window().maximize();
	webdriver.get("https://getnada.com/");
	webdriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[2]/nav/a[2]/span")).click();
	webdriver.findElement(By.xpath("//input[@class='user_name']")).clear();//enter email
	webdriver.findElement(By.xpath("//input[@class='user_name']")).sendKeys(email);//enter email
	webdriver.findElement(By.xpath("//*[@id='app']/div/div[1]/footer/a[2]")).click();//click inbox
	Thread.sleep(2000);
	webdriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div/div[1]/ul/li/div/div[2]")).click();
	Thread.sleep(5000);
	webdriver.switchTo().frame("idIframe");
	webdriver.findElement(By.linkText("I verify this is my email")).click(); 
    System.out.println(email + "@getnada.com" + " Email has been successfully verified.");
   
	}
	
	
	public void tearDown()
	{
		webdriver.quit();
	}
}
