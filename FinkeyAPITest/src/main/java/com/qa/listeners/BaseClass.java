package com.qa.listeners;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

/**
 * 
 * Date :- 13/07/2019
 * For the reporting purpose, we are using Extent Reports. 
 * It generates beautiful HTML reports. We use the extent reports for maintaining logs and also to include the screenshots of failed test cases in the Extent Report.
 * @author Roshan Choudhary
 * @version 1.0
 *
 */
public abstract class BaseClass {
   
	/**
	 * 
	 * @param method - This will be invoked before any Test Method
	 */
	@BeforeMethod
    public void beforeMethod(Method method) {
        ExtentTestManager.startTest(method.getName());
    }
    
	/**
	 * 
	 * @param result - It will log the result whether test pass, fail or skip
	 */
    @AfterMethod
    protected void afterMethod(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        }
        
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());        
        ExtentManager.getReporter().flush();
    }
    
    /**
     * 
     * @param t - The Throwable class is the superclass of all errors and exceptions
     * @return - It will return the result whether test pass, fail or skip
     */
    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
    
    protected XSSFSheet sheet;
    protected XSSFWorkbook book;
	
	@BeforeMethod
	public void read() throws IOException
	{
	
	
	File src=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\utils\\FinkeyData.xlsx");	
			
			  // Load the file

	      FileInputStream fis=new FileInputStream(src);
	      ZipSecureFile.setMinInflateRatio(-1.0d);
	     // load the workbook
	    book=new XSSFWorkbook(fis);
	    
	    //sheet= book.getSheetAt(0); 
	    //int row=sh1.getRow(1).getLastCellNum();
	    //System.out.println(sheet.getRow(1).getCell(3).getStringCellValue());
	   
	}
	
@AfterMethod
public void write() throws IOException
{
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\utils\\FinkeyData.xlsx");                                 
		
		  // finally write content		   
		book.write(fos);
		   
		  // close the file
		   
		   fos.close();
		
}
}