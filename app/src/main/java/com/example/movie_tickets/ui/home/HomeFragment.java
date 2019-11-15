package com.example.movie_tickets.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.movie_tickets.CircleTransform;
import com.example.movie_tickets.Config;
import com.example.movie_tickets.MainActivity;
import com.example.movie_tickets.R;
import com.example.movie_tickets.dreamgirlbook;
import com.example.movie_tickets.dreamgirltrailer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    Button b1,b2,b3,b4,b5;
    ImageView dreamgirl,it,chhore,section,saaho;


    StorageReference storageRef,storageRef1,storageRef2,storageRef3,storageRef4,storageRef5,storageRef6;
    FirebaseStorage storage;
Dialog dialog;

    String name1 = "";
    String cat1 = "";
    String name2 = "";
    String cat2 = "";

    String name3 = "";
    String cat3 = "";
    String name4 = "";
    String cat4 = "";
    String image;
    String name5 = "";
    String cat5 = "";
    private HomeViewModel homeViewModel;
    private SliderLayout mDemoSlider;


    TextView tv,tv2,tv3,tv4,tv5,pname,pname2,pname3,pname4,pname5,pprice,pprice2,pprice3,pprice4,pprice5;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        dreamgirl=root.findViewById(R.id.image21);
        it=root.findViewById(R.id.image22);
        chhore=root.findViewById(R.id.image23);
        section=root.findViewById(R.id.image24);
        saaho=root.findViewById(R.id.image25);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dreamgirl.setTooltipText("click to watch trailer");
        }
        b1=root.findViewById(R.id.book2);
        b2=root.findViewById(R.id.book);
        b3=root.findViewById(R.id.book3);
        b4=root.findViewById(R.id.book4);
        b5=root.findViewById(R.id.book5);
        pname=root.findViewById(R.id.mname1);
        pname2=root.findViewById(R.id.mname2);
        pname3=root.findViewById(R.id.mname3);
        pname4=root.findViewById(R.id.mname4);
        pname5=root.findViewById(R.id.mname5);
        pprice=root.findViewById(R.id.mcat1);
        pprice2=root.findViewById(R.id.mcat2);
        pprice3=root.findViewById(R.id.mcat3);
        pprice4=root.findViewById(R.id.mcat4);
        pprice5=root.findViewById(R.id.mcat5);
        getData();
        loadimage();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), dreamgirlbook.class);
                String moviename=pname.getText().toString();
                i.putExtra("movie_name",moviename);
                startActivity(i);


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), dreamgirlbook.class);
                String moviename=pname2.getText().toString();
                i.putExtra("movie_name",moviename);
                //i.putExtra("img",R.drawable.it);
                startActivity(i);


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), dreamgirlbook.class);
                String moviename=pname3.getText().toString();
                i.putExtra("movie_name",moviename);
                //i.putExtra("img",R.drawable.chhichore);
                startActivity(i);


            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), dreamgirlbook.class);
                String moviename=pname4.getText().toString();
                i.putExtra("movie_name",moviename);
                //i.putExtra("img",R.drawable.section375);

                startActivity(i);


            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(), dreamgirlbook.class);
                String moviename=pname5.getText().toString();
                i.putExtra("movie_name",moviename);
                //i.putExtra("img",R.drawable.saaho);
                startActivity(i);


            }
        });
        dreamgirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///   String name =getActivity().getIntent().getExtras().getString("name");
               //=intent.getStringExtra("movie_name");
                String moviename=pname.getText().toString();

                Intent i=new Intent(getActivity(), dreamgirltrailer.class);
                i.putExtra("name",moviename);
                startActivity(i);
          }
        });

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///   String name =getActivity().getIntent().getExtras().getString("name");
                //=intent.getStringExtra("movie_name");
                String moviename=pname2.getText().toString();

                Intent i=new Intent(getActivity(), dreamgirltrailer.class);
                i.putExtra("name",moviename);
                startActivity(i);
            }
        });
        chhore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///   String name =getActivity().getIntent().getExtras().getString("name");
                //=intent.getStringExtra("movie_name");
                String moviename=pname3.getText().toString();

                Intent i=new Intent(getActivity(), dreamgirltrailer.class);
                i.putExtra("name",moviename);
                startActivity(i);
            }
        });
        section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///   String name =getActivity().getIntent().getExtras().getString("name");
                //=intent.getStringExtra("movie_name");
                String moviename=pname4.getText().toString();

                Intent i=new Intent(getActivity(), dreamgirltrailer.class);
                i.putExtra("name",moviename);
                startActivity(i);
            }
        });
        saaho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///   String name =getActivity().getIntent().getExtras().getString("name");
                //=intent.getStringExtra("movie_name");
                String moviename=pname5.getText().toString();

                Intent i=new Intent(getActivity(), dreamgirltrailer.class);
                i.putExtra("name",moviename);
                startActivity(i);
            }
        });







        mDemoSlider = root.findViewById(R.id.slider);

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
            TextSliderView textSliderView = new TextSliderView(getActivity());
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
        return root;


    }


    @Override
    public void onSliderClick(BaseSliderView slider) {
        String name = String.valueOf(slider.getBundle());
        if (name.equals("PVR CINEMA")) {
            Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        }
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
    public void loadimage(){
        //image  =Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/dreamgirl.jpg?alt=media&token=b6f954b4-48e5-4606-87c8-2b8d883d1a47")

        Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/dreamgirl1.jpg?alt=media&token=e92f76e3-4f40-4011-a6cc-3e48d4fe9029")
                .crossFade()
                .thumbnail(0.5f)
                //.bitmapTransform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(dreamgirl);
        Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/it.jpg?alt=media&token=d9844160-051d-432c-ab87-661bbc20a8b9")
                .crossFade()
                .thumbnail(0.5f)
                //.bitmapTransform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(it);

        Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/chhichore1.jpg?alt=media&token=df8c9906-02e7-49c3-9dea-7b7fbfce878c")
                .crossFade()
                .thumbnail(0.5f)
                //.bitmapTransform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(chhore);

        Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/section375.jpg?alt=media&token=aa6e8653-617c-4beb-b362-292b9f71bc13")
                .crossFade()
                .thumbnail(0.5f)
                //.bitmapTransform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(section);


        Glide.with(getActivity()).load("https://firebasestorage.googleapis.com/v0/b/movietickets-d6897.appspot.com/o/saaho.jpg?alt=media&token=0c4a979e-e426-4c69-a87f-f72e24c07248")
                .crossFade()
                .thumbnail(0.5f)
                //.bitmapTransform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(saaho);
         }
    public void getData()

    {

String s1="1";
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = Config.DATA_URL + s1;
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

    public void ShowJson(String response) {

        try {
            JSONObject jo = new JSONObject(response);

            JSONArray jr = jo.getJSONArray(Config.KEY_RESULT);
            JSONObject jt = jr.getJSONObject(0);
            name1 = jt.getString(Config.KEY_NAME1);
            cat1= jt.getString(Config.KEY_CAT1);
            name2 = jt.getString(Config.KEY_NAME2);
            cat2 = jt.getString(Config.KEY_CAT2);
            name3 = jt.getString(Config.KEY_NAME3);
            cat3= jt.getString(Config.KEY_CAT3);
            name4 = jt.getString(Config.KEY_NAME4);
            cat4 = jt.getString(Config.KEY_CAT4);
            name5 = jt.getString(Config.KEY_NAME5);
            cat5 = jt.getString(Config.KEY_CAT5);
        } catch (JSONException e) {

        }
        pname.setText(name1);
        pprice.setText(cat1);
        pname2.setText(name2);
        pprice2.setText(cat2);
        pname3.setText(name3);
        pprice3.setText(cat3);
        pname4.setText(name4);
        pprice4.setText(cat4);
        pname5.setText(name5);
        pprice5.setText(cat5);


    }}