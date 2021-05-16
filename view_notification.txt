package com.example.tution_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
public class Stud_view_notification extends AppCompatActivity {

    ListView lvs;

    String [] date,notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_view_notification);


        lvs=(ListView) findViewById(R.id.lvs);


        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + ":8000/myapp/and_view_notification/";


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
                                notification = new String[jsa.length()];


                                date= new String[jsa.length()];
                                notification= new String[jsa.length()];

                                for (int i = 0; i < jsa.length(); i++) {
                                    JSONObject jsob = jsa.getJSONObject(i);

                                    date[i] = jsob.getString("date");
                                    notification[i] = jsob.getString("notification");


                                }

                                lvs.setAdapter(new custom_student_view_notification(getApplicationContext(),date,notification));

//                                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,subject);
//                                l1.setAdapter(ad);


                                lvs.setAdapter(new custom_student_view_notification(getApplicationContext(), notification,date));
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


//                params.put("uid", sh.getString("lid", ""));


                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        requestQueue.add(postRequest);



    }
}
