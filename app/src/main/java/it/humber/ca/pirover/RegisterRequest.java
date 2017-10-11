package it.humber.ca.pirover;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2017-10-06.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://barebarz.000webhostapp.com/RegisterPi.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password, String content, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params =  new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("content", content);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
