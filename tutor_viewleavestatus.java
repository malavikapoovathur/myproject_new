package com.example.tution_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tutor_viewleavestatus extends AppCompatActivity implements View.OnClickListener {
    ListView l1;
    String[] id,date,reasonforleave,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_viewleavestatus);
        l1=(ListView)findViewById(R.id.report);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":8000/myapp/tutorviewleavestatus/";


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //    Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_SHORT).show();
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();


                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String sucs = jsonObj.getString("status");
                            if (sucs.equalsIgnoreCase("ok")) {

//                                subject,batch,class
                                JSONArray jsa = jsonObj.getJSONArray("res2");

                                id = new String[jsa.length()];
                                date = new String[jsa.length()];
                                reasonforleave = new String[jsa.length()];
                                status = new String[jsa.length()];


                                for (int i = 0; i < jsa.length(); i++) {
                                    JSONObject jsob = jsa.getJSONObject(i);

                                    id[i] = jsob.getString("id");
                                    date[i] = jsob.getString("date");
                                    reasonforleave[i] = jsob.getString("reasonforleave");
                                    status[i] = jsob.getString("status");
                                }

                                l1.setAdapter(new custom_tutorviewleavestatus(getApplicationContext(),id,date,reasonforleave,status));
                                //lv1.setAdapter(new Custom5(getApplicationContext(),id,plc,time));
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "eeeee" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();


                params.put("userid", sh.getString("userid", ""));


                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        requestQueue.add(postRequest);


    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),tutor_add_testresult.class));

    }
}



