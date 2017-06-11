package com.mlpj.www.angelhack;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 6/10/2017.
 * All Rights Reserved by MLPJ©
 */

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://mlpj.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
