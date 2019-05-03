package StudentTS;

import com.relevantcodes.extentreports.LogStatus;
import com.softweb.config.APICommon;
import com.softweb.payload.APIPayload;
import com.softweb.report.ExtentTestManager;
import com.softweb.util.Constant;
import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StudentTC extends APICommon implements Constant {

    // POST Call
    @Test(description = "GetV Student Vitals By Month API")
    public void getVStudentVitalsByMonthAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-009: GetV Student Vitals By Month API");
            ExtentTestManager.getTest().assignCategory("Module: Student");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);
            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Health/GetVStudentVitalsByMonth", payload.getVStudentVitalsByMonthPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

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
    @Test(description = "Save Student Vitals API")
    public void saveStudentVitalsAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-010: Save Student Vitals API");
            ExtentTestManager.getTest().assignCategory("Module: Student");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            System.out.println("token:"+token);
            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Health/SaveStudentVitals", payload.saveStudentVitalsPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

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
    @Test(description = "Get Student Attendances By Date AnganwadiID API")
    public void getStudentAttendancesByDateAnganwadiIDAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-011: Save Student Vitals API");
            ExtentTestManager.getTest().assignCategory("Module: Student");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            System.out.println("token:"+token);
            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Family/GetStudentAttendancesByDateAnganwadiID", payload.getStudentAttendancesByDateAnganwadiIDPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

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
    @Test(description = "Save Attendance Of Student API")
    public void saveAttendanceOfStudentAPI() throws Exception {
        String response = null;
        try {
            ExtentTestManager.getTest().getTest().setName("SAA-012: Save Attendance Of Student API");
            ExtentTestManager.getTest().assignCategory("Module: Student");

            APIPayload payload = new APIPayload();

            //Generate token API
            HttpResponse resp1 = APICommon.postData(URI+"/Login/Authenticate", payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
            String test= APICommon.inputStreamToString(resp1.getEntity().getContent());
            JSONObject jsonObject=new JSONObject(test);
            String token = "Bearer "+jsonObject.get("Token").toString();
            //System.out.println("token:"+token);
            // Login Api End //

            HttpResponse resp = APICommon.postData(URI+"/Family/SaveAttendance", payload.saveAttendanceOfStudentPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);

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

}
