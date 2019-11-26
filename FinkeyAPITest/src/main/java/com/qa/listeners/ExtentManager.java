package com.qa.listeners;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * 
 * Date :- 13/07/2019
 * This is ExtentManager class that has path where the extent report to be generated.
 * @author Roshan Choudhary
 * @version 1.0
 *
 */
public class ExtentManager {
    static ExtentReports extent;
    final static String filePath = "./test-output/html/Extent.html";
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }
        
        return extent;
    }
}