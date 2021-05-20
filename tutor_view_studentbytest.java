package com.example.tution_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

public class tutor_view_studentbytestid extends AppCompatActivity {
    ListView l1;
    String[]stid,studentname,place,pin,district,contactno,email,photo,gender,dob,parentname,relationship,parentphno,parentemailid,BATCH;

    Spinner spbatch;

    String [] bid, bname;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_view_studentbytestid);
        l1=(ListView)findViewById(R.id.listview);



                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String ip = sh.getString("ip", "");
                String url = "http://" + ip + ":8000/myapp/sample/";


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
                                        stid = new String[jsa.length()];
                                        studentname = new String[jsa.length()];
                                        place= new String[jsa.length()];
                                        pin= new String[jsa.length()];
                                        district = new String[jsa.length()];
                                        contactno= new String[jsa.length()];
                                        email= new String[jsa.length()];
                                        photo = new String[jsa.length()];
                                        gender= new String[jsa.length()];
                                        dob= new String[jsa.length()];
                                        parentname = new String[jsa.length()];
                                        relationship= new String[jsa.length()];
                                        parentphno= new String[jsa.length()];
                                        parentemailid = new String[jsa.length()];
                                        BATCH= new String[jsa.length()];



                                        for (int i = 0; i < jsa.length(); i++) {
                                            JSONObject jsob = jsa.getJSONObject(i);
                                            stid[i] = jsob.getString("stid");
                                            studentname[i] = jsob.getString("studentname");
                                            place[i] = jsob.getString("place");
                                            pin[i] = jsob.getString("pin");
                                            district[i] = jsob.getString("district");
                                            contactno[i] = jsob.getString("contactno");
                                            email[i] = jsob.getString("email");
                                            photo[i] = jsob.getString("photo");
                                            gender[i] = jsob.getString("gender");
                                            dob[i] = jsob.getString("dob");
                                            parentname[i] = jsob.getString("parentname");
                                            relationship[i] = jsob.getString("relationship");
                                            parentphno[i] = jsob.getString("parentphno");
                                            parentemailid[i] = jsob.getString("parentemailid");
                                            BATCH[i] = jsob.getString("mark");

                                        }
//                                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,subject);
//                                l1.setAdapter(ad);


                                        l1.setAdapter(new custom_tutor_view_student_markentry(tutor_view_studentbytestid.this,stid,studentname,place,pin,district,contactno,email,photo,gender,dob,parentname,relationship,parentphno,parentemailid,BATCH));
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


                params.put("testid", sh.getString("testid",""));


                        return params;
                    }
                };
                postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                requestQueue.add(postRequest);




    }

}

