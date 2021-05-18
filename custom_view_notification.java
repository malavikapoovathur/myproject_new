package com.example.tution_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_student_view_notification extends BaseAdapter {

    private android.content.Context Context;

    String[] notification;
    String[] date;


    public custom_student_view_notification(android.content.Context applicationContext, String[] notification, String[] date) {

        this.Context=applicationContext;
        this.notification=notification;
        this.date=date;
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
            gridView=inflator.inflate(R.layout.custom_student_view_notification, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.textView41);

        TextView tv2=(TextView)gridView.findViewById(R.id.textView42);
        TextView tvno=(TextView)gridView.findViewById(R.id.textView43);




        tv1.setText(date[position]);
        tv2.setText(notification[position]);
        tvno.setText((position+1)+"");

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




