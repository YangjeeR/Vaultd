
package mobiletesting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class brokenlinks {

	@Test
	public void links()
	{
	//public static void main(String[] args) {
		WebDriver driver;
		
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("http://fireflynp.co/");
		driver.manage().window().maximize();
		
		List<WebElement> anchor=driver.findElements(By.tagName("a"));
		
		System.out.println("Total number of links : " + anchor.size());
		
		for(int i=0;i<anchor.size();i++)
		{
			WebElement anchor_count=anchor.get(i);
			String anchor_name=anchor_count.getAttribute("href");
			verifylinks(anchor_name);
			
		  
	}
	}

	
		public static void verifylinks(String linkurl)	
		{
		try 
		{
			URL anchor_name=new URL(linkurl);
			HttpURLConnection httpconnect=(HttpURLConnection)anchor_name.openConnection();
			httpconnect.setConnectTimeout(3000);
			httpconnect.connect();
			
			if(httpconnect.getResponseCode()==200)
			{
				System.out.println(linkurl + "-" + httpconnect.getResponseMessage());
			
		}
			if(httpconnect.getResponseCode()==httpconnect.HTTP_NOT_FOUND)
			{
				System.out.println(linkurl + "-" + httpconnect.getResponseMessage() + "-" +httpconnect.HTTP_NOT_FOUND);
				
			}
		}
		catch( Exception e)
		{
			
		}
	}
		
	

}
