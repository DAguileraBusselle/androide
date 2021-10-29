package com.example.adivinaelnumero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvPista;
    TextView tvIntentos;
    EditText textIntento;
    RelativeLayout rlFondo;
    Button btn_comparacion;
    Button btn_reset;
    //Num de intentos totales
    public static int i = 5;
    //Num aleatorio entre 1 y 100, se crea en onCreate
    public static Random rd = new Random();
    public static int numSecreto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para poder usarlos en el activity_main
        textIntento = findViewById(R.id.textIntento);
        tvIntentos = findViewById(R.id.tvIntentos);
        tvPista = findViewById(R.id.tvPista);
        rlFondo = findViewById(R.id.rlFondo);
        btn_comparacion = findViewById(R.id.btn_comparacion);
        btn_reset = findViewById(R.id.btn_reset);
        numSecreto = rd.nextInt((100 - 1) + 1) + 1;

    }

    public void adivinarNumero(View view) {

        boolean resuelto = false;
        boolean valido = true;

        //Si no hay nada escrito da un aviso
        if (textIntento.getText().toString().isEmpty()) {
            tvPista.setText("Debe introducir un número");
            manageBlinkEffect();

            valido = false;

        //Si escribes un num fuera de [1-100] da un aviso y parpadea la pantalla en naranja
        } else if (Integer.parseInt(String.valueOf(textIntento.getText())) > 100 || Integer.parseInt(String.valueOf(textIntento.getText())) < 1) {
            tvPista.setText("Debe introducir un número entre 1 y 100");
            manageBlinkEffect();

            valido = false;
        }
        //Si el num introducido es válido
        if (valido){
            int num = Integer.parseInt(String.valueOf(textIntento.getText()));

            //Si se acierta el numSecreto el fondo se vuelve verde
            if (num == numSecreto) {
                rlFondo.setBackgroundColor(getResources().getColor(R.color.verde));
                tvPista.setText("Has acertado el número");
                resuelto = true;

                //Si el num introducido es mayor al numSecreto, quita un intento
            } else if (num > numSecreto) {
                tvPista.setText("El número secreto es menor al introducido");
                i --;

                //Si el num introducido es menor al numSecreto, quita un intento
            } else if (num < numSecreto) {
                tvPista.setText("El número secreto es mayor que el introducido");
                i --;
            }
            tvIntentos.setText("Intentos restantes: " + i);

            //Si se terminan los intentos muestra el numSecreto, desactiva el btn
            //y el fondo se pone rojo
            if (!resuelto && i == 0) {
                tvPista.setText("El número era " + numSecreto);
                btn_comparacion.setEnabled(false);
                rlFondo.setBackgroundColor(getResources().getColor(R.color.rojo));
            }
            //Si se adivina el numSecreto el btn se desactiva
            if (resuelto) {
                btn_comparacion.setEnabled(false);
            }
        }




    }
    //Método para que cuando se le da al btnReset reinicie la pantalla
    public void reiniciar(View view) {
        i = 5;
        tvIntentos.setText("Intentos restantes: " + i);
        tvPista.setText("Debe introducir un número entre 1 y 100");
        textIntento.setText("");
        rlFondo.setBackgroundColor(getResources().getColor(R.color.color_fondo));
        numSecreto = rd.nextInt((100 - 1) + 1) + 1;
        btn_comparacion.setEnabled(true);
    }

    //Método para la animación de aviso, parpadea la pantalla 3 veces en naranja
    @SuppressLint("WrongConstant")
    private void manageBlinkEffect() {
        ObjectAnimator anim = ObjectAnimator.ofInt(rlFondo, "backgroundColor", getResources().getColor(R.color.color_fondo), getResources().getColor(R.color.naranja),
                getResources().getColor(R.color.color_fondo));
        anim.setDuration(250);
        anim.setEvaluator(new ArgbEvaluator());

        anim.setRepeatCount(3);
        anim.start();

    }

}