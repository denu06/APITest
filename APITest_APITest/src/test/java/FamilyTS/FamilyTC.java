package FamilyTS;

import com.relevantcodes.extentreports.LogStatus;
import com.softweb.config.APICommon;
import com.softweb.payload.APIPayload;
import com.softweb.report.ExtentTestManager;
import com.softweb.util.Constant;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FamilyTC extends APICommon implements Constant {



    // POST Call
    @Test(description = "Add Family API")
    public void addFamilyAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-005: Add Family API");
            ExtentTestManager.getTest().assignCategory("Module: Family");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);

            HttpResponse resp = APICommon.postData(URI+"/Family/AddFamily", payload.addFamilyPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

            //Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            //System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 :  Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            //System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode  = jsonObj.get("statuscode").toString();
            //System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode, "200");

            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }


    // POST Call
    @Test(description = "List Family API")
    public void listFamilyAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-006: List Family API");
            ExtentTestManager.getTest().assignCategory("Module: Family");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);

            HttpResponse resp = APICommon.postData(URI+"/Family/GetAll", payload.listFamilyPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

            //Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            //System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 :  Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            //System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode  = jsonObj.get("statuscode").toString();
            //System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode, "200");

            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }


    // POST Call
    @Test(description = "Get Family By Id API")
    public void getFamilyByIdAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-007: Get Family By Id API");
            ExtentTestManager.getTest().assignCategory("Module: Family");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);

            HttpResponse resp = APICommon.postData(URI+"/Family/GetFamilyById", payload.getFamilyByIdAPIPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

            //Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            //System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 :  Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            //System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode  = jsonObj.get("statuscode").toString();
            //System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode, "200");

            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }

    // POST Call
    @Test(description = "Update Family API")
    public void updateFamilyAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-008: Update Family API");
            ExtentTestManager.getTest().assignCategory("Module: Family");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);

            HttpResponse resp = APICommon.postData(URI+"/Family/UpdateFamily", payload.updateFamilyAPIPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

            //Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            //System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Step-2 :  Verify with response body
            String responsePayload = inputStreamToString(resp.getEntity().getContent());
            String responsePayload2 = responsePayload.toString();
            //System.out.println(responsePayload2);
            JSONObject jsonObj = new JSONObject(responsePayload2);
            String statuscode  = jsonObj.get("statuscode").toString();
            //System.out.println("statuscode" + statuscode);
            Assert.assertEquals(statuscode, "200");

            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + responsePayload);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }


}
