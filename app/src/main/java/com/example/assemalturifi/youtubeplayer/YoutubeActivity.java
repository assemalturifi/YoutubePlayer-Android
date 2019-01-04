package com.example.assemalturifi.youtubeplayer;

import android.drm.DrmStore;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

//step3 extends YoutubeBaseActivity
//step 4a implements YoutubePlayer.OnInitializedListener and override the two methods
public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {
    private static final String TAG = "YoutubeActivity";

    //step8,after getting youtube player API key
    static final String GOOGLE_API_KEY = "AIzaSyAXxfxAVf9W9bodC3B5Blkv5QDgDA9S53o";
    static final String YOUTUBE_VIDEO_ID = "SJOgTMP8cs4";//this is a youtube videos list of URL after "="
    static final String YOUTUBE_PLAYLIST = "PLvFYFNbi-IBFeP5ALr50hoOmKiYRMvzUq";//this is a youtube videos list of URL after "list="
    //so till now we've added the youtube player widget to our layout and now we got our google api key,
    // it's time to write the code that will actually play the videos for us
    //so the first thing we need to do is initialize the player which is how we tell it where our key is
    //and we are going to do that by calling the players initialize method and we'll use the onCreate() to do that

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //step 5
        //we will add some widgets programatically instead of creating it in the layout
//        setContentView(R.layout.activity_youtube);
//        ConstraintLayout constraintLayout = findViewById(R.id.youtubeActivity);
        //this two lines are equl to the above two codes
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube,null);
        setContentView(layout);

        //step6, we are addig a button to the view
        //so when we create a widget in code, we have to provide the constructor with a context so it knows
        // about the enivornment
        //this is not used that much but it can be very useful for making layouts more dynamic
        //this called adding widgets to layouts dynamically
        //by doing this you have to create all the constraints in code which is very complex and kind a hard
//        Button btn1 = new Button(this);
//        btn1.setLayoutParams(new ConstraintLayout.LayoutParams(300, 80));
//        btn1.setText("Button added");
//        layout.addView(btn1);

        //step7
        //our youtube players view is just going to fill the entire screen space available in the layout,
        // thats why i commented out the above codes and this below code is identical to above codes
        //here instead of specifying the width and height ad dp units i actually used the predefined constant match_parent
        //so here we added youtube payer widgets to the layout
        //step8 get youtube player api key
        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);


        //step9
        playerView.initialize(GOOGLE_API_KEY, this);
        //so onInitializationSuccess() and onInitializationFailure() interfaces method that we created below,
        // so the initialization is successful the player will call onInitializationSuccess
        // if not will call onInitializationFailure methods will be called
        //until now the app is running and video is showing in the emulator




    }


    //step4b
    //we need internet permission
    @Override                                                                                               //wasRestored changed
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        //step4c
        Log.d(TAG, "onInitializationSuccess: provider is "+provider.getClass().toString());
        Toast.makeText(this, "Initialization of Youtube Player was Successful", Toast.LENGTH_LONG).show();


        //step12
        //next step in main activity layout xml
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        //step4c
        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }

    }

    //step4c
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE=1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();

        }
        else {
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());

            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
    //step10
    //our basic application is able to play the video, so what i am gonna do right now is to add some
    //listeners to the youtube player so we can get some feedback when various things happen with that youtube video.
    //so playbackEventListener is an interface so we have to implement all the methods defined by that this interface

    private YouTubePlayer.PlaybackEventListener playbackEventListener=new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this,"Good video is playing ok",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this,"Video is paused",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this,"Video has stopped",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    //step11

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener =new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {


        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this,"Click Ad now, make the video creator rich!",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this,"Video has started",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this,"Video has ended",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
