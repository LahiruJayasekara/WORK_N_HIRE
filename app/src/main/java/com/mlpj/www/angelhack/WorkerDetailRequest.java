package com.mlpj.www.angelhack;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 6/11/2017.
 * All Rights Reserved by MLPJ©
 */

public class WorkerDetailRequest extends StringRequest{

    private static final String WORKER_DETAIL_REQUEST_URL = "http://mlpj.000webhostapp.com/WorkerDetailRequest.php";
    private Map<String, String> params;

    public WorkerDetailRequest(String jobType, Response.Listener<String> listener){
        super(Request.Method.POST, WORKER_DETAIL_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("jobType", jobType);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
