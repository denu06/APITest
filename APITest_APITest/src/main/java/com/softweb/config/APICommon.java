package com.softweb.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.LogStatus;
import com.softweb.report.ExtentManager;
import com.softweb.report.ExtentTestManager;
import com.softweb.report.listner.AnnotationTransformer;
import com.softweb.report.listner.TestListener;
import com.softweb.util.Constant;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created By Softweb QA Team
 * on 22/06/2018
 */
@Listeners({AnnotationTransformer.class, TestListener.class})
public class APICommon implements Constant{

    public static Client client = null;
    public static String projectId = null;
    public static String version = null;
    public static int cycleId = 0;
    public static String baseJiraURL = null;
    public static String jiraUserId = null;
    public static boolean jiraIntregration;
    public String jiraPropertiesFile = System.getProperty("user.dir") + "\\src\\main\\resources\\JiraConfig.properties";

    public APICommon() {
    }

    @Parameters("updateOnJira")
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("false") boolean updateOnJira)throws Exception{
        Properties prop;
        String jiraPassword;
        String environment;
        String testCycleNamePreFix;
        jiraIntregration = updateOnJira;
        if (jiraIntregration) {
            try {
                prop = LoadPropertiesFiles.loadProperties(jiraPropertiesFile);
                jiraUserId = prop.getProperty("UserName");
                jiraPassword = prop.getProperty("Password");
                projectId = prop.getProperty("ProjectId");
                version = prop.getProperty("VersionId");
                baseJiraURL = prop.getProperty("BaseJiraURL");
                environment = prop.getProperty("Environment");
                testCycleNamePreFix = prop.getProperty("TestCycleNamePreFix");
                String testCycleDes = prop.getProperty("TestCycleDes");
                Date dateobj = new Date();
                DateFormat df = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss");
                String startDate = (new SimpleDateFormat("MM/dd/yy")).format(dateobj);
                String endDate = (new SimpleDateFormat("MM/dd/yy")).format(dateobj);
                ClientConfig clientConfig = new ClientConfig();
                HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(jiraUserId, jiraPassword);
                clientConfig.register(feature);
                clientConfig.register(JacksonFeature.class);
                client = ClientBuilder.newClient(clientConfig);
                String cycleName = testCycleNamePreFix + "_" + df.format(dateobj);
                Entity payload = Entity.json("{  \"name\": \"" + cycleName + "\", \"environment\": \"" + environment + "\",  \"description\": \"" + testCycleDes + "\",  \"startDate\": \"" + startDate + "\",  \"endDate\": \"" + endDate + "\",  \"projectId\": \"" + projectId + "\",  \"versionId\": \"" + version + "\"}");
                Response response = client.target(baseJiraURL + "/rest/zapi/latest/cycle").request(new MediaType[]{MediaType.APPLICATION_JSON_TYPE}).post(payload);
                try {
                    JsonObject jsonObject2 = (new JsonParser()).parse("[" + (String)response.readEntity(String.class) + "]").getAsJsonArray().get(0).getAsJsonObject();
                    cycleId = jsonObject2.get("id").getAsInt();
                    System.out.println("cycleId  "+cycleId);
                } catch (Exception var22) {
                    cycleId = 0;
                }
            } catch (Exception var23) {
                throw var23;
            }
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void testStart(ITestResult result, Method method) throws Exception{
        ExtentTestManager.startTest("" + method.getName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Test Start" );
    }

    @AfterMethod
    public void terminateBrowser(ITestResult result) throws Exception{
        byte testStatus;
        if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
            testStatus = 2;
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
            testStatus = 3;
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
            testStatus = 1;
        }

        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Session Closed");
        if (jiraIntregration){
            try {
                String methodId = result.getMethod().getMethodName().split("_")[1];
                Entity payloadE = Entity.json("{  \"cycleId\": \"" + cycleId + "\",  \"issueId\": \"" + methodId + "\",  \"projectId\": \"" + projectId + "\",  \"versionId\": \"" + version + "\",  \"assigneeType\": \"assignee\",  \"assignee\": \"" + jiraUserId + "\"}");
                Response responseE = client.target(baseJiraURL + "/rest/zapi/latest/execution").request(new MediaType[]{MediaType.APPLICATION_JSON_TYPE}).post(payloadE);
                String[] array = ((String)responseE.readEntity(String.class)).split(":");
                String resultRest = array[0].substring(2);
                resultRest = resultRest.substring(0, resultRest.length() - 1);
                Entity payload22 = Entity.json("{\"status\": \"" + testStatus + "\"}");
                Response var14 = client.target(baseJiraURL + "/rest/zapi/latest/execution/" + resultRest + "/execute").request(new MediaType[]{MediaType.APPLICATION_JSON_TYPE}).put(payload22);
            } catch (Exception var24) {
                System.out.println("Problem with JIRA Intregration");
            }
        }
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        String line;
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        // Return full string
        return total.toString();
    }

    public static HttpResponse getData(String uri,String api_key) throws JSONException, IOException, URISyntaxException {
        @SuppressWarnings("deprecation") HttpClient client = new DefaultHttpClient();
        System.out.println(uri);

        HttpGet request = new HttpGet(new URI(uri));
        request.setHeader(HttpHeaders.AUTHORIZATION,api_key);
        HttpResponse response = client.execute(request);
        /*String output = APICommon.inputStreamToString(response.getEntity().getContent());*/
        return response;
    }

    public static HttpResponse postData(String uri, String data, String method, String accept, String contentType, String api_key) throws JSONException, IOException, URISyntaxException {
        @SuppressWarnings("deprecation") HttpClient client = new DefaultHttpClient();

        HttpUriRequest request;
        switch (method.toUpperCase()) {
            case "PUT":
                request = new HttpPut(new URI(uri));
                ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(data));
                break;
            case "DELETE":
                request = new HttpDelete(new URI(uri));
                break;
            case "POST":
                request = new HttpPost(new URI(uri));
                ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(data));
                break;
            default:
                request = new HttpPost(new URI(uri));
                ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(data));
                break;
        }
        request.setHeader(HttpHeaders.ACCEPT, accept);
        request.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        request.setHeader(HttpHeaders.AUTHORIZATION, api_key);
        HttpResponse response = client.execute(request);
        return response;
    }

    public static String response(int responseCode){
        String response = "";
        switch (responseCode) {
            case 200:
                response = "200 - Success";
                break;
            case 201:
                response = "201 - Created";
                break;
            case 202:
                response = "202 - Accepted";
                break;
            case 204:
                response = "204 - No Content";
                break;
            case 400:
                response = "400 - Bad Request";
                break;
            case 401:
                response = "401 - Unauthorized";
                break;
            case 403:
                response = "403 - Forbidden";
                break;
            case 404:
                response = "404 - Not Found";
                break;
            case 405:
                response = "405 - Method Not Allowed";
                break;
            case 406:
                response = "406 - Not Acceptable";
                break;
            case 500 :
                response = "500 - Internal Server Error";
                break;
            case 501:
                response = "501 - Not Implemented";
                break;
            case 412:
                response = "412 - Precondition Failed";
                break;
            case 415:
                response = "415 - Unsupported Media Type";
                break;
            case 301:
                response = "301 - Moved Permanently";
                break;
            default:
                response = "000 - Fail";
                break;
        }
        return response.toUpperCase();
    }
}