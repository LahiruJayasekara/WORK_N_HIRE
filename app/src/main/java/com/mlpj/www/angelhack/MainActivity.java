package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = (EditText)findViewById(R.id.etSearch);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_login:
                Intent intent = new Intent(this, loginActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_register:
                Intent intent1 = new Intent(this, RegisterActivity.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickCheckJobs(View view){
        /*Intent intent = new Intent(this,CheckWorkersActivity.class);
        String txt = "";
        intent.putExtra("search", txt);
        startActivity(intent);*/
    }

    public void onClickCheckWorkers(View view){
        Intent intent = new Intent(this,CheckWorkersActivity.class);
        String txt = "";
        intent.putExtra("search", txt);
        startActivity(intent);

    }


    public void onClickSearch(View view){
        Intent intent = new Intent(this,CheckWorkersActivity.class);
        String txt = etSearch.getText().toString();
        intent.putExtra("search", txt);
        startActivity(intent);

    }
}
