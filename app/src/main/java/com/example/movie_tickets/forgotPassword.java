package com.example.movie_tickets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class forgotPassword extends AppCompatActivity {
Button getpass;
EditText e1;
String pname="",ppassword="",s11;
    private Object CoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT>16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        e1=findViewById(R.id.pemail);
        getpass=findViewById(R.id.getpassword);
        getpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s11 = e1.getText().toString().replace(" ", "");
                if (s11.length() == 0) {
                    e1.requestFocus();
                    e1.setError("FIELD CANN'T BE NULL");
                }
           else     {    getData();
                new AlertDialog.Builder(forgotPassword.this)
                        .setTitle("Loading...")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("Your password has been send to your mail.....")
                        .setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ss = e1.getText().toString().trim();
                                Intent m = new Intent(forgotPassword.this, otpsend.class);
                                m.putExtra("em", ss);
                                startActivity(m);

                                finish();
                            }
                        })
                        //.setNegativeButton("NO", null)
                        /**/.show();
            } }
        });

    }

    ////fetch data
    public void getData()
    {
String s21=e1.getText().toString();
       /* Intent intent=getIntent();
        String name=intent.getStringExtra("movie_name");
        tv1.setText(name);
       */ RequestQueue rq = Volley.newRequestQueue(this);
        String url = Config.DATA_URL4 +s21;
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

            JSONArray jr = jo.getJSONArray(Config.KEY_RESULT4);
            JSONObject jt = jr.getJSONObject(0);
            pname = jt.getString(Config.KEY_NAM);
            ppassword= jt.getString(Config.KEY_PASSWORD);
        } catch (JSONException e) {

        }
        ////

/*
        Snackbar snackbar = Snackbar
                .make((View) CoordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });

// Changing message text color
        snackbar.setActionTextColor(Color.RED);

// Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();

*/
        //snackbar.show();
    //   Toast.makeText(forgotPassword.this,pname+"!! Your password has been send to your mail",Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {

            public void run() {

                try {

                    GMailSender sender = new GMailSender(

                            "mkchouhan2016@gmail.com",

                            "Lakshar@1");

                    String s21 = e1.getText().toString().replace(" ", "");


                    //    sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/image.jpg");
                    //      s12 = e12.getText().toString().replace(" ", "");


                    sender.sendMail("FORGET PASSWORD", "HEY " +pname+
                                    " Your password for VR cinema is "+ppassword,

                            "mkchouhan2016@gmail.com",

                            s21);

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

                    getEmail();


                }

            }

        }).start();


    }
    public void getEmail()
    {/*
        new Thread(new Runnable() {

            public void run() {

                try {

                    GMailSender sender = new GMailSender(

                            "mkchouhan2016@gmail.com",

                            "Lakshar@1");

                  String s21 = e1.getText().toString().replace(" ", "");


                    //    sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/image.jpg");
              //      s12 = e12.getText().toString().replace(" ", "");


                    sender.sendMail("FORGET PASSWORD", "HEY " +pname+
                                    "Your password for VR cinema is "+ppassword,

                            "mkchouhan2016@gmail.com",

                            s21);

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

                    getEmail();


                }

            }

        }).start();*/
    }

}
