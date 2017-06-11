package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class JobAddActivity extends AppCompatActivity {

    String email, name;
    EditText etJobType, etContactNumber, etDescription;
    Button bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_add);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");

        etJobType = (EditText) findViewById(R.id.etJobType);
        etContactNumber = (EditText) findViewById(R.id.etContactNumber);
        etDescription = (EditText) findViewById(R.id.etDescription);
        bSubmit =(Button)findViewById(R.id.bSubmit);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String jobType = etJobType.getText().toString();
                final String contactNumber = etContactNumber.getText().toString();
                final String description = etDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast toast = Toast.makeText(JobAddActivity.this, "Successfully submitted", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(JobAddActivity.this, SelectAddTypeActivity.class);
                                intent.putExtra("email",email);
                                JobAddActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(JobAddActivity.this);
                                builder.setMessage("oopss..Failed to submit your ad")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Log.v("my tag", "" + email+"," + jobType+"," + contactNumber+"," + description);
                JobAddRequest jobAddRequest = new JobAddRequest(name, email,jobType,contactNumber,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(JobAddActivity.this);
                queue.add(jobAddRequest);
            }
        });
    }
}
