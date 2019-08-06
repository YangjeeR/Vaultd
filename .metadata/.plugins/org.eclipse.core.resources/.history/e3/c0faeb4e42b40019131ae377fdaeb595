package mobiletesting;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class demoapp {
	
	
	AppiumDriver<MobileElement> driver;
	String path;

@Test
public void setup()
{
	
	System.out.print("Session is creating");
	
	path=System.getProperty("user.dir");
	DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("platformName", "Android");
    cap.setCapability("deviceName", "Samsung Galaxy S8");
    cap.setCapability("platformVersion", "7.0");
    cap.setCapability("app", path+"\\app\\demo.apk");
    
    try
    {
    	driver= new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),cap);
       System.out.println("Sesion is created");
    }
    catch(Exception e)
    {
   	 System.out.println("Unable to start application.");
   		 System.out.println(e.getMessage());
    }
   	 
	
	
	
}

}
