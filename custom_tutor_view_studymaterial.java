package com.example.tution_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_tutor_view_stdmaterial extends BaseAdapter {
    private android.content.Context Context;
    String[] studymaterial;
    String[] subject;
    String[] nid;
    String url="";

    public custom_tutor_view_stdmaterial(android.content.Context applicationContext, String[] studymaterial, String[] subject, String[] nid) {

        this.Context=applicationContext;
        this.studymaterial=studymaterial;
        this.subject=subject;
        this.nid=nid;


    }
    @Override
    public int getCount() {

            return subject.length;
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
            gridView=inflator.inflate(R.layout.custom_tutor_view_stdmaterial, null);



        }
        else
        {
            gridView=(View)convertview;

        }







        TextView tv2=(TextView)gridView.findViewById(R.id.stdsub);
//        ImageView photoz = (ImageView) gridView.findViewById(R.id.stdim);
        TextView tvfname = (TextView) gridView.findViewById(R.id.textView99);
        TextView tvs = (TextView) gridView.findViewById(R.id.textView98);


        Button bts = (Button) gridView.findViewById(R.id.button13);
        bts.setTag(studymaterial[position]);

        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
                String ss=sh.getString("ip", "");
                String url = "http://" + ss + ":8000"+ view.getTag().toString();




                Intent b= new Intent(Intent.ACTION_VIEW);
                b.setDataAndType(Uri.parse(url),"application/pdf");
                b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Context.startActivity(b);

            }
        });






        tv2.setTextColor(Color.BLACK);
        tvs.setText((position+1)+"");


        //tv6.setTextColor(Color.BLACK);
        //tv1.setText(c[position]);
        //tv2.setText(d[position]);


        tv2.setText(subject[position]);
        tvfname.setText(studymaterial[position]);
//        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(Context);
//        url=sh.getString("url","")+studymaterial[position];
//        Picasso.with(Context).load(url).into(photoz);
        return gridView;



//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
//        String ss=sh.getString("ip", "");
//        String url = "http://" + ss + ":5000"+f[position];
//        Toast.makeText(Context, "tstid ass="+url, Toast.LENGTH_LONG).show();
//
//        Picasso.with(Context).load(url).into(im);
////        Picasso.with(Context).load(url).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).transform(new CircleTransform()).into(im);



    }


}





