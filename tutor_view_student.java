package com.example.tution_app;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class tutor_view_student extends AppCompatActivity {
    ListView l1;
    String[]stid,studentname,place,pin,district,contactno,email,photo,gender,dob,parentname,relationship,parentphno,parentemailid,BATCH;

    Spinner spbatch;

    String [] bid, bname;


    public void loadspinner()
    {
        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/and_tutorviewassignedbatches/";
        //  Toast.makeText(getApplicationContext(),"tt="+url,Toast.LENGTH_LONG).show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //
                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String sucs = jsonObj.getString("status");
                            if (sucs.equalsIgnoreCase("ok")) {

//                                subject,batch,class
                                JSONArray jsa = jsonObj.getJSONArray("data");
                                bid = new String[jsa.length()];
                                bname= new String[jsa.length()];



                                for (int i = 0; i < jsa.length(); i++) {
                                    JSONObject jsob = jsa.getJSONObject(i);

                                    bid[i] = jsob.getString("bid");
                                    bname[i] = jsob.getString("batchname");




                                }
                                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,bname);
                                spbatch.setAdapter(ad);


//                                sp.setAdapter(new custom_view_allocated_subject(getApplicationContext(),bid,batchname));
                                //lv1.setAdapter(new Custom5(getApplicationContext(),id,plc,time));
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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

                params.put("lid",sh.getString("userid",""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_view_student);
        l1=(ListView)findViewById(R.id.listview);

        spbatch=(Spinner) findViewById(R.id.spinner3);
        loadspinner();

        spbatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,final int position, long l) {


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String ip = sh.getString("ip", "");
                String url = "http://" + ip + ":8000/myapp/and_tutor_view_studentbybatchid/";


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
                                            BATCH[i] = jsob.getString("BATCH");

                                        }
//                                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,subject);
//                                l1.setAdapter(ad);


                                        l1.setAdapter(new custom_tutor_view_student(getApplicationContext(),stid,studentname,place,pin,district,contactno,email,photo,gender,dob,parentname,relationship,parentphno,parentemailid,BATCH));
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

                        params.put("bid", bid[position]);
                        return params;
                    }
                };
                postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                requestQueue.add(postRequest);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

}

