package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity{

    EditText etEmail, etPassword;
    Button bLogin;
    TextView tvNotRegistered;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etpassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvNotRegistered = (TextView) findViewById(R.id.tvNotRegistered);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(getBaseContext(), "test", Toast.LENGTH_LONG).show();
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String name = jsonResponse.getString("name");
                                //int age = jsonResponse.getInt("age");


                                Intent intent = new Intent(loginActivity.this, SelectAddTypeActivity.class);
                                intent.putExtra("email", email);
                                intent.putExtra("name",name);
                                loginActivity.this.startActivity(intent);

                                /*
                                intent.putExtra("name",name);
                                intent.putExtra("userName",userName);
                                intent.putExtra("age",age);
                                loginActivity.this.startActivity(intent);
                                */

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
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

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(loginActivity.this);
                queue.add(loginRequest);
            }


        });
        tvNotRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(loginActivity.this,RegisterActivity.class));
            }
        });
    }


}
