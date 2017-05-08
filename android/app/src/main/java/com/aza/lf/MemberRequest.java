package com.aza.lf;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MemberRequest extends StringRequest {

    final static private String URL = "";
    private Map<String, String> parameters;

    public MemberRequest(String userID, String userPassword, String userPhone, String userEmail, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userPhone", userPhone);
        parameters.put("userEmail", userEmail);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
