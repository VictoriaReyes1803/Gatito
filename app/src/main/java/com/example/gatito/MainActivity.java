package com.example.gatito;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.Color;

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
    private View vista1, vista2, vista3, vista4, vista5, vista6, vista7, vista8, vista9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas y asignarlas a las variables miembro
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

        // Agregar combinaciones ganadoras
        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        // Establecer clic listeners para las vistas y manejar la lógica del juego en estos listeners
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
    }

    private void handleBoxClick(int boxIndex) {
        if (isBoxSelectable(boxIndex)) {
            boxPositions[boxIndex] = playerTurn;
            totalSelectedBoxes++;

            // Verificar si alguien ha ganado
            if (checkForWin(playerTurn)) {
                showWinMessage(playerTurn);
                return;
            } else if (totalSelectedBoxes == 9) {
                showDrawMessage();
                return;
            }

            // Cambiar el turno del jugador
            playerTurn = (playerTurn == 1) ? 2 : 1;
            updatePlayerTurnUI();
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

    private void showWinMessage(int player) {
        String message = "Jugador " + player + " gana!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showDrawMessage() {
        Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
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
