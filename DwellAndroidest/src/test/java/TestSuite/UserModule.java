package TestSuite;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import Modules.ProfileSetUpLandlord;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import Modules.SignUp;

public class UserModule {
	
	AppiumDriver<MobileElement> driver;
	SignUp signup;
	ProfileSetUpLandlord landnew;
	
	@Test(priority=1,description="Creating App session and opening app")
	public void setup_server( ) throws IOException, InterruptedException
	{
		
	 System.out.println("Session is creating");
	 DesiredCapabilities cap=new DesiredCapabilities();
	 cap.setCapability("platformName","Android");
	 cap.setCapability("deviceName", "S7 Edge"); 
	 cap.setCapability("platformVersion", "8.0");
	 cap.setCapability("noReset",true);
	 cap.setCapability("appPackage", "com.app.dwell");
	 cap.setCapability("appActivity", "ui.splash.SplashScreenActivity");
	 cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
	 
	 try
 {
	 
 driver= new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
 System.out.println("Session is created");   
	 }

 catch(Exception e)
 {
	 System.out.println("Unable to start application.");
     System.out.println(e.getMessage());
 }
	 Thread.sleep(4000);
	 
	
}
	
	@Test(priority=2,description="Click landing page links")
	public void clicklinks()
	{
		signup=new SignUp(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		signup.clicklogin();
		//signup.clickpre();
		//signup.clicksignup();
		
		
	}
	
	@Test(priority=3,description="Testing Sign up Page")
	public void SignUp()
	{
		
		
		try {
			signup.signup("Yangjee", "Rai", "yangjee3@yopmail.com", "password1");
			verticalSwipe();
			signup.clicksignup();
			Thread.sleep(10000);
			
			String alert_title=signup.getalert();
			
			if(alert_title.equalsIgnoreCase("Registration Successful"))
			{
				System.out.println("New user has been successfully registered.");
				signup.clickok();
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
	
//	@Test(priority=4,description="Testing Forgot Password Page")
	public void forgetpass()
	{
		try {
			signup.forgetpwd("landlord1@yopmail.com");
			Thread.sleep(10000);
			
			String alert_title=signup.getalert();
			
			if(alert_title.equalsIgnoreCase("Reset Password Successful"))
			{
				System.out.println("Email has been sent to reset password.");
				signup.clickok();
			}
			
			else
				
			{
				System.out.println("Unable to send reset email.");
			}
			
			
			
		} catch (InterruptedException e) {
			 System.out.println("Unable to test forgot password page");
		     System.out.println(e.getMessage());
		}
		
		
	}
	
	
	//@Test(priority=5,description="Testing Login Page when user has already set up landlord profile")
		public void Login()
		{
			
			
			try {
				signup.login("landlord1@yopmail.com", "password1");
				Thread.sleep(10000);
				
				MobileElement inbox=driver.findElement(signup.inbox);
				
				if(inbox.isDisplayed())
				{
					System.out.println("New user has been successfully logged in.");
					signup.logout();
				}
				
				else
					
				{
					System.out.println("Unable to logged in.");
				}
				
				
				
			} catch (InterruptedException e) {
				 System.out.println("Unable to test login page");
			     System.out.println(e.getMessage());
			}
			
		}
		
		
		
		@Test(priority=5,description="Testing Login Page with new user's landlord profile set up")
				public void LoginNewLandlord()
				{
					
					
					try {
						landnew=new ProfileSetUpLandlord(driver);
						signup.login("y.land@yopmail.com", "password1");
						Thread.sleep(10000);
						
						//Step 1 profile set up
						//landnew.loginstep1("0491570967","bris");
						//verticalSwipe();
						//landnew.clicknext();
						
						//Step 2 Add card details
						/*verticalSwipe();
						Thread.sleep(2000);
						landnew.clicknext(); //trial page
						//add card details
						
						landnew.loginstep2("4111111111111111","123","Yangjee Rai");
						*/
						//add bank details
						landnew.loginstep2_bank("12345a","123456","yangjee","world bank");
						landnew.clicknext();
						
						//add property Step 3
						
						landnew.loginstep3_pro("bris","400");
						
						/*MobileElement inbox=driver.findElement(signup.inbox);
						
						if(inbox.isDisplayed())
						{
							System.out.println("Profile set up Step 1 has been completed.");
							signup.logout();
						}
						
						else
							
						{
							System.out.println("Issue in profile set up Step 1.");
						}
						*/
						
						
					} catch (InterruptedException e) {
						 System.out.println("Unable to test landlord profile set up process");
					     System.out.println(e.getMessage());
					}
					
				}
		
		public void verticalSwipe() throws InterruptedException {
			
			Dimension dim=driver.manage().window().getSize();
			int height=dim.getHeight();
			int width=dim.getWidth();
			int x= width/2;
			int starty=(int)(height*0.80);
			int endy=(int)(height*0.20);
			driver.swipe(x, starty, x, endy, 2000);
			//driver.swipe(x, endy, x, starty, 3000);
			Thread.sleep(2000);
		}
		


}
