package Listeners;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import static io.appium.java_client.touch.offset.PointOption.point;
import static io.appium.java_client.touch.WaitOptions.waitOptions;

public class BaseClass{
	
	public static AppiumDriver<MobileElement> driver=null;
	public Properties Config=new Properties();
	public static String locator=null;

	

	@BeforeTest
	public void setUp() throws IOException {
		
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Config\\config.properties");
			Config.load(fis);
			
			
			 System.out.println("Session is creating");
			 DesiredCapabilities cap=new DesiredCapabilities();
			 cap.setCapability("platformName",Config.getProperty("platformName"));
			 cap.setCapability("deviceName",Config.getProperty("deviceName")); 
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
	
	public void SwipeVertical()
	{
		  Dimension size = driver.manage().window().getSize();
	        int width = (int) (size.width/2);
	        int startPoint = (int) (size.height * 0.80);
	        int endPoint = (int) (size.height * 0.20);
	 
	        new TouchAction(driver)
	                .press(point(width, startPoint))
	                .waitAction(waitOptions(Duration.ofMillis(2000)))
	                .moveTo(point(width, endPoint))
	                .release().perform();
	}
	

	
}

