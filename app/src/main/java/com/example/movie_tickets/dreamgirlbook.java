package com.example.movie_tickets;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dreamgirlbook extends AppCompatActivity {
ImageView back,movieimage;
Button book;
TextView tv1,tv2,tv3,tv4,tv5,tv6,watchtrailer;
String genre,duration,released,cast,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dreamgirlbook);
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT>16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        back=findViewById(R.id.back);
        book=findViewById(R.id.bookbtn);
        tv1=findViewById(R.id.moviename);
        tv2=findViewById(R.id.genre);
        tv3=findViewById(R.id.duration);
        tv4=findViewById(R.id.released);
        tv5=findViewById(R.id.cast);
        tv6=findViewById(R.id.about);
        watchtrailer=findViewById(R.id.watchtrailer);
        watchtrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String name=intent.getStringExtra("movie_name");

                Intent i=new Intent(dreamgirlbook.this, dreamgirltrailer.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });

        movieimage=findViewById(R.id.movieimage);
        Bundle im=getIntent().getExtras();
        movieimage.setImageResource(im.getInt("img"));
        loadimage();

        getData();
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String name=intent.getStringExtra("movie_name");

                Intent i=new Intent(dreamgirlbook.this,book.class);
                i.putExtra("name",name);
                i.putExtra("genre",genre);
                i.putExtra("duration",duration);
                startActivity(i);

            }
        });
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent i=new Intent(dreamgirlbook.this,movie.class);
               // i.putExtra("name",name);
                startActivity(i);
                return  true;
                //return false;
            }
        });
 }

 public void getData()
 {

     Intent intent=getIntent();
     String name=intent.getStringExtra("movie_name");
     tv1.setText(name);
     RequestQueue rq = Volley.newRequestQueue(this);
     String url = Config.DATA_URL2 +name;
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

         JSONArray jr = jo.getJSONArray(Config.KEY_RESULT2);
         JSONObject jt = jr.getJSONObject(0);
         genre = jt.getString(Config.KEY_GENRE);
         duration= jt.getString(Config.KEY_DURATION);
         released= jt.getString(Config.KEY_RELEASED);
         cast= jt.getString(Config.KEY_CAST);
         about = jt.getString(Config.KEY_ABOUT);
     } catch (JSONException e) {

     }
     tv2.setText(genre);
     tv3.setText(duration);
     tv4.setText(released);
     tv5.setText(cast);
     tv6.setText(about);
 }

    public void loadimage() {

        Intent intent=getIntent();
        String name=intent.getStringExtra("movie_name");
        if(name.equals("Dream Girl")) {
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/dreamgirl1.jpg?alt=media&token=e92f76e3-4f40-4011-a6cc-3e48d4fe9029")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(movieimage);
        }
        else if(name.equals("IT")) {
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/it.jpg?alt=media&token=d9844160-051d-432c-ab87-661bbc20a8b9")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(movieimage);
        }
        else if(name.equals("IT")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(movieimage);
        }
        else if(name.equals("Chhichhore")) {

                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                        .crossFade()
                        .thumbnail(0.5f)
                        //.bitmapTransform(new CircleTransform(getActivity()))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(movieimage);
            }
        else if(name.equals("Section 375")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/section375.jpg?alt=media&token=aa6e8653-617c-4beb-b362-292b9f71bc13")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(movieimage);
        }
        else if(name.equals("Saaho")) {


            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/saaho.jpg?alt=media&token=0c4a979e-e426-4c69-a87f-f72e24c07248")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(movieimage);
        }
    }
    }
