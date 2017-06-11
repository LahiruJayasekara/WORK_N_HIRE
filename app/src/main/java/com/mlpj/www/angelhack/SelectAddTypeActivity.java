package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class SelectAddTypeActivity extends AppCompatActivity {

    String email, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_add_type);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        name = intent.getStringExtra("name");
    }

    public void onClickGotoServiceAdd(View view){
        Intent intent = new Intent(this,ServiceAddActivity.class);
        intent.putExtra("email",email);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    public void onClickGotoJobAdd(View view){
        Intent intent = new Intent(this,JobAddActivity.class);
        intent.putExtra("email",email);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
