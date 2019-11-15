package com.example.movie_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class otpsend extends AppCompatActivity {


    Button getotp;
    EditText e1,e21,e22;
    TextView tv1,forget;
    String s1  ,s21,s22 ,fname,femail,fmobile;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsend);
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT>16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
       // e1=findViewById(R.id.number);
        tv1=findViewById(R.id.createacc);
        e21=findViewById(R.id.number);
        e22=findViewById(R.id.pass);
       // getData();
        getotp=findViewById(R.id.getotp);
        forget=findViewById(R.id.forgetpass);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(otpsend.this,forgotPassword.class);
                startActivity(i);

                finish();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(otpsend.this,createAcc.class);
                startActivity(i);
                finish();

            }
        });
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s21 = e21.getText().toString().replace(" ", "");
                s22 = e22.getText().toString().replace(" ", "");
                if(s21.length()==0)

                {
                    e21.requestFocus();
                    e22.setError("FIELD CANNOT BE EMPTY");
                }


                else if(s22.length()==0)
                {
                    e22.requestFocus();
                    e22.setError("FIELD CANNOT BE EMPTY");
                }
                else
                {
                    getData();
                login();
                }
            }
        });


       }

    public void login() {

        RequestQueue rq = Volley.newRequestQueue(this);
        String url = "https://studentmanagement924230.000webhostapp.com/movie/login.php";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contentEquals("success")) {
                    Toast.makeText(otpsend.this, "login in ", Toast.LENGTH_SHORT).show();
///


                    //


                   String mobile = e21.getText().toString().trim();
                    Intent i = new Intent(otpsend.this, movie.class);
                    i.putExtra("name",fname);
                    i.putExtra("email",femail);
                    i.putExtra("mobile", fmobile);
                    startActivity(i);
                } else {
                    Toast.makeText(otpsend.this, "incorrect username or password", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(otpsend.this, "data not login" + error.toString() + "", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<String, String>();

                hm.put("e", s21);
                hm.put("p", s22);
                return hm;
            }
        };
        rq.add(sr);


    }
    ////fetch data
    public void getData()
    {

       /* Intent intent=getIntent();
        String name=intent.getStringExtra("movie_name");
        tv1.setText(name);
       */ RequestQueue rq = Volley.newRequestQueue(this);
        String url = Config.DATA_URL3 +s21;
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ShowJson(response);
                // Toast.makeText(songinfo.this, " ", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(songinfo.this, " " + error, Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(sr);

    }
    public void ShowJson(String response)
    {

        try {
            JSONObject jo = new JSONObject(response);

            JSONArray jr = jo.getJSONArray(Config.KEY_RESULT3);
            JSONObject jt = jr.getJSONObject(0);
            fname = jt.getString(Config.KEY_NAME);
            femail= jt.getString(Config.KEY_EMAIL);
            fmobile= jt.getString(Config.KEY_MOBILE);
           } catch (JSONException e) {

        }
        Toast.makeText(otpsend.this,fname+femail+fmobile,Toast.LENGTH_SHORT).show();

    }

    }

