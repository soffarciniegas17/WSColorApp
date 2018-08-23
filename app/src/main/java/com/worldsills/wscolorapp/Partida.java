package com.worldsills.wscolorapp;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class Partida extends AppCompatActivity {

    Button b1, b2, b3, b4;
    TextView palabra, intemp, contap, bien;
    int palabraP, good, wrong, total, intentos, mod;
    private  int resources []=  {R.drawable.anim_boton_a,
               R.drawable.anim_boton_az,
               R.drawable.anim_boton_r
              , R.drawable.anim_boton_v};
    int numeros[];
    Dialog gameover;
    Animation botonani;
    private long timerpa;
    private final String colores[]={"AMARILLO", "AZUL", "ROJO", "VERDE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);

        palabra = findViewById(R.id.palabra);
        contap = findViewById(R.id.contapa);
        intemp = findViewById(R.id.ti_inte);
        bien = findViewById(R.id.good);


        gameover = new Dialog(this);
        gameover.setContentView(R.layout.dialog_final);
        gameover.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        gameover.setCanceledOnTouchOutside(false);
        gameover.setCancelable(false);

        botonani = AnimationUtils.loadAnimation(this, R.anim.aparecer);

        good = 0;
        wrong = 0;
        total=0;
        intentos=3;
        timerpa=2000;

        juego();

      /*  Bundle recuperar= getIntent().getExtras();

        try  {
            mod= recuperar.getInt("tipoP");
            if(mod==0){
                intentos=3;
                timerpa=2000;
                juego();
            }
        } catch (Exception e){}*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        String modopartida;
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPreferences.getString("MODO_PARTIDA", "INTENTOS").equalsIgnoreCase("TIEMPO")){
            tiempoPartida(30000);
            mod=1;
            juego();
        } else {
            mod=2;
            intentos = Integer.parseInt(sharedPreferences.getString("INTENTOS", "3"));
        }

        timerpa= (Integer.parseInt(sharedPreferences.getString("DURACION_PALABRA", "1000")))*1000;

    }

    private int azar (){
        Random a= new Random();
        int num= a.nextInt(colores.length);
        return num;
    }

    private void mostrarbotones (){
        numeros = new int [4];
        for (int i=0; i<numeros.length; i++){
            numeros[i]=-2;
        }
        int po=0;
        do {
            int num = azar();
            if (numeros[num]==-2){
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

    public void juego (){
        total++;
        mostrarbotones();
        asignarcolor();
        palabra.setText(colores[azar()]);
        botonosclick();

        tiempoPalabra(timerpa);

        bien.setText("Correctas \n" +good);
        contap.setText("Total \n "+ total);
        intemp.setText("Intentos \n "+intentos);
    }


    public void botonosclick(){
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
        try {
            palabratimer.cancel();

        }catch (Exception e){}


        if(palabraP==p){
            good++;
            juego();

        } else {
            intentos--;

        }

        if(intentos==0){
            finalzaPartida();
                } else {
            juego();
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


   ////////////////// TIEMPOS /////////////////////////////////////

    CountDownTimer partidatimer;
    public void tiempoPartida(long tiempopartida){
        palabratimer= new CountDownTimer(tiempopartida, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                intemp.setText("Tiempo \n "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                finalzaPartida();
            }
        }.start();

    }

    CountDownTimer palabratimer;
    public void tiempoPalabra(long time){
        palabratimer= new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                Toast.makeText(Partida.this, "ENTRAR", Toast.LENGTH_SHORT).show();
                intentos--;
                if(intentos==0){
                    finalzaPartida();
                } else {
                    juego();
                }

            }
        }.start();

    }


    private void finalzaPartida() {
        try {
            palabratimer.cancel();
        }catch (Exception e){}

        ImageButton home, again, red;
        TextView finalCorrectas, finalInco;

        home= gameover.findViewById(R.id.final_bton_home);
        again= gameover.findViewById(R.id.final_bton_replay);
        red= gameover.findViewById(R.id.final_bton_shared);

        finalCorrectas= gameover.findViewById(R.id.final_correctas);
        finalInco= gameover.findViewById(R.id.final_incorrectas);

        finalCorrectas.setText(""+good);
        finalInco.setText(""+(total-good));

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Partida.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameover.dismiss();
                juego();
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedesSociales compartir = new RedesSociales(Partida.this);
                compartir.compartir(good);

            }
        });


        gameover.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            partidatimer.cancel();
        } catch (Exception e){}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }


}
