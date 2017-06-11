package com.mlpj.www.angelhack;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckWorkersActivity extends AppCompatActivity {
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_workers);

        listView = (ListView)findViewById(R.id.lvJobTypes);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

        //String search = "";

        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);

                    JSONArray jsonArray = jsonResponse.getJSONArray("workerDetails");
                    int count = 0;
                    String email, jobType,contactNumber,description;
                    String [] arrayJobType = new String[jsonArray.length()];
                    while(count<jsonArray.length()){
                        JSONObject jo = jsonArray.getJSONObject(count);
                        //email = jo.getString("email");
                        jobType = jo.getString("jobType");
                        /*contactNumber = jo.getString("contactNumber");
                        description = jo.getString("description");
                        */
                        arrayJobType[count]=jobType;
                       // Log.v("my tag", name + "," + email + "," + password);
                        count++;
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            CheckWorkersActivity.this,android.R.layout.simple_list_item_1,arrayJobType
                    );
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                            String jobType = ((TextView)view).getText().toString();
                                                            //Toast.makeText(CheckWorkersActivity.this, jobType, Toast.LENGTH_LONG).show();
                                                            Intent intent = new Intent(CheckWorkersActivity.this,DisplayWorkerDetailAcivity.class);
                                                            intent.putExtra("jobType", jobType);
                                                            startActivity(intent);
                                                        }
                                                    }
                    );


                    //if(success){
                       // String name = jsonResponse.getString("name");
                        //int age = jsonResponse.getInt("age");

/*
                        Intent intent = new Intent(MainActivity.this, SelectAddTypeActivity.class);
                        //intent.putExtra("email", email);
                        MainActivity.this.startActivity(intent);
*/
                                /*
                                intent.putExtra("name",name);
                                intent.putExtra("userName",userName);
                                intent.putExtra("age",age);
                                loginActivity.this.startActivity(intent);
                                */
                    /*
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Login failed")
                                .setNegativeButton("Retry",null)
                                .create()
                                .show();
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        ViewRequest viewRequest = new ViewRequest(search,responseListener);
        RequestQueue queue = Volley.newRequestQueue(CheckWorkersActivity.this);
        queue.add(viewRequest);
    }
}
