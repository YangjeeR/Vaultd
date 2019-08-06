package Modules;

import java.io.IOException;

import org.testng.annotations.Test;

import Listeners.BaseClass;
import ElementsPath.Elements;

public class Login extends BaseClass {
	
	Elements elementPath=new Elements();
	
	public void Login()  
	{
		
		try
		{
		//click login btn in landing screen
		ClickBtn(elementPath.login);
		Thread.sleep(5000);
		//Enter data and click login
		SetText(elementPath.email1,"yangjee3@getnada.com");
		SetText(elementPath.pass,"password1");
		ClickBtn(elementPath.login1); 
		
		locator=elementPath.land_profile;
		
		String profile=GetText();
		if(profile.equalsIgnoreCase("Profile Set Up"))//checking login profile title
		{
			System.out.println("User has been successfully logged in.");	
		}
		else
		{
			System.out.println("Unable to log in");
		}
		}
		
		catch(Exception e)
		{
			System.out.println("Error Message:" + e.getMessage());
		}
		
	}

}
