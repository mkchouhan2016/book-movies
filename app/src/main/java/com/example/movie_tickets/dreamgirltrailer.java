package com.example.movie_tickets;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;

public class dreamgirltrailer extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerView youTubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dreamgirltrailer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
     //   getSupportFragmentManager().findFragmentById(R.id.youtube_player_fragment);

        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
          //  player.loadVideo(Config.YOUTUBE_VIDEO_CODE);
          //  String s1="Dream",s2,s3,s4,s5;

        Intent intent=getIntent();
            String name=intent.getStringExtra("name");

            if(name.contentEquals("Dream Girl")){
            final String YOUTUBE_VIDEO_CODE = "nf39Jpi3ZQ4";
player.loadVideo(YOUTUBE_VIDEO_CODE);}

            else    if(name.contentEquals("IT"))
            {
                final String YOUTUBE_VIDEO_CODE = "v16nYGJy7Wk";
                player.loadVideo(YOUTUBE_VIDEO_CODE);}

            else    if(name.contentEquals("Section 365"))
            {
                final String YOUTUBE_VIDEO_CODE = "UWjxS8EJ4Z8";
                player.loadVideo(YOUTUBE_VIDEO_CODE);}

            else    if(name.contentEquals("Chhichhore"))
            {
                final String YOUTUBE_VIDEO_CODE = "tsxemFX0a7k";
                player.loadVideo(YOUTUBE_VIDEO_CODE);}


            else    if(name.contentEquals("Saaho"))
            {
                final String YOUTUBE_VIDEO_CODE = "lD0-ztCFydA";
                player.loadVideo(YOUTUBE_VIDEO_CODE);}


            //UWjxS8EJ4Z8

            player.setPlayerStyle(PlayerStyle.CHROMELESS);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

}