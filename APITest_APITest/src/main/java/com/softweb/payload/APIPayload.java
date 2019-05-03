package com.softweb.payload;

import com.google.gson.JsonObject;
import com.softweb.config.APICommon;
import com.softweb.util.generateRandomDate;
import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import static com.google.gson.internal.bind.TypeAdapters.URL;
import static com.softweb.util.Constant.APPLICATION_JSON;
import static com.softweb.util.Constant.URI;

public class APIPayload {

   /* public JSONObject userRoleDataInformation() throws Exception{

        String userNamePassword = LoadPropertiesFiles.getProValue( "common",null, "EPARole","USER_NAME") + ":" + LoadPropertiesFiles.getProValue( "common",null, "EPARole","USER_PASSWORD");
        String encodedString = "Basic " + Base64.getEncoder().encodeToString(userNamePassword.getBytes());

        HttpResponse resp =  APICommon.getDataWithAuthHeader(LoadPropertiesFiles.getProValue( "sam",LoadPropertiesFiles.auth, "RolemgmtData","ROLE_URL_PREFIX") + "users/" +LoadPropertiesFiles.getProValue("sam", LoadPropertiesFiles.auth, "RolemgmtData", "UPN_DETAIL").replaceAll("@", "%40")+"/roles/"+LoadPropertiesFiles.getProValue("sam", LoadPropertiesFiles.auth, "RolemgmtData", "ROLE_DETAIL").replaceAll(" ", "%20"), encodedString);
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        System.out.println(response);

        JSONObject jsonObj = new JSONObject(response);

        String assigned =jsonObj.get("assigned").toString();
        String roleName =jsonObj.get("roleName").toString();

        JSONArray array = (JSONArray) jsonObj.get("roleScopeList");
        //System.out.println(array.getJSONObject(0));
        JSONObject jsonObj1 = array.getJSONObject(0);
        String scopeName = jsonObj1.get("scopeName").toString();

        //System.out.println(assigned + " " +scopeName);
        JSONArray array1 = (JSONArray)jsonObj1.get("scopeItemList");
        //System.out.println(array1.getJSONObject(1));

        JSONObject jsonObj2 = array1.getJSONObject(0);
        String ref = jsonObj2.get("ref").toString();

        JSONArray array2 = (JSONArray) jsonObj2.get("scopeItemAttrList");
        JSONObject jsonObj3 = array2.getJSONObject(1);
        String name = jsonObj3.get("name").toString();
        String value = jsonObj3.get("vlaue").toString();
        //String scopeName1 = jsonObj2.get("scopeItemAttrList").toString();

        //System.out.println("Ref "+ref+ "  "+ "name "+name+ "  "+"value "+value +" "+ "scopeName "+scopeName+ " "+"assigned "+assigned);
        boolean assign = false;
        if(assigned.equals("false")) {
            assign = true;
        }


        String data = "{\n" +
                "  \"assigned\": "+assign+",\n" +
                "  \"roleName\": \""+roleName+"\",\n" +
                "  \"roleScopeList\": [\n" +
                "    {\n" +
                "      \"scopeItemList\": [\n" +
                "        {\n" +
                "          \"assigned\": "+assign+",\n" +
                "          \"ref\": \""+ref+"\",\n" +
                "          \"scopeItemAttrList\": [\n" +
                "            {\n" +
                "              \"name\": \""+name+"\",\n" +
                "              \"vlaue\": \""+value+"\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ],\n" +
                "      \"scopeName\": \""+scopeName+"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JSONObject mainObject = new JSONObject(data);

        return mainObject;
    }*/

