package com.worldsills.wscolorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Home extends AppCompatActivity {

    Button btonPlay, btonScores, btonSetting;
    ImageButton btonExit;
    ImageView logoApp;

    Animation aparecerIzquierda, aparecerDerecha, desaparecerIzquierda, desaparecerDerecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findView();

    }
    public void findView(){
        logoApp=findViewById(R.id.logo_app);
        btonPlay=findViewById(R.id.home_play);
        btonScores=findViewById(R.id.home_socres);
        btonExit=findViewById(R.id.home_exit);
        btonSetting=findViewById(R.id.home_setting);
    }
    public void findAnimations(){
        aparecerIzquierda= AnimationUtils.loadAnimation(this,R.anim.aparecer_desde_izquierda);
    }

    public void botonesHome(View v){

        Intent intent;
        switch (v.getId()){
            case R.id.home_play:
                intent=new Intent(this, Partida.class);
                intent.putExtra("tipoP",0);
                startActivity(intent);
                finish();
                break;
            case R.id.home_socres:
                break;
            case R.id.home_setting:
                intent=new Intent(this, Settings.class);
                startActivity(intent);
                finish();
                break;
            case R.id.home_exit:
                finish();
                break;
        }
    }
}
