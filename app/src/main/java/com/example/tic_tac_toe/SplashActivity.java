package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView=findViewById(R.id.imageView);
        Animation splash_anim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation splash_anim1 = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        imageView.startAnimation(splash_anim);
        TextView textView=findViewById(R.id.textView);
        textView.startAnimation(splash_anim1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), InputActivity.class);
                startActivity(intent);
                //To disable default transition between layouts
                overridePendingTransition(0,0);
                finish();
            }
        }, 5000);
    }
}
