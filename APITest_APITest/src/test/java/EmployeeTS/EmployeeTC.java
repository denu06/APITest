package EmployeeTS;

import com.relevantcodes.extentreports.LogStatus;
import com.softweb.config.APICommon;
import com.softweb.payload.APIPayload;
import com.softweb.report.ExtentTestManager;
import com.softweb.util.Constant;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTC extends APICommon implements Constant {



    // POST Call
    @Test(description = "Save Attendance Of Employee API")
    public void saveAttendanceOfEmployeeAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-002: Save Attendance Of Employee API");
            ExtentTestManager.getTest().assignCategory("Module: Employee");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);
            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Employee/SaveAttendance", payload.saveAttendanceOfEmployeePayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

            // Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 : Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode = jsonObj.get("statuscode").toString();
            System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode,"200");


            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload2);
        }   catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
            throw e;
        }
    }



    // POST Call
    @Test(description = "Get All Helper by AnaganwadiID API")
    public void getAllHelperByAnganwadiID() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-003: Get All Helper By AnganwadiID");
            ExtentTestManager.getTest().assignCategory("Module: Employee");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            System.out.println("token:"+token);
            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Employee/GetAllHelperByAnganwadiID", payload.getAllHelperByAnganwadiIDPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
            // Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 : Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode = jsonObj.get("statuscode").toString();
            System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode,"200");


            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload2);

        }   catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }


    // POST Call
    @Test(description = "Get Employee Attendances By Date AnganwadiID API")
    public void getEmployeeAttendancesByDateAnganwadiID() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-004: Get Employee Attendances By Date AnganwadiID");
            ExtentTestManager.getTest().assignCategory("Module: Employee");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            System.out.println("token:"+token);

            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Employee/GetEmployeeAttendancesByDateAnganwadiID", payload.getEmployeeAttendancesByDateAnganwadiIDPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
            // Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 : Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            //System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode = jsonObj.get("statuscode").toString();
            //System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode,"200");


            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload2);

        }   catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }


}
