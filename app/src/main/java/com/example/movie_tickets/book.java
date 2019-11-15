package com.example.movie_tickets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.movie_tickets.ui.home.HomeViewModel;

import java.util.HashMap;

public class book extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    String time[] = {"10:40 AM", "11:50 AM", "1:50 PM", "3:00 PM", "5:20 PM", "6:40 PM", "8:20 PM", "10:20 PM", "11:50 PM"};
    String cost[] = {"125", "175", "250", "325"};
    String ppl[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    ImageView bookimg;
    Spinner sp1,sp2,sp3;


    private HomeViewModel homeViewModel;
    private SliderLayout mDemoSlider;
    // array
    ArrayAdapter<String> advp1;
    ArrayAdapter<String> advp2;
    ArrayAdapter<String> advp3;
    EditText mobile;
   Button confirm;
TextView tv1,tv2,tv3,textView11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
textView11=findViewById(R.id.textview12);
////important notes

        String content=
                "        <h2>IMPORTANT NOTES</h2>\n" +
                "        <ul>\n" +
                "        <li>check your seats.It will not change again</li>\n" +
                "        <li>Confirm your time correctly</li>\n" +
                "        <li>Please note your tickets informatoon</li>\n" +
                "        </ul>\n";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Spanned c= Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY);
             textView11.setText(c);
        } else {
            textView11.setText(Html.fromHtml(content));
        }

        ////

        bookimg=findViewById(R.id.bookimg);
        loadimage();
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT>16){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        sp1=findViewById(R.id.spinner1);
        sp2=findViewById(R.id.spinner2);
        sp3=findViewById(R.id.spinner3);
        mobile=findViewById(R.id.mobile);
        confirm=findViewById(R.id.confirm);
        tv1=findViewById(R.id.moviename);
        tv2=findViewById(R.id.type);
        tv3=findViewById(R.id.duration);

        Intent intent=getIntent();
        String mname=intent.getStringExtra("name");
        String genre=intent.getStringExtra("genre");
        String duration=intent.getStringExtra("duration");
        tv1.setText(mname);
        tv2.setText(genre);
        tv3.setText(duration);


        advp1=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,time);
        advp2=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,cost);
        advp3=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,ppl);
        sp1.setAdapter(advp1);
        sp2.setAdapter(advp2);
        sp3.setAdapter(advp3);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nt=sp1.getSelectedItem().toString();
                Integer num=Integer.parseInt(sp2.getSelectedItem().toString());
                Integer num2=Integer.parseInt(sp3.getSelectedItem().toString());
                Integer  totalamtt=num*num2;
                String nt2=num.toString();
                String nt3=num2.toString()+" Tickets @"+nt2+"per head";
                String total=totalamtt.toString();


                Intent intent=getIntent();
                String mname=intent.getStringExtra("name");
                String genre=intent.getStringExtra("genre");
                String duration=intent.getStringExtra("duration");
                Intent i=new Intent(book.this,confirmticket.class);
                i.putExtra("name",mname);
                i.putExtra("genre",genre);
                i.putExtra("duration",duration);
                i.putExtra("time",nt);
                i.putExtra("totalamt",total);
                i.putExtra("ppl",nt3);
                startActivity(i);
                finish();
            }
        });

        ///sliding adddss
        mDemoSlider = findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("PVR CINEMA","https://2.bp.blogspot.com/-USJoydWAvCg/WGzk6g4ewmI/AAAAAAAATcQ/D-96W5MyRGkELVWPoA4sk0AYasbVV6YtACLcB/s1600/PVR-cinemas-loot-offer.png");
        url_maps.put("UPCOMING MOVIE #WAR", "https://akm-img-a-in.tosshub.com/indiatoday/images/story/201908/war-770x433.png?4IE3FtYosWCLS4EUQbDNvX5xtxcgXzZQ");
        url_maps.put("paytm", "https://promocodeclub.com/wp-content/uploads/2016/11/paytm-movie-tickets-pcc.jpg");
        url_maps.put("Syra Narishma Reddy", "https://i0.wp.com/www.socialnews.xyz/wp-content/uploads/2018/08/21/sye-raa-narasimha-reddy-horizontal-poster-.jpg?w=960&quality=90&zoom=1&ssl=1");
        //  url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

       /* HashMap<String,Integer> file_maps = new HashMap<String, Integer>();

        file_maps.put("Tickets",R.drawable.tickets);
        file_maps.put("Cinema",R.drawable.pvr);
       // file_maps.put("PAYTM ",R.drawable.paytm);
        file_maps.put("Dream Girl", R.drawable.dreamgirl);
        file_maps.put("Upcoming movie", R.drawable.war);
*/
        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
  //      return root;

        /////


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
                    .into(bookimg);
        }
        else if(name.equals("IT")) {
            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/it.jpg?alt=media&token=d9844160-051d-432c-ab87-661bbc20a8b9")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bookimg);
        }
        else if(name.equals("IT")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bookimg);
        }
        else if(name.equals("Chhichhore")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bookimg);
        }
        else if(name.equals("Section 375")) {

            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/section375.jpg?alt=media&token=aa6e8653-617c-4beb-b362-292b9f71bc13")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bookimg);
        }
        else if(name.equals("Saaho")) {


            Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/saaho.jpg?alt=media&token=0c4a979e-e426-4c69-a87f-f72e24c07248")
                    .crossFade()
                    .thumbnail(0.5f)
                    //.bitmapTransform(new CircleTransform(getActivity()))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bookimg);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(book.this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
