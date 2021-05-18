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

public class custom_tutor_view_salary extends BaseAdapter {
    private android.content.Context Context;
    String[] year,month,amount,date;


    public custom_tutor_view_salary(android.content.Context applicationContext, String[] year,String[] month,String[] amount,String[] date) {

        this.Context=applicationContext;
        this.year=year;
        this.month=month;
        this.amount=amount;
        this.date=date;


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


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.custom_tutor_view_salary, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tvyear=(TextView)gridView.findViewById(R.id.textView25);
        TextView tvmonth=(TextView)gridView.findViewById(R.id.textView26);
        TextView tvamount=(TextView)gridView.findViewById(R.id.textView27);
        TextView tvdate=(TextView)gridView.findViewById(R.id.textView28);

        tvyear.setText(year[position]);
        tvmonth.setText(month[position]);
        tvamount.setText(amount[position]);
        tvdate.setText(date[position]);



        return gridView;
    }


}



