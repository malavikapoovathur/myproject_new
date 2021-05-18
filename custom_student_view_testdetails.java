package com.example.tution_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
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

public class custom_student_view_testdetails extends BaseAdapter {
    private android.content.Context Context;
    String[] date;
    String[] subject;
    String[] testname;



    public custom_student_view_testdetails(android.content.Context applicationContext, String[] date, String[] subject, String[] testname) {

        this.Context = applicationContext;
        this.date = date;
        this.subject = subject;
        this.testname = testname;



    }

    @Override
    public int getCount() {
        return date.length;
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


        LayoutInflater inflator = (LayoutInflater) Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (convertview == null) {
            gridView = new View(Context);
            gridView = inflator.inflate(R.layout.custom_student_view_test, null);


        } else {
            gridView = (View) convertview;

        }


        TextView tv0 = (TextView) gridView.findViewById(R.id.textView37);

        TextView tv1 = (TextView) gridView.findViewById(R.id.textView38);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView39);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView40);


        tv0.setText((position+1)+"");


        tv1.setText(date[position]);
        tv2.setText(testname[position]);
        tv3.setText(subject[position]);
//        tv4.setText(status[position]);


        return gridView;
    }
}
