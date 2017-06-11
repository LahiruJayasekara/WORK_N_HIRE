package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    TextView tvName,tvEmail, tvJobType, tvContactNumber, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String jobType = intent.getStringExtra("jobType");
        String contactNumber = intent.getStringExtra("contactNumber");
        String description = intent.getStringExtra("description");

        tvName = (TextView)findViewById(R.id.tvName);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvJobType = (TextView)findViewById(R.id.tvJobType);
        tvContactNumber = (TextView)findViewById(R.id.tvContactNumber);
        tvDescription = (TextView)findViewById(R.id.tvDescription);




        tvName.setText(name);
        tvEmail.setText(email);
        tvJobType.setText(jobType);
        tvContactNumber.setText(contactNumber);
        tvDescription.setText(description);
    }
}
