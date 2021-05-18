package com.example.tution_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_tutor_view_testdetails extends BaseAdapter {
    private android.content.Context Context;
    String[]testid,subject,date,testname,instruction;


    public custom_tutor_view_testdetails(android.content.Context applicationContext, String[] testid, String[] subject, String[] date, String[] testname,String [] instruction) {

        this.Context=applicationContext;
        this.testid=testid;
        this.subject=subject;
        this.date=date;
        this.testname=testname;
        this.instruction=instruction;


    }

    @Override
    public int getCount() {
        return testid.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, final View convertview, ViewGroup parent) {


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.custom_tutor_view_testdetails, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.tdate);
        final ImageView im1=(ImageView) gridView.findViewById(R.id.imageView8);
        final Button bme=(Button) gridView.findViewById(R.id.button14);

        TextView tv2=(TextView)gridView.findViewById(R.id.tname);
        TextView tv3=(TextView)gridView.findViewById(R.id.tsub);
        TextView tvinstructions=(TextView)gridView.findViewById(R.id.textView48);


        im1.setTag(testid[position]);
        bme.setTag(testid[position]);


        bme.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
                SharedPreferences.Editor ed= sh.edit();
                ed.putString("testid",view.getTag().toString());
                ed.commit();


                Intent ins = new Intent(Context,tutor_view_studentbytestid.class);
                ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Context.startActivity(ins);


            }
        });


        im1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                final String sid= im1.getTag().toString();


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(Context);
                String ip = sh.getString("ip", "");
                String url = "http://" + ip + ":8000/myapp/tutor_delete_testdetails/";


                RequestQueue requestQueue = Volley.newRequestQueue(Context);
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

                                        Toast.makeText(Context,"Deleted Successfully",Toast.LENGTH_LONG).show();

                                        Intent ins= new Intent(Context,tutor_view_test_details.class);
                                        ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        Context.startActivity(ins);


                                    }
                                    else
                                    {
                                        Toast.makeText(Context,"Failed to delete",Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(Context, "eeeee" + e.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(Context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("id", sid);


                        return params;
                    }
                };
                postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                requestQueue.add(postRequest);









            }
        });


        tv1.setText(date[position]);
        tv2.setText(testname[position]);
        tv3.setText(subject[position]);
        tvinstructions.setText(instruction[position]);

        return gridView;
    }


}



