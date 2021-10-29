package com.example.pruebasvistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnSaludar;
    TextView tvSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvSaludo = findViewById(R.id.tvSaludo);
        btnSaludar = findViewById(R.id.btnSaludar);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSaludo.setText(R.string.tv_saludo);
            }
        };

        btnSaludar.setOnClickListener(listener);
    }
}