package com.example.tution_app;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Viewcomplaints extends AppCompatActivity {

    FloatingActionButton fb;
    ListView lvs;

    String [] complaints,date,reply,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomplaints);




        fb=(FloatingActionButton) findViewById(R.id.floatingActionButton);
        lvs=(ListView) findViewById(R.id.lvs);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ins=new Intent(getApplicationContext(),Usersentcomplaint.class);
                startActivity(ins);
            }
        });

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        final String url = "http://" + ip + ":8000/myapp/studentviewcomplaint/";




        final ProgressDialog pd = new ProgressDialog(Viewcomplaints.this);
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            pd.dismiss();
                            JSONObject obj = new JSONObject(new String(response.data));
                            String dis = obj.getString("status");
                            if (dis.equalsIgnoreCase("ok")) {
                                JSONArray ja= obj.getJSONArray("data");


                                date=new String[ja.length()];
                                complaints=new String[ja.length()];
                                reply=new String[ja.length()];
                                status=new String[ja.length()];


                                for (int i=0;i < ja.length() ; i++)
                                {
                                    JSONObject j= ja.getJSONObject(i);
                                    date[i]= j.getString("date");
                                    complaints[i]= j.getString("complaint");
                                    reply[i]= j.getString("reply");
                                    status[i]= j.getString("status");
                                }

                                lvs.setAdapter(new customcomplaints(getApplicationContext(),complaints,date,reply,status));

//                                lvs.setAdapter(new Cust_viewdesignerplan(getApplicationContext(),id,design_name,file,date,amount,description));








                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to sent feedback", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "VOlley error", Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                params.put("lid",sh.getString("userid",""));




                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);





    }
}
