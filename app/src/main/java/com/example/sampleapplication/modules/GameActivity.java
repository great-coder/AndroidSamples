package com.example.sampleapplication.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sampleapplication.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    int activePlayer = 1;
    boolean gameIsActive = true;
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winPosistions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    /**
     * Gets current ImageView has called this method and will do some stuff!!!
     */
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameIsActive && gameState[tappedCounter] == 0) {
            counter.setTranslationY(-1000f);
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.player1);
                activePlayer = 2;
            } else {
                activePlayer = 1;
                counter.setImageResource(R.drawable.player2);
            }
            counter.animate().translationY(0f).setDuration(500);

            for (int[] winPosition : winPosistions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 0) {
                    gameIsActive = false;
                    String winner = "Blue";
                    if (gameState[winPosition[0]] == 2) {
                        winner = "Red";
                    }
                    EndGame(winner + " has won!");
                } else {
                    boolean gameIsOver = true;
                    for (int counterState : gameState) {
                        if (counterState == 0) {
                            gameIsOver = false;
                            break;
                        }
                    }
                    if (gameIsOver) {
                        EndGame("It's a draw!");
                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 1;
        for (int i = 0; i < gameState.length; i++)
            gameState[i] = 0;
        androidx.gridlayout.widget.GridLayout boardLayout = findViewById(R.id.boardLayout);
        for (int i = 0; i < boardLayout.getChildCount(); i++) {
            ((ImageView) boardLayout.getChildAt(i)).setImageResource(0);
        }
    }

    private void EndGame(String message) {
        TextView winnerMessage = findViewById(R.id.winnerMessage);
        winnerMessage.setText(message);
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.VISIBLE);
    }
}
