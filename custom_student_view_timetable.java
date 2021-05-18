package com.example.tution_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_student_view_timetable  extends BaseAdapter {

    private android.content.Context Context;

    String[] DATE;
    String[] SUBJECT1;
    String[] TUTOR1;
    String[] SUBJECT2;
    String[] TUTOR2;
    String[] SUBJECT3;
    String[] TUTOR3;
    String[] SUBJECT4;
    String[] TUTOR4;

    public custom_student_view_timetable(android.content.Context applicationContext, String[] DATE,String[] SUBJECT1,String[] TUTOR1,String[] SUBJECT2,String[] TUTOR2,String[] SUBJECT3,String[] TUTOR3,String[] SUBJECT4,String[] TUTOR4) {

        this.Context=applicationContext;
        this.DATE=DATE;
        this.SUBJECT1=SUBJECT1;
        this.TUTOR1=TUTOR1;
        this.SUBJECT2=SUBJECT2;
        this.TUTOR2=TUTOR2;
        this.SUBJECT3=SUBJECT3;
        this.TUTOR3=TUTOR3;
        this.SUBJECT4=SUBJECT4;
        this.TUTOR4=TUTOR4;

    }

    @Override
    public int getCount() {
        return SUBJECT1.length;
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
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.custom_student_view_timetable, null);



        }
        else
        {
            gridView=(View)convertview;

        }

        TextView tv1=(TextView)gridView.findViewById(R.id.textView2);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView4);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView6);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView66);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView8);
        TextView tv7=(TextView)gridView.findViewById(R.id.textView88);
        TextView tv8=(TextView)gridView.findViewById(R.id.textView10);
        TextView tv9=(TextView)gridView.findViewById(R.id.textView1010);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
        tv7.setTextColor(Color.BLACK);
        tv8.setTextColor(Color.BLACK);
        tv9.setTextColor(Color.BLACK);




        tv1.setText(DATE[position]);
        tv2.setText(SUBJECT1[position]);
        tv3.setText(TUTOR1[position]);
        tv4.setText(SUBJECT2[position]);
        tv5.setText(TUTOR2[position]);
        tv6.setText(SUBJECT3[position]);
        tv7.setText(TUTOR3[position]);
        tv8.setText(SUBJECT4[position]);
        tv9.setText(TUTOR4[position]);



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


S