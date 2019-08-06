package mobiletesting;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class winauth {
	static WebDriver driver;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.gecko.driver","D:\\geckodriver\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		Runtime.getRuntime().exec("D:\\AutoIT\\handleauth.exe");
		driver.navigate().to("https://www.engprod-charter.net/");
		

	}
	
	

}
