package com.example.tution_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_student_view_feereport extends BaseAdapter {

    private android.content.Context Context;

    String[]date,month,year,feespaid,status;


    public custom_student_view_feereport(android.content.Context applicationContext, String[] date,String[] month,String[] year,String[] feespaid,String[] status) {

        this.Context=applicationContext;
        this.year=year;
        this.month=month;
        this.date=date;
        this.feespaid=feespaid  ;
        this.status=status  ;
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
            gridView=inflator.inflate(R.layout.custom_studentviewfeereport, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tvyear=(TextView)gridView.findViewById(R.id.textView86);

        TextView tvmonth=(TextView)gridView.findViewById(R.id.textView87);
        TextView tvamount=(TextView)gridView.findViewById(R.id.textView89);
        TextView tvstatus=(TextView)gridView.findViewById(R.id.textView90);

        tvyear.setText(year[position]);
        tvmonth.setText(month[position]);
        tvamount.setText(feespaid[position]);
        tvstatus.setText(status[position]);


//
//
//        tv1.setText(date[position]);
//        tv2.setText(notification[position]);
//        tvno.setText((position+1)+"");

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




