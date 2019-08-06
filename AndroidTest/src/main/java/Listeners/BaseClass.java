package Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {
	
	public static Properties Config=new Properties();
	AppiumDriver<MobileElement> driver;
	public String locator;
	
	@BeforeTest
	public void setUp() throws IOException {
		
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Config\\config.properties");
			Config.load(fis);
			
			
			 System.out.println("Session is creating");
			 DesiredCapabilities cap=new DesiredCapabilities();
			 cap.setCapability("platformName",Config.getProperty("platformName"));
			 cap.setCapability("deviceName",Config.getProperty("deviceName") ); 
			 cap.setCapability("platformVersion", Config.getProperty("platformVersion"));
			 cap.setCapability("noReset",true);
			 cap.setCapability("appPackage",Config.getProperty("appPackage") );
			 cap.setCapability("appActivity", Config.getProperty("appActivity"));
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
			
	}
	
	public void ClickBtn(String locator)
	{
		try 
		{
			if(locator.contains("hierarchy")|| locator.startsWith("//"))
			{
			driver.findElement(By.xpath(locator)).click();
			}
			else 
			{
			driver.findElement(By.id(locator)).click();
			}

		}
		
		catch(Exception e)
		{
			
			System.out.println("Error Message:" + e.getMessage());
		}
	}
	
	
	public void SetText(String locator,String text)
	{
		try 
		{
			if(locator.contains("hierarchy")||locator.startsWith("//"))
			{
			driver.findElement(By.xpath(locator)).sendKeys(text);
			}
			else
			{
			driver.findElement(By.id(locator)).sendKeys(text);
			}
		
			driver.hideKeyboard();
		}
		
		catch(Exception e)
		{
			
			System.out.println("Error Message:" + e.getMessage());
		}
	}
	

	public String GetText()
	{
		try 
		{
			if(locator.contains("hierarchy")|| locator.startsWith("//"))
			{
			return driver.findElement(By.xpath(locator)).getText();
			}
			else if(locator.contains(":id"))
			{
			return driver.findElement(By.id(locator)).getText();
			}


			
		}
		
		catch(Exception e)
		{
			
			System.out.println("Error Message:" + e.getMessage());
	
		}
		return locator;
		
	}
}
	
	
/*public void verticalSwipe() throws InterruptedException {
		
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

}*/
