package com.worldsills.wscolorapp;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class Partida extends AppCompatActivity {

    Button b1, b2, b3, b4;
    Button botones;
    TextView palabra, intemp, contap, bien, mal;
    int palabraP, good, wrong, total, intentos;
    private  int resources []=  {R.drawable.anim_boton_a, R.drawable.anim_boton_az, R.drawable.anim_boton_r
    , R.drawable.anim_boton_v};
    int numeros[];
    Dialog gameover;
    Animation botonani;
    private long timerpa, timerpartida;
    private final String colores[]={"AMARILLO", "AZUL", "ROJO", "VERDE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        b1= findViewById(R.id.b1);
        b2= findViewById(R.id.b2);
        b3= findViewById(R.id.b3);
        b4= findViewById(R.id.b4);

        palabra= findViewById(R.id.palabra);
        contap= findViewById(R.id.contapa);
        intemp= findViewById(R.id.ti_inte);
        bien = findViewById(R.id.good);


        gameover= new Dialog(this);
        gameover.setContentView(R.layout.dialog_final);
        gameover.setCanceledOnTouchOutside(false);
        gameover.setCancelable(false);

        botonani= AnimationUtils.loadAnimation(this, R.anim.aparecer);

        good=0;
        wrong=0;
        total=0;
        JuegaBoton();

    }

   private int azar (){
        Random a= new Random();
        int num= a.nextInt(colores.length);
        return num;
    }


    private void mostrarbotones (){
        numeros = new int [4];
        for (int i=0; i<numeros.length; i++){
            numeros[i]=-1;
        }
        int po=0;
        do {
            int num = azar();
            if (numeros[num]==-1){
                numeros[num] = po;
                po++;
            }
        } while (po<4);

        b1.setBackgroundResource(resources[numeros[0]]);
        b2.setBackgroundResource(resources[numeros[1]]);
        b3.setBackgroundResource(resources[numeros[2]]);
        b4.setBackgroundResource(resources[numeros[3]]);

        b1.startAnimation(botonani);
        b2.startAnimation(botonani);
        b3.startAnimation(botonani);
        b4.startAnimation(botonani);

    }

    public void JuegaBoton (){
        total++;
        asignarcolor();
        mostrarbotones();
        palabra.setText(colores[azar()]);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparar(numeros[0]);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparar(numeros[1]);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparar(numeros[2]);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comparar(numeros[3]);
            }
        });
    }



    @TargetApi(23)
    private void comparar (int p){

        if(palabraP==p){
            good++;
            JuegaBoton();
        } else {
            wrong++;
        }

        if(intentos==0){
            finalzaPartida();
        }
    }


    @TargetApi(23)
   private void asignarcolor (){
        palabraP= azar();
       switch (palabraP){
           case 0:
               palabra.setTextColor(getResources().getColor(R.color.amarilloclaro,null));
               break;
           case 1:
               palabra.setTextColor(getResources().getColor(R.color.azulclaro, null));
               break;
           case 2:
               palabra.setTextColor(getResources().getColor(R.color.rojoclaro, null));
               break;
           case 3:
               palabra.setTextColor(getResources().getColor(R.color.verdeclaro, null));
               break;
       }
   }

    CountDownTimer partidatimer;
    public void tiempoPartida(){
        palabratimer= new CountDownTimer(timerpartida, 100) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    CountDownTimer palabratimer;
    public void tiempoPalabra(){
        palabratimer= new CountDownTimer(timerpa, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                wrong++;
                JuegaBoton();
            }
        }.start();

    }


    private void finalzaPartida() {
        gameover.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }


}
