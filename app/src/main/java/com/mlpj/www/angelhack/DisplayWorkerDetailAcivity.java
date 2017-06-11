package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayWorkerDetailAcivity extends AppCompatActivity {

    String jobType;
    TextView tvName,tvEmail, tvJobType, tvContactNumber, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_worker_detail_acivity);

        final Intent intent = getIntent();
        jobType = intent.getStringExtra("jobType");

        tvName = (TextView)findViewById(R.id.tvName);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvJobType = (TextView)findViewById(R.id.tvJobType);
        tvContactNumber = (TextView)findViewById(R.id.tvContactNumber);
        tvDescription = (TextView)findViewById(R.id.tvDescription);


        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){

                        String name = jsonResponse.getString("name");
                        String email = jsonResponse.getString("email");
                        String jobType = jsonResponse.getString("jobType");
                        String contactNumber = jsonResponse.getString("contactNumber");
                        String description = jsonResponse.getString("description");

                        //Toast.makeText(getBaseContext(), "test", Toast.LENGTH_LONG).show();
                        /*
                        Intent intent1 = new Intent(DisplayWorkerDetailAcivity.this, DisplayActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("jobType", jobType);
                        intent.putExtra("contactNumber", contactNumber);
                        intent.putExtra("description", description);
                        startActivity(intent);
                        */

                        tvName.setText("Name: " + name);
                        tvEmail.setText("Email: " + email);
                        tvJobType.setText(jobType);
                        tvContactNumber.setText("Contact NO: " + contactNumber);
                        tvDescription.setText(description);


                                /*
                                intent.putExtra("name",name);
                                intent.putExtra("userName",userName);
                                intent.putExtra("age",age);
                                loginActivity.this.startActivity(intent);
                                */

                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(DisplayWorkerDetailAcivity.this);
                        builder.setMessage("Login failed")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        WorkerDetailRequest loginRequest = new WorkerDetailRequest(jobType, responseListener);
        RequestQueue queue = Volley.newRequestQueue(DisplayWorkerDetailAcivity.this);
        queue.add(loginRequest);

    }
}
