package com.worldsills.wscolorapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iniciaHome();
            }
        },2000);
    }
    @TargetApi(20)
    public void iniciaHome(){
        Intent intent=new Intent(this, Home.class);
        ImageView imagenLogo=findViewById(R.id.logo_app);

        Pair pair=new Pair(imagenLogo,"t_logo_app");
        ActivityOptionsCompat op=ActivityOptionsCompat.makeSceneTransitionAnimation(this,pair);
        startActivity(intent, op.toBundle());
        finish();
    }
}
