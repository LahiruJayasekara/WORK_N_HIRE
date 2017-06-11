package com.mlpj.www.angelhack;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 6/10/2017.
 * All Rights Reserved by MLPJ©
 */

public class ServiceAddRequest extends StringRequest{
    private static final String SERVICE_REQUEST_URL = "http://mlpj.000webhostapp.com/ServiceAddRequest.php";
    private Map<String, String> params;

    public ServiceAddRequest(String name, String email, String serviceRequired, String contactNumber, String description, Response.Listener<String> listener){
        super(Method.POST, SERVICE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("serviceRequired", serviceRequired);
        params.put("contactNumber", contactNumber);
        params.put("description", description);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
