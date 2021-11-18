package com.example.test_du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView img_logo_slash_screen;
    TextView txt_name_developed, txt_name_land;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler =new Handler();

        img_logo_slash_screen = this.findViewById(R.id.img_logo_splash_screen);

        Animation animation_logo_splash_screen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_logo_splash_screen);
        img_logo_slash_screen.startAnimation(animation_logo_splash_screen);

        txt_name_developed = this.findViewById(R.id.txt_name_developed);
        txt_name_developed.setVisibility(View.INVISIBLE);
        Animation animation_developed = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_text_splash_developed);


        txt_name_land = this.findViewById(R.id.txt_name_land);
        txt_name_land.setVisibility(View.INVISIBLE);
        Animation animation_land = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_text_splash_land);


        animation_logo_splash_screen.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txt_name_developed.setVisibility(View.VISIBLE);
                txt_name_land.setVisibility(View.VISIBLE);
                txt_name_developed.startAnimation(animation_developed);
                txt_name_land.startAnimation(animation_land);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },7000);


    }
}