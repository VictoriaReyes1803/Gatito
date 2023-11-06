package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.List;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.Color;
import android.os.Handler;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationList = new ArrayList<>();
    private final int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 0;
    private LinearLayout playeronelayout, playertwolayout;
    private TextView playerone, playertwo;
    private int player1Score;
    private int player2Score;

    private View vista1, vista2, vista3, vista4, vista5, vista6, vista7, vista8, vista9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerone = findViewById(R.id.playerone);
        playertwo = findViewById(R.id.playertwo);
        playeronelayout = findViewById(R.id.playeronelayout);
        playertwolayout = findViewById(R.id.playertwolayout);
        vista1 = findViewById(R.id.vista1);
        vista2 = findViewById(R.id.vista2);
        vista3 = findViewById(R.id.vista3);
        vista4 = findViewById(R.id.vista4);
        vista5 = findViewById(R.id.vista5);
        vista6 = findViewById(R.id.vista6);
        vista7 = findViewById(R.id.vista7);
        vista8 = findViewById(R.id.vista8);
        vista9 = findViewById(R.id.vista9);

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        vista1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(0);
            }
        });

        vista2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(1);
            }
        });

        vista3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(2);
            }
        });

        vista4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(3);
            }
        });

        vista5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(4);
            }
        });

        vista6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(5);
            }
        });

        vista7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(6);
            }
        });

        vista8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(7);
            }
        });

        vista9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBoxClick(8);
            }
        });

        playeronelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerTurn = 1;
                updatePlayerTurnUI();
            }
        });

        playertwolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerTurn = 2;
                updatePlayerTurnUI();
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            player1Score = extras.getInt("player1Score", 0);
            player2Score = extras.getInt("player2Score", 0);
        }

        playerone.setText("Jugador 1: " + player1Score);
        playertwo.setText("Jugador 2: " + player2Score);
    }

    private void handleBoxClick(int boxIndex) {
        if (isBoxSelectable(boxIndex)) {

            View clickedView = null;
            switch (boxIndex) {
                case 0:
                    clickedView = vista1;
                    break;
                case 1:
                    clickedView = vista2;
                    break;
                case 2:
                    clickedView = vista3;
                    break;
                case 3:
                    clickedView = vista4;
                    break;
                case 4:
                    clickedView = vista5;
                    break;
                case 5:
                    clickedView = vista6;
                    break;
                case 6:
                    clickedView = vista7;
                    break;
                case 7:
                    clickedView = vista8;
                    break;
                case 8:
                    clickedView = vista9;
                    break;
            }

            if (clickedView != null) {
                if (playerTurn == 1) {
                    clickedView.setBackgroundResource(R.drawable.equis);
                    boxPositions[boxIndex] = 1; // Marcar la casilla como ocupada por el jugador 1
                } else {
                    clickedView.setBackgroundResource(R.drawable.img_5); // Cambiar el fondo de la vista a bolita


                    boxPositions[boxIndex] = 2; // Marcar la casilla como ocupada por el jugador 2
                }

                clickedView.setClickable(false); // Deshabilitar clics en esta vista

                // Verificar si el jugador actual ha ganado
                if (checkForWin(playerTurn)) {
                       if (playerTurn == 1) {
                            playerTurn++;
                        } else {
                            playerTurn--;
                        }
                    showWinMessage1(playerTurn);
                } else {
                    totalSelectedBoxes++;
                    if (totalSelectedBoxes == 9) {
                        showDrawMessage();
                    } else {
                        // Cambiar el turno del jugador
                        playerTurn = 3 - playerTurn; // Alternar entre 1 y 2
                        updatePlayerTurnUI();
                    }
                }
            }
        }
    }


    private boolean isBoxSelectable(int boxIndex) {
        return boxPositions[boxIndex] == 0;
    }

    private boolean checkForWin(int player) {
        for (int[] combination : combinationList) {
            int a = combination[0];
            int b = combination[1];
            int c = combination[2];
            if (boxPositions[a] == player && boxPositions[b] == player && boxPositions[c] == player) {
                return true;
                }
        }
        return false;
    }

    private void showWinMessage1(int player) {
        String message = "Jugador " + player + " gana!";
        player1Score += (player == 1) ? 1 : 0;
        player2Score += (player == 2) ? 1 : 0;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        TextView textView = findViewById(R.id.textView2);
        textView.setText("Felicidades, Jugador " + player + " ganó!");
        textView.setVisibility(View.VISIBLE);

        disableAllBoxes();
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        intent.putExtra("player1Score", player1Score);
        intent.putExtra("player2Score", player2Score);
        startActivity(intent);

        finish();

    }


    private void showDrawMessage() {
        Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
        TextView textView = findViewById(R.id.textView2);
        textView.setText("¡Empate!");
        textView.setVisibility(View.VISIBLE);
        disableAllBoxes();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    private void disableAllBoxes() {
        vista1.setClickable(false);
        vista2.setClickable(false);
        vista3.setClickable(false);
        vista4.setClickable(false);
        vista5.setClickable(false);
        vista6.setClickable(false);
        vista7.setClickable(false);
        vista8.setClickable(false);
        vista9.setClickable(false);
    }




    private void updatePlayerTurnUI() {
        if (playerTurn == 1) {
            playerone.setTextColor(Color.RED);
            playertwo.setTextColor(Color.BLACK);
        } else {
            playerone.setTextColor(Color.BLACK);
            playertwo.setTextColor(Color.RED);
        }
    }


}
