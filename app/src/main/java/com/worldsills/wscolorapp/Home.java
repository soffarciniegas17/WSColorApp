package com.worldsills.wscolorapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private Button btonPlay, btonScores, btonSetting;
    private ImageButton btonExit;
    private ImageView logoApp;

    private Animation aparecerIzquierda, aparecerDerecha, desaparecerIzquierda, desaparecerDerecha, aparecer, desaparecer;

    private String[] top5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findView();
        findAnimations();

        top5=new String[5];

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animaEntrada();
                LinearLayout contenedorBotones=findViewById(R.id.contenedor_botones);
                contenedorBotones.setVisibility(View.VISIBLE);
            }
        },500);

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
        aparecerIzquierda.setFillAfter(true);

        aparecerDerecha= AnimationUtils.loadAnimation(this,R.anim.aparecer_desde_derecha);
        aparecerDerecha.setFillAfter(true);

        desaparecerIzquierda= AnimationUtils.loadAnimation(this,R.anim.desaparecer_hacia_izquierda);
        desaparecerIzquierda.setFillAfter(true);

        desaparecerDerecha= AnimationUtils.loadAnimation(this,R.anim.desaparecer_hacia_derecha);
        desaparecerDerecha.setFillAfter(true);

        aparecer=AnimationUtils.loadAnimation(this,R.anim.aparecer);
        aparecer.setFillAfter(true);
        desaparecer=AnimationUtils.loadAnimation(this,R.anim.desaparecer);
        desaparecer.setFillAfter(true);


    }
    public void animaEntrada(){
        btonPlay.startAnimation(aparecerIzquierda);
        btonSetting.startAnimation(aparecerIzquierda);

        btonScores.startAnimation(aparecerDerecha);
        btonExit.startAnimation(aparecerDerecha);



    }
    public void animaSalidad(){
        btonPlay.startAnimation(desaparecerIzquierda);
        btonSetting.startAnimation(desaparecerIzquierda);

        btonScores.startAnimation(desaparecerDerecha);
        btonExit.startAnimation(desaparecerDerecha);

        logoApp.startAnimation(desaparecer);

    }

    public void botonesHome(View v){


        switch (v.getId()){
            case R.id.home_play:
                accionClick(0);
                break;
            case R.id.home_socres:
                accionClick(1);
                break;
            case R.id.home_setting:
                accionClick(2);

                break;
            case R.id.home_exit:
                accionClick(3);

                break;
        }
    }
    private Intent intent;
    public void accionClick(final int ACTION) {

        animaSalidad();
        desaparecerIzquierda.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                switch (ACTION) {
                    case 0:
                        intent = new Intent(getApplicationContext(), Partida.class);
                        intent.putExtra("tipoP", 0);
                        startActivity(intent);
                        finish();
                        break;


                    case 1:
                        abreScores();
                        break;


                    case 2:
                        intent = new Intent(getApplicationContext(), Settings.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 3:

                        finish();
                        break;

                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void abreScores(){
        final Dialog dialogScores=new Dialog(this);
        dialogScores.setContentView(R.layout.dialog_scores);
        dialogScores.setCanceledOnTouchOutside(false);
        dialogScores.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView[] puntajes=new TextView[5];
        puntajes[0]=dialogScores.findViewById(R.id.p1);
        puntajes[1]=dialogScores.findViewById(R.id.p2);
        puntajes[2]=dialogScores.findViewById(R.id.p3);
        puntajes[3]=dialogScores.findViewById(R.id.p4);
        puntajes[4]=dialogScores.findViewById(R.id.p5);

        cargarDatosPuntajes();

        for (int i=0; i<puntajes.length; i++)puntajes[i].setText(top5[i]);

        TextView cerrarDialog=dialogScores.findViewById(R.id.scores_cerrar);
        cerrarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animaEntrada();
                logoApp.startAnimation(aparecer);
                dialogScores.dismiss();
            }
        });
        dialogScores.show();

    }
    public void cargarDatosPuntajes(){
        for (int i=0; i<top5.length;i++)top5[i]="0";

        DataBase db=new DataBase(this);

        Cursor cursor=db.cargar();

        if(cursor==null)return;

        if (cursor.moveToFirst()){
            int l=0;
            do{
                top5[l]=cursor.getInt(0)+"";
                l++;
            }while (cursor.moveToNext());
        }
    }
    public void onResume(){
        super.onResume();

        /*

        MODO_PARTIDA   "TIEMPO" "INTENTOS"
        DURACION_PALABRA
        INTENTOS
         */
    }

}
