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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class custom_tutor_view_student extends BaseAdapter {

    private android.content.Context Context;
    String[] stid;
    String[] studentname;
    String[] place;
    String[] pin;
    String[] district;
    String[] contactno;
    String[] email;
    String[] photo;
    String[] gender;
    String[] dob;
    String[] parentname;
    String[] relationship;
    String[] parentphno;
    String[] parentemailid;
    String[] BATCH;





    public custom_tutor_view_student(android.content.Context applicationContext, String[] stid,String[] studentname,String[] place,String[] pin,String[] district,String[] contactno,String[] email,String[] photo,String[] gender,String[] dob,String[] parentname,String[] relationship,String[] parentphno,String[] parentemailid,String[] BATCH) {

        this.Context=applicationContext;
        this.stid=stid;
        this.studentname=studentname;
        this.place=place;
        this.pin=pin;
        this.district=district;
        this.contactno=contactno;
        this.email=email;
        this.photo=photo;
        this.gender=gender;
        this.dob=dob;
        this.parentname=parentname;
        this.relationship=relationship;
        this.parentphno=parentphno;
        this.parentemailid=parentemailid;
        this.BATCH=BATCH;


    }

    @Override
    public int getCount() {
        return studentname.length;
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
            gridView=inflator.inflate(R.layout.custom_tutor_view_student, null);



        }
        else
        {
            gridView=(View)convertview;

        }


        ImageView photoz = (ImageView) gridView.findViewById(R.id.imageView2);
        final Button chat=(Button)gridView.findViewById(R.id.button8);
        chat.setTag(stid[position]);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(Context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("to_id",chat.getTag().toString());
                ed.commit();
                Intent ij = new Intent(Context, chats.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Context.startActivity(ij);
            }
        });


        final Button msg=(Button)gridView.findViewById(R.id.button12);
        msg.setTag(stid[position]);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(Context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("slid",chat.getTag().toString());
                ed.commit();
                Intent ij = new Intent(Context, Tutorsentmessagetotparent.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Context.startActivity(ij);
            }
        });


        TextView tvname= (TextView)gridView.findViewById(R.id.textView2);
        TextView tvgender= (TextView)gridView.findViewById(R.id.textView11);
        TextView tvdob= (TextView)gridView.findViewById(R.id.textView13);
        TextView tvcntct= (TextView)gridView.findViewById(R.id.textView3);


        tvname.setText(studentname[position]);
        tvgender.setText(gender[position]);
        tvdob.setText(dob[position]);
        tvcntct.setText(contactno[position]);



        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
        String ss=sh.getString("ip", "");
        String url = "http://" + ss + ":8000"+photo[position];

        Picasso.with(Context).load(url).into(photoz);
        return gridView;
    }


}


