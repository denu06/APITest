package com.softweb.report;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author Jainik Bakaraniya 01/10/2018
 */
public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports(workingDir+"\\Report\\API_Test_Report.html", true);
        }
        return extent;
    }
}
