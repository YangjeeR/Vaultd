package Modules;

import Listeners.BaseClass;
import org.testng.annotations.Test;

import ElementsPath.Elements;

public class Login extends BaseClass {
	
	Elements elementPath1=new Elements();
	
	@Test
	public void LoginTest()
	{
		
		try
		{
			
		//click login btn in landing screen
		ClickBtn(elementPath1.login);
		Thread.sleep(5000);
		//Enter data and click login
		SetText(elementPath1.email1,"yangjee7@getnada.com");
		SetText(elementPath1.pass,"password1");
		ClickBtn(elementPath1.login1); 
		Thread.sleep(4000);
		locator=elementPath1.land_profile;
		
		String profile=GetText();
		System.out.println("profile" + profile);
		if(profile.equalsIgnoreCase("Profile setup"))//checking login profile title
		{
			System.out.println("User has been successfully logged in.");	
		}
		else
		{
			System.out.println("Unable to log in");
		}
		
 }


		catch(Throwable e)
		{
	e.printStackTrace();
		}

	}

}
