package Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import ElementsPath.Elements;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ProfileSetUpLandlord extends Elements {
	
	AppiumDriver<MobileElement> driver;
	Elements obj=new Elements();
	

	By ok=By.id(obj.ok);
	By alert=By.id(obj.alert);
	By land_prof=By.xpath(obj.land_profile);
	By land_phone=By.xpath(obj.land_phone);
	By street=By.id(obj.street);
	By next=By.xpath(obj.next);
	By street_text=By.id(obj.street_text);
	By first_street=By.xpath(obj.first_street);
	By next_btn=By.id(obj.next_btn);
	By first_image=By.xpath(obj.first_image);
	By files=By.xpath(obj.files);
	By add_card=By.id(obj.add_card);
	By card_num=By.id(obj.card_num);
	By expiry_date=By.xpath(obj.expiry_date);
	By cal_next=By.id(obj.cal_next);
	By expiry_date1=By.xpath(obj.expiry_date1);
	By cvc=By.xpath(obj.cvc);
	By full_name=By.xpath(obj.full_name);
	By add_btn=By.xpath(obj.add_btn);
	By add_bank=By.id(obj.add_bank);
	By ac_num=By.xpath(obj.ac_num);
	By bsb=By.xpath(obj.bsb);
	By bankname=By.xpath(obj.bankname);
	By ac_name=By.xpath(obj.ac_name);
	By ac_type=By.id(obj.ac_type);
	By ac_type1=By.xpath(obj.ac_type1);
	By hold_type=By.id(obj.hold_type);
	By hold_type1=By.xpath(obj.hold_type1);
	By pro_street=By.xpath(obj.pro_street);
	By pro_type=By.id(obj.pro_type);
	By house=By.xpath(obj.house);
	By rent_price=By.xpath(obj.rent_price);
	By rent_fre=By.id(obj.rent_fre);
	By rent_week=By.xpath(obj.rent_week);
	By pet_on=By.id(obj.pet_on);

	
	
			
	
	public ProfileSetUpLandlord(AppiumDriver<MobileElement> driver2) 
	{
		this.driver=driver2;

    }
	
	public void clickprofimg() 
	{
	
		driver.findElement(land_prof).click();

    }
	
	public void click() 
	{
	
		driver.findElement(land_prof).click();

    }
	
	
	public void clickfile()
	{
	
		driver.findElement(files).click();

    }
	
	public void setprofimage()
	{
	
		driver.findElement(first_image).click();

    }
	
	public void setland_phone(String phone) 
	{
	
		driver.findElement(land_phone).sendKeys(phone);

    }
	
	
	public void setstreet(String street_name) throws InterruptedException 
	{
		driver.findElement(street).click();
		Thread.sleep(5000);
		driver.findElement(street_text).sendKeys(street_name);
		Thread.sleep(10000);
		driver.findElement(first_street).click();
		Thread.sleep(5000);

    }
	
	
	
	public void clicknext() 
	{
	
		driver.findElement(next_btn).click();

    }
	
	
	public void click_next() 
	{
	
		driver.findElement(next).click();

    }
	
	//step 2 card details
	public void clickadd_card() 
	{
	
		driver.findElement(add_card).click();

    }
	
	public void setcard_num(String card) 
	{
	
		driver.findElement(card_num).sendKeys(card);

    }
	
	public void expiry_date() 
	{
	
		driver.findElement(expiry_date).click();
		driver.findElement(cal_next).click();
		driver.findElement(expiry_date1).click();
		driver.findElement(ok).click();

    }
	
	public void setcvc(String ccv) 
	{
	
		driver.findElement(cvc).sendKeys(ccv);

    }
	
	
	public void setfullname(String name) 
	{
	
		driver.findElement(full_name).sendKeys(name);

    }
	
	
	public void clickadd_btn() 
	{
	
		driver.findElement(add_btn).click();

    }
	
	//bank details
	public void clickadd_bank() 
	{
	
		driver.findElement(add_bank).click();

    }
	
	
	
	public void setacc_num(String num) 
	{
	
		driver.findElement(ac_num).sendKeys(num);
    }
	
	public void setbsb(String bsb1) 
	{
	
		driver.findElement(bsb).sendKeys(bsb1);
    }
	
	public void setacc_name(String aname) 
	{
	
		driver.findElement(ac_name).sendKeys(aname);
    }
	
	public void setbankname(String bname) 
	{
	
		driver.findElement(bankname).sendKeys(bname);
    }
	
	
	public void clickac_type() 
	{
	
		driver.findElement(ac_type).click();

    }
	
	public void clickac_type1() 
	{
	
		driver.findElement(ac_type1).click();

    }
	
	
	
	public void clickhold_type() 
	{
	
		driver.findElement(hold_type).click();

    }
	
	public void clickhold_type1() 
	{
	
		driver.findElement(hold_type1).click();

    }

	
	//step 3 
	public void setstreetpro(String street_name) throws InterruptedException 
	{
		driver.findElement(pro_street).click();
		Thread.sleep(5000);
		driver.findElement(street_text).sendKeys(street_name);
		Thread.sleep(10000);
		driver.findElement(first_street).click();
		Thread.sleep(5000);

    }
	
	public void clickpro_type() 
	{
	
		driver.findElement(pro_type).click();
		driver.findElement(house).click();

    }
	
	public void clickrent_fre() 
	{
	
		driver.findElement(rent_fre).click();
		driver.findElement(rent_week).click();

    }
	
	public void setrent_price(String price) 
	{
	
		driver.findElement(rent_price).sendKeys(price);
    }
	
	public void clickpet() 
	{
	
		driver.findElement(pet_on).click();

    }
	
	
	public void loginstep1(String phone,String street_name) throws InterruptedException
	{
		Thread.sleep(2000);
		this.clickprofimg();
		this.clickfile();
		Thread.sleep(5000);
		this.setprofimage();
		this.setland_phone(phone);
		this.setstreet(street_name);
		Thread.sleep(3000);
		
				
	}
	
	public void loginstep2(String cardnum,String cvc,String fullname) throws InterruptedException
	{
		Thread.sleep(2000);
		this.clickadd_card();
		this.setcard_num(cardnum);
		this.expiry_date();
		this.setcvc(cvc);
		this.setfullname(fullname);
		this.clickadd_btn();
		Thread.sleep(3000);	
		this.clicknext(); //authorize
				
	}
	
	public void loginstep2_bank(String num,String bsb1,String aname,String bname) throws InterruptedException
	{
		this.clickadd_bank();
		this.setbsb(bsb1);
		this.setacc_num(num);
		this.setacc_name(aname);
		this.setbankname(bname);
		this.clickac_type();
		this.clickac_type1();
		this.clickhold_type();
		this.clickhold_type1();
		this.clicknext();
				
	}
	
	public void loginstep3_pro(String street_name,String price) throws InterruptedException
	{	
		this.setstreetpro(street_name);
	    verticalSwipe();
	    this.clickpro_type();
	    this.setrent_price(price);
	    this.clickrent_fre();
	    verticalSwipe();
	  	this.clickpet();
	  	this.clicknext();
				
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
