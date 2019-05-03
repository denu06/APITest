import com.softweb.config.APICommon;
import com.softweb.payload.APIPayload;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.softweb.util.Constant.APPLICATION_JSON;

public class Test {

    public static void main(String args[]) throws IOException, JSONException, URISyntaxException {

        System.out.println("Hello");
        APIPayload payload=new APIPayload();

        HttpResponse resp = APICommon.postData("https://www.siyanainfo.com/SmartAnganwadiAPI/v1/Login/Authenticate",payload.loginUsers().toString(), "POST", APPLICATION_JSON, APPLICATION_JSON, "");
        String response = APICommon.inputStreamToString(resp.getEntity().getContent());
        System.out.println(response);

        JSONObject jsonObj = new JSONObject(response);
        //String assigned =jsonObj.get("assigned").toString();

        JSONArray array = (JSONArray) jsonObj.get("data");
        System.out.println(array.getJSONObject(0));

        JSONObject jsonObj1 = array.getJSONObject(0);
        String scopeName = jsonObj1.get("EmployeeID").toString();
        System.out.println(scopeName);

    }
}
