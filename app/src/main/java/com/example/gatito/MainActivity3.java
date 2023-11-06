package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.List;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.Color;


import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {
    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private int player1Score;
    private int player2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        player1ScoreTextView = findViewById(R.id.puntaje1);
        player2ScoreTextView = findViewById(R.id.puntaje2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            player1Score = extras.getInt("player1Score", 0);
            player2Score = extras.getInt("player2Score", 0);

            player1ScoreTextView.setText(String.valueOf(player1Score));
            player2ScoreTextView.setText(String.valueOf(player2Score));
        }

        Button continuarJugandoButton = findViewById(R.id.continuarJugandoButton);
        Button reiniciarButton = findViewById(R.id.reiniciarButton);

        continuarJugandoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);

                // Actualiza las variables de la clase en lugar de crear variables locales
                intent.putExtra("player1Score", player1Score);
                intent.putExtra("player2Score", player2Score);

                // Inicia la actividad y cierra MainActivity3
                startActivity(intent);
                finish();
            }
        });

        reiniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1Score = 0;
                player2Score = 0;

                // Crear un intent para volver a MainActivity con los puntajes en 0
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                intent.putExtra("player1Score", player1Score);
                intent.putExtra("player2Score", player2Score);

                // Iniciar la actividad y cerrar MainActivity3
                startActivity(intent);
                finish();
            }
        });
    }
}
