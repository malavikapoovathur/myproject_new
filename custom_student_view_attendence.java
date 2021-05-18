package com.example.tution_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_student_view_attendance extends BaseAdapter {

    private android.content.Context Context;
    String[] id;
    String[] date;
    String[] timeslot;
    String[] status;


    public custom_student_view_attendance(android.content.Context applicationContext, String[] id, String[] date, String[] timeslot, String[] status) {

        this.Context=applicationContext;
        this.id=id;
        this.date=date;
        this.timeslot=timeslot;
        this.status=status;


    }
    @Override
    public int getCount() {
        return id.length;
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
            gridView=inflator.inflate(R.layout.custom_student_view_attendance, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.textView8);

        TextView tv2=(TextView)gridView.findViewById(R.id.textView5);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView10);





        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        //tv6.setTextColor(Color.BLACK);
        //tv1.setText(c[position]);
        //tv2.setText(d[position]);

        tv1.setText(timeslot[position]);
        tv2.setText(date[position]);
        tv3.setText(status[position]);



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




