package Dwell;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SignUp extends Elements {
	
	AppiumDriver<MobileElement> driver;
	Elements obj=new Elements();
	
	
	By signup=By.xpath(obj.signup);
	By login=By.xpath(obj.login);
	By pre=By.xpath(obj.pre);
	By fname=By.xpath(obj.fname);
	By lname=By.xpath(obj.lname);
	By email=By.id(obj.email);
	By pwd=By.xpath(obj.pwd);
	By dob=By.xpath(obj.dob);
	By date=By.xpath(obj.date);
	By ok=By.id(obj.ok);
	By alert=By.id(obj.alert);
	By fwp_email=By.xpath(obj.fwp_email);
	By reset=By.xpath(obj.reset);
	By fwp_link=By.id(obj.fwp_link);
	By inbox=By.xpath(obj.inbox);
	By login1=By.xpath(obj.login1);
	By pass=By.xpath(obj.pass);
	By email1=By.xpath(obj.email1);
	By more=By.xpath(obj.more);
	By logout=By.xpath(obj.logout);
	By tab_ten=By.id(obj.tab_ten);
	By land_prof=By.id(obj.land_profile);
	
	
			
	
	public SignUp(AppiumDriver<MobileElement> driver2) 
	{
		this.driver=driver2;

    }
	
	public void clicksignup() 
	{
		MobileElement clt=driver.findElement(signup);
		clt.click();

    }
	
	public void clicklogin() 
	{
		MobileElement clt=driver.findElement(login);
		clt.click();

    }
	
	public void clickpre() 
	{
		MobileElement clt=driver.findElement(pre);
		clt.click();

    }
	
	public void clickok() 
	{
		driver.findElement(ok).click();
		

    }
	
	
	public void setfname(String fname1) 
	{
	driver.findElement(fname).sendKeys(fname1);

    }
	
	public void setlname(String lname1) 
	{
	driver.findElement(lname).sendKeys(lname1);

    }
	
	public void setemail(String email1) 
	{
	driver.findElement(email).sendKeys(email1);

    }
	
	public void setpwd(String pass) 
	{
	driver.findElement(pwd).sendKeys(pass);

    }
	

	public void clickdob() 
	{
	driver.findElement(dob).click();
	driver.findElement(date).click();
	driver.findElement(ok).click();
    }
	
	
	public void signup(String fname, String lname, String email, String pass)
	{
	
		this.setfname(fname);
		this.setlname(lname);
		this.setemail(email);
		this.setpwd(pass);
		this.clickdob();
				
	}
	
	public String getalert() 
	{
	return driver.findElement(alert).getText();

    }
	
	public void clickfwp() 
	{
		driver.findElement(fwp_link).click();
		

    }

	
	public void setfwpemail(String fwp) 
	{
	driver.findElement(fwp_email).sendKeys(fwp);

    }
	
	

	public void clickreset()
	{
	driver.findElement(reset).click();

    }
	
	public void forgetpwd(String fwp)
	{
	
		this.clickfwp();
		this.setfwpemail(fwp);
		this.clickreset();
				
	}
	
	public void clicklogin1() 
	{
		driver.findElement(login1).click();

    }
	
	public void setemail1(String mail) 
	{
	driver.findElement(email1).sendKeys(mail);

    }
	
	public void setpass(String pass1) 
	{
	driver.findElement(pass).sendKeys(pass1);

    }
	
	public void login(String email, String pass)
	{
	
		this.setemail1(email);
		this.setpass(pass);
		this.clicklogin1();
				
	}
	
	public void clickmore() 
	{
		driver.findElement(more).click();
		

    }
	
	public void logout() 
	{
		this.clickmore();
		driver.findElement(logout).click();
		this.clickok();
		

    }
	
	public void clicktabten() 
	{
		driver.findElement(tab_ten).click();		

    }
	
	public void setlandprof(String loc) 
	{
	
		driver.findElement(land_prof).sendKeys(loc);

    }
	
	
	public void loginstep1(String loc)
	{
	
		this.setlandprof(loc);
				
	}
	

}
