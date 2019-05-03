package com.softweb.tc;

import com.relevantcodes.extentreports.LogStatus;
import com.softweb.config.APICommon;
import com.softweb.payload.APIPayload;
import com.softweb.report.ExtentTestManager;
import com.softweb.util.Constant;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest_1 extends APICommon implements Constant {

    //GET METHOD
    @Test(description = "")
    public void getCall_10000() {
       //Assert.assertTrue(false);
        System.out.println("Ravi");
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("");
            ExtentTestManager.getTest().assignCategory("Module: ");

            HttpResponse resp = APICommon.getData("https://reqres.in/api/users/2", "");
            response = response(resp.getStatusLine().getStatusCode());

            Assert.assertEquals(response,SUCCESS);

            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + new JSONObject(inputStreamToString(resp.getEntity().getContent())));
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }

    //PUT
    //@Test(description = "")
    public void updateSpaceBooking() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("");
            ExtentTestManager.getTest().assignCategory("Module:");

            APIPayload payload = new APIPayload();
            HttpResponse resp = APICommon.postData("URL", payload.getAllUsers().toString(), "PUT", APPLICATION_JSON, APPLICATION_JSON, "");
            response = response(resp.getStatusLine().getStatusCode());

            Assert.assertEquals(response, SUCCESS);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + inputStreamToString(resp.getEntity().getContent()));
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }

    //Delete : Deletes the car wash request
    //@Test(description = "")
    public void deleteTheCarWashSchedule() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("");
            ExtentTestManager.getTest().assignCategory("Module:");

            APIPayload payload = new APIPayload();
            HttpResponse resp = APICommon.postData("URL", null, "DELETE", APPLICATION_JSON, APPLICATION_JSON, "");
            response = response(resp.getStatusLine().getStatusCode());

            Assert.assertEquals(response, ACCEPTED);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + inputStreamToString(resp.getEntity().getContent()));
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }

    // POST Call
    //@Test(description = "")
    public void saveChargesOfamenitySpaceFacility() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("");
            ExtentTestManager.getTest().assignCategory("Module: Booking");

            APIPayload payload = new APIPayload();
            HttpResponse resp = APICommon.postData("URL", payload.getAllUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            response = response(resp.getStatusLine().getStatusCode());

            Assert.assertEquals(CREATED, response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + inputStreamToString(resp.getEntity().getContent()));
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }
}
