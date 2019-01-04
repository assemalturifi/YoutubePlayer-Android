package com.example.assemalturifi.youtubeplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//step1
//before i integrate  the youtubeAPI into this app, i have to download the library file from google youtube side first
//after downloading you go to the file, then go to lips and
// copy  youtubeAndroidPIayerAPI.jar and paste it to the project under libs directory
//and later we have to tell android studio how to use this file,the way to do it is that
//you have to add a reference to it in the build gradle file
//this build gradle module file has all sorts of info telling android studio gradle build system itself
//how to build the actual APK file thats going to be deployed to our device or emulator


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //step18
        Button btnSingle = findViewById(R.id.PlaySingle);
        Button btnStandAlone = findViewById(R.id.btnStandAlone);

        btnSingle.setOnClickListener(this);
        btnStandAlone.setOnClickListener(this);


    }

    //step19

    @Override
    public void onClick(View v) {
        Intent intent=null;

        switch(v.getId()){
            case R.id.PlaySingle:
                intent = new Intent(this, YoutubeActivity.class);
                break;
            case R.id.btnStandAlone:
                intent = new Intent(this, StandAloneActivity.class);
                break;

            default:

        }
        if(intent!=null){
            startActivity(intent);
        }

    }
}

