package LoginTS;

import com.relevantcodes.extentreports.LogStatus;
import com.softweb.config.APICommon;
import com.softweb.payload.APIPayload;
import com.softweb.report.ExtentTestManager;
import com.softweb.util.Constant;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTC extends APICommon implements Constant {



    // POST Call
    @Test(description = "Login API")
    public void loginAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-001: Login API");
            ExtentTestManager.getTest().assignCategory("Module: Login");

            APIPayload payload = new APIPayload();
            HttpResponse resp = APICommon.postData("https://www.siyanainfo.com/SmartAnganwadiAPI/v1/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");

            // Step-1 : Verify with response code
            response = response(resp.getStatusLine().getStatusCode());
            System.out.println("response:"+response);
            Assert.assertEquals(response,SUCCESS);

            //Generate token
            String test= APICommon.inputStreamToString(resp.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = jsonObject.get("Token").toString();
            System.out.println("token:"+token);

            ExtentTestManager.getTest().log(LogStatus.PASS, "Status Code : " + response);
            ExtentTestManager.getTest().log(LogStatus.PASS, "payload : " + test);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, response);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Oops ! .. API have some issue ", e.getMessage());
        }
    }


}
