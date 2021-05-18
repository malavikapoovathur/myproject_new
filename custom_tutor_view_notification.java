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

public class custom_tutor_view_notification extends BaseAdapter {
    private android.content.Context Context;
    String[] notification;
    String[] batchname;
    String[] date;
    String[] nid;
    String url="";

    public custom_tutor_view_notification(android.content.Context applicationContext, String[] notification, String[] batchname, String[] date, String[] nid) {

        this.Context=applicationContext;
        this.notification=notification;
        this.batchname=batchname;
        this.date=date;
        this.nid=nid;


    }

    @Override
    public int getCount() {
        return notification.length;
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
    public View getView(int position, View convertview, ViewGroup parent) {


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.custom_tutor_view_notification, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.textView5);

        TextView tv2=(TextView)gridView.findViewById(R.id.textView8);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView10);

        Button btn1 = (Button) gridView.findViewById(R.id.button5);
        btn1.setTag(nid[position]);
        btn1.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        final String nid = view.getTag().toString();

                                        RequestQueue requestQueue = Volley.newRequestQueue(Context);
                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(Context);
                                        url="http://" + sh.getString("ip","") + ":8000/myapp/and_delete_notification";
                                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {

//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                                                        // response
                                                        try {
                                                            JSONObject jsonObj = new JSONObject(response);
                                                            String sucs = jsonObj.getString("status");
                                                            if (sucs.equalsIgnoreCase("ok")) {
                                                                Toast.makeText(Context, "Successfully", Toast.LENGTH_SHORT).show();
                                                                Intent ij = new Intent(Context, tutor_view_notification.class);
                                                                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                Context.startActivity(ij);

                                                            } else {
                                                                Toast.makeText(Context, "Unable to delete", Toast.LENGTH_SHORT).show();

                                                            }


                                                        } catch (Exception e) {

                                                            Toast.makeText(Context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
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
                                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(Context);
                                                Map<String, String> params = new HashMap<String, String>();


//                params.put("First", edt_cmt.getText().toString());
                                                params.put("nid", nid);



                                                return params;
                                            }
                                        };
                                        requestQueue.add(postRequest);
                                        postRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                                    }

        });





        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        //tv6.setTextColor(Color.BLACK);
        //tv1.setText(c[position]);
        //tv2.setText(d[position]);

        tv1.setText(date[position]);
        tv2.setText(notification[position]);
        tv3.setText(batchname[position]);



//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
//        String ss=sh.getString("ip", "");
//        String url = "http://" + ss + ":5000"+f[position];
//        Toast.makeText(Context, "tstid ass="+url, Toast.LENGTH_LONG).show();
//
//        Picasso.with(Context).load(url).into(im);
////        Picasso.with(Context).load(url).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).transform(new CircleTransform()).into(im);


        return gridView;
    }


}



