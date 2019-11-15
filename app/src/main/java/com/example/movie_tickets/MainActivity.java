package com.example.movie_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT>16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String e="mkchouhan2018@gmail.com";
                String m="8988080808";
                Intent i=new Intent(MainActivity.this,otpsend.class);
               // i.putExtra("e",e);
               // i.putExtra("m",m);
                startActivity(i);
                finish();
            }
        },3000);

    }
}
