package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    TextView Contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Contador = findViewById(R.id.Contador);

        long duration = 5 * 1000;

        // Set the interval to 1 second
        long interval = 1000;

        new CountDownTimer(duration, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                String sDuration = String.format(Locale.ENGLISH, "%02d : %02d",
                        secondsRemaining / 60, secondsRemaining % 60);
                Contador.setText(sDuration);
            }

            @Override
            public void onFinish() {
                Contador.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),
                        "Countdown timer has ended", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();

    }


}