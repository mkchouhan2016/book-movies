package com.example.movie_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class confirmticket extends AppCompatActivity {
    Button click;
    TextView tv11, tv12, tv13, people, tamt, sum1;
ImageView img,whatapp;
String s;
    String mname,duration,genre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmticket);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
img=findViewById(R.id.confirmimg);
        tv11 = findViewById(R.id.moviename11);
        tv12 = findViewById(R.id.type11);
        loadimage();

        tv13 = findViewById(R.id.duration11);
        people = findViewById(R.id.ppl);
        whatapp
                =findViewById(R.id.whatapp);
        whatapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //  sum1.setText(mname);
                String content=
                        "        <h2>VR CINEMA</h2>\n" +
                                "        <ul>\n" +
                                "        <li>YOUR MOVIE TICKETS FOR "+mname+" confirmed</li>\n" +
                                "        <li>BOOKING ID:7252924230mkchouhan2016</li>\n" +
                                "        <li>Please note your tickets informatoon</li>\n" +
                                "        </ul>\n";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Spanned c= Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);
                    s=c.toString();
                    // textView11.setText(c);
                } else {
                    // textView11.setText(Html.fromHtml(content));
                }
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT,s);
                startActivity(Intent.createChooser(i,"mkmk"));
            }
        });
        tamt = findViewById(R.id.ticketamt);
        sum1=findViewById(R.id.totalamount);
        Intent intent = getIntent();

        mname = intent.getStringExtra("name");
        genre = intent.getStringExtra("genre");
        duration = intent.getStringExtra("duration");

        String time = intent.getStringExtra("time");
        tv11.setText(mname);
        tv12.setText(genre);
        tv13.setText(duration + " | " + time);

        String ppl = intent.getStringExtra("ppl");
        String totalamt = intent.getStringExtra("totalamt");
        Integer amount = Integer.parseInt(totalamt);
        Integer n1 = 108, n2 = 9, n3 = 9;
        Integer sum = amount + n1 + n2 + n3;
        String sam=sum.toString();
        people.setText(ppl);
        tamt.setText(totalamt);
     sum1.setText(sam);

        //  Toast.makeText(confirmticket.this,"infor:-"+name+email+mobile,Toast.LENGTH_SHORT).show();


    }
    public void loadimage() {

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        if(name.equals("Dream Girl")) {
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/dreamgirl1.jpg?alt=media&token=e92f76e3-4f40-4011-a6cc-3e48d4fe9029")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
        else if(name.equals("IT")) {
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/it.jpg?alt=media&token=d9844160-051d-432c-ab87-661bbc20a8b9")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
        else if(name.equals("IT")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
        else if(name.equals("Chhichhore")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
        else if(name.equals("Section 375")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/section375.jpg?alt=media&token=aa6e8653-617c-4beb-b362-292b9f71bc13")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
        else if(name.equals("Saaho")) {


            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/saaho.jpg?alt=media&token=0c4a979e-e426-4c69-a87f-f72e24c07248")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img);
        }
    }
}
