package com.mlpj.www.angelhack;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 6/10/2017.
 * All Rights Reserved by MLPJ©
 */

public class JobAddRequest extends StringRequest{

    private static final String JobAdd_REQUEST_URL = "http://mlpj.000webhostapp.com/JobAddRequest.php";
    private Map<String, String> params1;

    public JobAddRequest(String name, String email, String jobType, String contactNumber, String description, Response.Listener<String> listener){
        super(Method.POST, JobAdd_REQUEST_URL, listener, null);
        params1 = new HashMap<>();
        params1.put("name", name);
        params1.put("email", email);
        params1.put("jobType", jobType);
        params1.put("contactNumber", contactNumber);
        params1.put("description",description);
        Log.v("my tag", "" + email+"," + jobType+"," + contactNumber+"," + description);

    }

    @Override
    public Map<String, String> getParams() {
        return params1;
    }
}