    public JSONObject getAllUsers() {
        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("item_key", "test_item");
            data.put("item_description", "test item from testng");
            data.put("item_value", "testvalue");
            data.put("created_by", "bamboo");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

    /**********************Login Start *************************/

    public JSONObject loginUsers() {
        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("Username", "jatin.saksena@siyanainfo.com");
            data.put("Password", "Jatin@123");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

    /**********************Login End *************************/

    /**********************Employee Start *************************/

    /********************** saveAttendanceOfEmployeePayload Start *************************/

    public JSONObject saveAttendanceOfEmployeePayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        System.out.println("Response from save Attendance" + response);
        JSONObject jsonObj = new JSONObject(response);
        String token = "Bearer " + jsonObj.get("Token").toString();
        System.out.println(token);


        // save attendance
        System.out.println("Into Save Attendance");
        HttpResponse resp1 = APICommon.postData(URI + "/Employee/GetAllHelperByAnganwadiID", getAllHelperByAnganwadiIDPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
        System.out.println(resp1);
        String response1 = APICommon.inputStreamToString(resp1.getEntity().getContent());
        System.out.println("Res :" + response1);

        // fetching employeeid
        JSONObject jsonObj1 = new JSONObject(response1);
        JSONArray array = (JSONArray) jsonObj1.get("data");
        System.out.println("Ravi" + array.getJSONObject(0));
        JSONObject jsonObj2 = array.getJSONObject(0);
        Integer empId = jsonObj2.getInt("EmployeeID");
        System.out.println("Employee Id :" + empId);

        // Date generator
        generateRandomDate gen = new generateRandomDate();
        String mydate = generateRandomDate.createRandomDate(2019, 2019).toString();
        System.out.println(mydate);

        JSONObject data = new JSONObject();
        data = new JSONObject();
        data.put("EmployeeAttendanceID", 0);
        data.put("EmployeeID", empId);
        data.put("AttendanceDate", mydate);
        data.put("IsPresent", false);
        data.put("DeviceLocation", "sample string 2");
        data.put("DeviceToken", "sample string 3");
        data.put("DeviceType", "sample string 4");
        data.put("CreatedBy", 1);


        return data;
    }

    /********************** saveAttendanceOfEmployeePayload END *************************/

    /********************** getAllHelperByAnganwadiIDPayload Start *************************/

    public JSONObject getAllHelperByAnganwadiIDPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        JSONObject jsonObj = new JSONObject(response);
        JSONObject dt = jsonObj.getJSONObject("data");
        Integer anganwadiId = dt.getInt("AnganwadiID");
        System.out.println(anganwadiId);

        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("AnganwadiID", anganwadiId);


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

    /********************** getAllHelperByAnganwadiIDPayload END *************************/

    /********************** getEmployeeAttendancesByDateAnganwadiID Start *************************/

    public JSONObject getEmployeeAttendancesByDateAnganwadiIDPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        JSONObject jsonObj = new JSONObject(response);
        JSONObject dt = jsonObj.getJSONObject("data");
        Integer anganwadiId = dt.getInt("AnganwadiID");
        generateRandomDate gen = new generateRandomDate();
        String mydate = generateRandomDate.createRandomDate(2019, 2019).toString();
        System.out.println(mydate);
        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("AnganwadiID", anganwadiId);
            data.put("AttendanceDate", mydate);


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

    /********************** getEmployeeAttendancesByDateAnganwadiID END *************************/


    /**********************Employee End *************************/


    /**********************Family Start *************************/

    public JSONObject addFamilyPayload() throws Exception {

        //Login API
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        //System.out.println(response);

        JSONObject jsonObj = new JSONObject(response);
        JSONObject dt = jsonObj.getJSONObject("data");
        Integer CreatedBy = dt.getInt("EmployeeID");
        Integer anganwadiID = dt.getInt("AnganwadiID");

        int familyCastID = 1;
        boolean stateMinority = true;

        System.out.println(familyCastID + " " + stateMinority + " " + CreatedBy + " " + anganwadiID);

        String data = "{\n" +
                "  \n" +
                "  \"AddressLine1\": \"sample string 3\",\n" +
                "  \"AddressLine2\": \"sample string 4\",\n" +
                "  \"FamilyCastID\": " + familyCastID + ",\n" +
                "  \"Religion\": \"sample string 5\",\n" +
                "  \"StateMinority\": " + stateMinority + ",\n" +
                "  \"CreatedBy\": " + CreatedBy + ",\n" +
                "  \"AnganwadiID\": " + anganwadiID + "\n" +
                "}";

        JSONObject mainObject = new JSONObject(data);

        return mainObject;
    }


    public JSONObject listFamilyPayload() throws Exception {

        //Login API
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        //System.out.println(response);

        JSONObject jsonObj = new JSONObject(response);

        JSONObject dt = jsonObj.getJSONObject("data");
        Integer anganwadiID = dt.getInt("AnganwadiID");

        System.out.println(anganwadiID);

        String data = "{\n" +
                "  \"AnganwadiID\": " + anganwadiID + ",\n" +
                "  \"PageNo\": 0\n" +
                "}";

        JSONObject mainObject = new JSONObject(data);

        return mainObject;
    }

    public JSONObject getFamilyByIdAPIPayload() throws Exception {

        //List Family API
        HttpResponse resp1 = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String test = APICommon.inputStreamToString(resp1.getEntity().getContent());
        JSONObject jsonObject = new JSONObject(test);
        String token = "Bearer " + jsonObject.get("Token").toString();

        //List Family API
        HttpResponse resp = APICommon.postData(URI + "/Family/GetAll", listFamilyPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        //System.out.println(response);

        JSONObject jsonObj = new JSONObject(response);
        JSONArray array = (JSONArray) jsonObj.get("data");
        //System.out.println("Ravi"+array.getJSONObject(0));
        JSONObject jsonObj1 = array.getJSONObject(0);

        Integer familyID = jsonObj1.getInt("FamilyID");

        System.out.println(familyID);

        String data = "{\n" +
                "  \"FamilyID\": " + familyID + "\n" +
                "}";

        JSONObject mainObject = new JSONObject(data);

        return mainObject;
    }

    public JSONObject updateFamilyAPIPayload() throws Exception {

        //List Family API
        HttpResponse resp1 = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response1 = APICommon.inputStreamToString(resp1.getEntity().getContent());
        JSONObject jsonObject = new JSONObject(response1);
        String token = "Bearer " + jsonObject.get("Token").toString();

        JSONObject jsonObj = new JSONObject(response1);
        JSONObject dt = jsonObj.getJSONObject("data");

        Integer CreatedBy = dt.getInt("EmployeeID");
        Integer anganwadiID = dt.getInt("AnganwadiID");
        boolean stateMinority = true;

        //List Family API
        HttpResponse resp = APICommon.postData(URI + "/Family/GetAll", listFamilyPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        //System.out.println(response);

        JSONObject jsonObj2 = new JSONObject(response);
        JSONArray array = (JSONArray) jsonObj2.get("data");
        //System.out.println("Ravi"+array.getJSONObject(0));
        JSONObject jsonObj1 = array.getJSONObject(0);

        Integer familyID = jsonObj1.getInt("FamilyID");

        System.out.println(CreatedBy + " " + anganwadiID + " " + stateMinority + " " + familyID);

        JSONObject data = new JSONObject();
        data = new JSONObject();
        data.put("FamilyID", familyID);
        data.put("StateMinority", stateMinority);
        data.put("CreatedBy", CreatedBy);
        data.put("AnganwadiID", anganwadiID);


        return data;
    }
    /**********************Family End *************************/


    /**********************Student Start *************************/

    /********************** getVStudentVitalsByMonthPayload Start *************************/

    public JSONObject getVStudentVitalsByMonthPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        JSONObject jsonObj = new JSONObject(response);
        JSONObject dt = jsonObj.getJSONObject("data");
        Integer anganwadiId = dt.getInt("AnganwadiID");
        System.out.println(anganwadiId);

        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("AnganwadiID", anganwadiId);
            data.put("AsAtDate", "2019-04-24");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

    /********************** getVStudentVitalsByMonthPayload END *************************/

    /********************** saveStudentVitalsPayload Start *************************/

    public JSONArray saveStudentVitalsPayload() throws Exception{
        // For login

        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        System.out.println("Response from save Attendance" + response);
        JSONObject jsonObj = new JSONObject(response);
        String token = "Bearer " + jsonObj.get("Token").toString();
        System.out.println(token);


        // get students vital by month
        System.out.println("Into Get Students Vital By Month");
        HttpResponse resp1 = APICommon.postData(URI + "/Health/GetVStudentVitalsByMonth", getVStudentVitalsByMonthPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
        System.out.println(resp1);
        String response1 = APICommon.inputStreamToString(resp1.getEntity().getContent());
        System.out.println("Res :" + response1);

        // fetching All student data
        JSONObject jsonObj1 = new JSONObject(response1);
        JSONArray array = (JSONArray) jsonObj1.get("data");
        System.out.println(array);
        String studentList = array.toString();
        //System.out.println("Student List"+studentList);
        Integer count = 0;


        //JSONObject jo = new JSONObject(studentList);

        //JSONObject data = new JSONObject();

        /*for (int i = 0; i < array.length(); i++) {
            JSONObject jsonobject = array.getJSONObject(i);
            Integer studentID = jsonobject.getInt("StudentID");
            Integer familyID = jsonobject.getInt("FamilyID");
            Integer familyCode = jsonobject.getInt("FamilyCode");
            String studentName = jsonobject.getString("StudentName");
            Integer anganwadiID = jsonobject.getInt("AnganwadiID");
            String height = jsonobject.getString("Height");
            String heightUoMID = jsonobject.getString("HeightUoMID");
            String weight = jsonobject.getString("Weight");
            String weightUoMID = jsonobject.getString("WeightUoMID");
            String vitalDate = jsonobject.getString("VitalDate");
            String MMMYYYY = jsonobject.getString("MMMYYYY");
            Integer createdBy = jsonobject.getInt("createdBy");



            data = new JSONObject();
            data.put("StudentID", studentID);
            data.put("FamilyID", familyID);
            data.put("FamilyCode", familyCode);
            data.put("StudentName", studentName);
            data.put("AnganwadiID", anganwadiID);
            data.put("Height", height);
            data.put("HeightUoMID", heightUoMID);
            data.put("Weight", weight);
            data.put("WeightUoMID", weightUoMID);
            data.put("VitalDate", vitalDate);
            data.put("MMMYYYY", MMMYYYY);
            data.put("createdBy", createdBy);

            count++;
            System.out.println("Data Entered");
            System.out.println(count);
            System.out.println(data);
            return  data;

        }*/
        return array;

    }


        /********************** saveStudentVitalsPayload END *************************/


    /********************** getStudentAttendancesByDateAnganwadiIDPayload Start *************************/

    public JSONObject getStudentAttendancesByDateAnganwadiIDPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        JSONObject jsonObj = new JSONObject(response);
        JSONObject dt = jsonObj.getJSONObject("data");
        Integer anganwadiId = dt.getInt("AnganwadiID");
        generateRandomDate gen = new generateRandomDate();
        String mydate = generateRandomDate.createRandomDate(2019, 2019).toString();
        System.out.println(mydate);
        System.out.println(anganwadiId);

        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("AnganwadiID", anganwadiId);
            data.put("AttendanceDate", "2019-04-15");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

    /********************** getStudentAttendancesByDateAnganwadiIDPayload END *************************/


    /********************** saveAttendanceOfStudentPayload Start *************************/

    public JSONObject saveAttendanceOfStudentPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        System.out.println("Response from save Attendance" + response);
        JSONObject jsonObj = new JSONObject(response);
        String token = "Bearer " + jsonObj.get("Token").toString();
        System.out.println(token);


        // save attendance

        System.out.println("Into Save Attendance of Student");
        HttpResponse resp1 = APICommon.postData(URI + "/Family/GetStudentAttendancesByDateAnganwadiID", getStudentAttendancesByDateAnganwadiIDPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
        System.out.println(resp1);
        String response1 = APICommon.inputStreamToString(resp1.getEntity().getContent());
        System.out.println("Response from GetStudentAttendancesByDateAnganwadiID :" + response1);
        // fetching employeeid
        JSONObject jsonObj1 = new JSONObject(response1);
        JSONArray array = (JSONArray) jsonObj1.get("data");
        System.out.println("-----" + array.getJSONObject(0));
        JSONObject jsonObj2 = array.getJSONObject(0);
        Integer studentID = jsonObj2.getInt("StudentID");
        Integer studentAttendanceID = jsonObj2.getInt("StudentAttendanceID");
        System.out.println("Student Id :" + studentID);
        System.out.println("Student Attedence Id :" + studentAttendanceID);

        // Date generator
        generateRandomDate gen = new generateRandomDate();
        String mydate = generateRandomDate.createRandomDate(2019, 2019).toString();
        System.out.println("Random Date for Attendence : "+mydate);

        JSONObject data = new JSONObject();
        data = new JSONObject();
        data.put("StudentAttendanceID", studentAttendanceID);
        data.put("StudentID", studentID);
        data.put("AttendanceDate", mydate);
        data.put("IsPresent", true);
        data.put("DeviceLocation", "Test");
        data.put("DeviceToken", "Test");
        data.put("DeviceType", "Test");
        data.put("CreatedBy", 1);


        return data;
    }

    /********************** saveAttendanceOfEmployeePayload END *************************/

    /**********************Student End *************************/

    /**********************Vaccination Start *************************/
    /********************** getAllStudentsByAnganwadiIDPayload start *************************/

    public JSONObject getAllStudentsByAnganwadiIDPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        JSONObject jsonObj = new JSONObject(response);
        JSONObject dt = jsonObj.getJSONObject("data");
        Integer anganwadiId = dt.getInt("AnganwadiID");
        System.out.println(anganwadiId);

        JSONObject data = new JSONObject();
        try {
            data = new JSONObject();
            data.put("AnganwadiID", anganwadiId);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return data;
    }

/********************** getAllStudentsByAnganwadiIDPayload END *************************/

/********************** getStudentStudentVaccinationByStudentIDMonthPayload start *************************/

    public JSONObject getStudentStudentVaccinationByStudentIDMonthPayload() throws Exception {
        // For login
        HttpResponse resp = APICommon.postData(URI + "/Login/Authenticate", loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        System.out.println("Response from save Attendance" + response);
        JSONObject jsonObj = new JSONObject(response);
        String token = "Bearer " + jsonObj.get("Token").toString();
        System.out.println(token);


        // save attendance
        System.out.println("Into Get All Students By AnganwadiID");
        HttpResponse resp1 = APICommon.postData(URI + "/Family/GetAllStudentsByAnganwadiID", getAllStudentsByAnganwadiIDPayload().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, token);
        System.out.println(resp1);
        String response1 = APICommon.inputStreamToString(resp1.getEntity().getContent());
        System.out.println("Response from getAllStudentsByAnganwadiIDPayload :" + response1);

        // fetching employeeid
        JSONObject jsonObj1 = new JSONObject(response);
        JSONArray array = (JSONArray)jsonObj1.get("data");
        JSONObject jsonObj2 = array.getJSONObject(0);
        System.out.println(jsonObj2);
        Integer studentId = jsonObj2.getInt("StudentID");
        Integer anganwadiId = jsonObj2.getInt("AnganwadiID");
        System.out.println(anganwadiId);
        System.out.println(studentId);

        // Date generator
        generateRandomDate gen = new generateRandomDate();
        String mydate = generateRandomDate.createRandomDate(2019, 2019).toString();
        System.out.println("Random Date for Attendence : "+mydate);

        JSONObject data = new JSONObject();
        data = new JSONObject();
        data.put("AnganwadiID", anganwadiId);
        data.put("AsAtDate", mydate);
        data.put("LanguageCode","en-US");
        data.put("FamilyMemberID", studentId);


        return data;
    }

/********************** getStudentStudentVaccinationByStudentIDMonthPayload END *************************/

    /**********************Vaccination End *************************/



}

