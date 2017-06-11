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

public class ServiceAddActivity extends AppCompatActivity {

    String email, name;
    EditText etServiceRequest,etContactNumber,etDescription;
    Button bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_add);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");

        etServiceRequest = (EditText)findViewById(R.id.etServiceType);
        etContactNumber = (EditText)findViewById(R.id.etContactNumber);
        etDescription = (EditText)findViewById(R.id.etDescription);
        bSubmit = (Button)findViewById(R.id.bSubmit);

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String serviceRequest = etServiceRequest.getText().toString();
                final String contactNumber = etContactNumber.getText().toString();
                final String description = etDescription.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast toast = Toast.makeText(ServiceAddActivity.this, "Successfully submitted", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent intent = new Intent(ServiceAddActivity.this, SelectAddTypeActivity.class);
                                intent.putExtra("email",email);
                                ServiceAddActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(ServiceAddActivity.this);
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

                Log.v("my tag", "" + email+"," + serviceRequest+"," + contactNumber+"," + description);
                ServiceAddRequest serviceAddRequest = new ServiceAddRequest(name, email,serviceRequest,contactNumber,description,responseListener);
                RequestQueue queue = Volley.newRequestQueue(ServiceAddActivity.this);
                queue.add(serviceAddRequest);
            }
        });
    }
}
