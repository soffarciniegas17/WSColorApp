package com.worldsills.wscolorapp;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.aparecer);
        animation.setFillAfter(true);

        LinearLayout layout=findViewById(R.id.contenedor_preferencias);
        layout.startAnimation(animation);

        getFragmentManager().beginTransaction().replace(R.id.contenedor_preferencias,new Pref()).commit();


    }
    public void onBackPressed(){
        Intent intent=new Intent(this,Home.class);
        startActivity(intent);
        finish();
    }
    public void startActivity(Intent intent){
        intent.putExtra("tipoP",1);
        super.startActivity(intent);
    }

}
