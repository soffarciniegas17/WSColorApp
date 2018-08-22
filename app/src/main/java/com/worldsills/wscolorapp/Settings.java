package com.worldsills.wscolorapp;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction().replace(R.id.contenedor_preferencias,new Pref()).commit();


    }
    public void onBackPressed(){
        Intent intent=new Intent(this,Home.class);
        startActivity(intent);
        finish();
    }

}
