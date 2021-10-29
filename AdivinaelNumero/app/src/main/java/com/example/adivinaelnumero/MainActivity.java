package com.example.adivinaelnumero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    public static int i = 5;
    Random rd = new Random();
    int numSecreto = rd.nextInt((100 - 1) + 1) + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textIntento = findViewById(R.id.textIntento);
        tvIntentos = findViewById(R.id.tvIntentos);
        tvPista = findViewById(R.id.tvPista);
        rlFondo = findViewById(R.id.rlFondo);
        btn_comparacion = findViewById(R.id.btn_comparacion);

    }

    public void adivinarNumero(View view) {

        boolean resuelto = false;
        boolean valido = true;

        if (textIntento.getText().toString().isEmpty()) {
            tvPista.setText("Debe introducir un número");
            valido = false;
        } else if (Integer.parseInt(String.valueOf(textIntento.getText())) > 100 || Integer.parseInt(String.valueOf(textIntento.getText())) < 1) {
            tvPista.setText("Debe introducir un número entre 1 y 100");
            valido = false;
        }
        if (valido){
            int num = Integer.parseInt(String.valueOf(textIntento.getText()));

            if (num == numSecreto) {
                rlFondo.setBackgroundColor(getResources().getColor(R.color.verde));
                tvPista.setText("Has acertado el número");
                resuelto = true;
            } else if (num > numSecreto) {
                tvPista.setText("El número secreto es menor al introducido");
                i --;
            } else if (num < numSecreto) {
                tvPista.setText("El número secreto es mayor que el introducido");
                i --;
            }
            tvIntentos.setText("Intentos restantes: " + i);
            if (!resuelto && i == 0) {
                tvPista.setText("El número era " + numSecreto);
                btn_comparacion.setEnabled(false);
                rlFondo.setBackgroundColor(getResources().getColor(R.color.rojo));

            }
            if (resuelto) {
                btn_comparacion.setEnabled(false);
            }
        }



    }


}