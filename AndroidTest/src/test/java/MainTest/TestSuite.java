package MainTest;

import org.testng.annotations.Test;

import ElementsPath.Elements;
import Listeners.BaseClass;
import Modules.Login;
import Modules.SignUp;

public class TestSuite extends BaseClass


{
	
	Elements elementPath=new Elements();
	Login loginObj=new Login();
	SignUp SignUpObj=new SignUp();
	
	//@Test(priority=1)
	public void SignUpModule()
	{
		SignUpObj.Signup();
	}
	
	
	@Test(priority=2)
	public void loginModule()
	{
		loginObj.Login();
	}

}
