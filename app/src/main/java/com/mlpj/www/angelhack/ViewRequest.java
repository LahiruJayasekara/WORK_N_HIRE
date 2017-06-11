package com.mlpj.www.angelhack;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 6/10/2017.
 * All Rights Reserved by MLPJ©
 */

public class ViewRequest extends StringRequest{
    private static final String VIEW_REQUEST_URL = "http://mlpj.000webhostapp.com/SearchRequest.php";
    private Map<String, String> params;

    public ViewRequest(String search,Response.Listener<String> listener){
        super(Request.Method.POST, VIEW_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("search", search);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
