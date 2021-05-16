package com.example.tution_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

public class Parent_view_message extends AppCompatActivity {
    ListView l1;
    String[] message,date,tutorname,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_viewmessage);
        l1=(ListView)findViewById(R.id.lvs);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":8000/myapp/parentviewmsgfromtutor/";


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
                                message = new String[jsa.length()];
                                date= new String[jsa.length()];
                                tutorname= new String[jsa.length()];
                                phone= new String[jsa.length()];

                                for (int i = 0; i < jsa.length(); i++) {
                                    JSONObject jsob = jsa.getJSONObject(i);

                                    message[i] = jsob.getString("message");
                                    tutorname[i] = jsob.getString("tutorname");
                                    date[i] = jsob.getString("date");
                                    phone[i] = jsob.getString("phone");
                                }

                                l1.setAdapter(new custom_parent_view_tutormessage(getApplicationContext(),message,tutorname,date,phone));


//                                l1.setAdapter(new custom_student_view_notification(getApplicationContext(), notification,date));
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


                params.put("sid", sh.getString("userid", ""));


                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        requestQueue.add(postRequest);


    }

}


