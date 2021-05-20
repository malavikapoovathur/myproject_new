package com.example.tution_app;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Usersentfeedback extends AppCompatActivity {

    EditText edcomplaint;
    Button btcomplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersentfdbk);

        edcomplaint=(EditText) findViewById(R.id.editText);
        btcomplaint=(Button) findViewById(R.id.button2);

        btcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String complaint= edcomplaint.getText().toString();

                if(complaint.length()==0)
                {
                    edcomplaint.setError("Missing");
                }
                else
                {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String ip = sh.getString("ip", "");
                    final String url = "http://" + ip + ":8000/myapp/studentsentfeedback/";


                     final ProgressDialog pd = new ProgressDialog(Usersentfeedback.this);
                    pd.setMessage("Uploading....");
                    pd.show();
                    VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                            new Response.Listener<NetworkResponse>() {
                                @Override
                                public void onResponse(NetworkResponse response) {
                                    try {
                                        pd.dismiss();
                                        JSONObject obj = new JSONObject(new String(response.data));
                                        String dis = obj.getString("status");
                                        if (dis.equalsIgnoreCase("ok")) {


                                            Toast.makeText(getApplicationContext(), "Feedback sent successfully", Toast.LENGTH_LONG).show();


                                            Intent ins =new Intent(getApplicationContext(),student.class);
                                            startActivity(ins);


                                        } else {
                                            Toast.makeText(getApplicationContext(), "Failed to sent Feedback" , Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }) {


                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                            params.put("complaint", complaint);
                            params.put("lid", sh.getString("userid",""));


                            return params;
                        }


                        @Override
                        protected Map<String, DataPart> getByteData() {
                            Map<String, DataPart> params = new HashMap<>();
                            return params;
                        }
                    };

                    Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
                }











            }
        });

    }
}
