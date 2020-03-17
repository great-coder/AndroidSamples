package com.example.sampleapplication.modules;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sampleapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class BrainTrainActivity extends AppCompatActivity {

    Button startBtn;
    ArrayList<Integer> answers = new ArrayList<>();
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainBtn;
    ConstraintLayout game_board_layout;
    Random random;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    boolean isGameActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_train);

        random = new Random();
        startBtn = findViewById(R.id.startBtn);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        game_board_layout = findViewById(R.id.game_board_layout);
    }

    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        game_board_layout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(playAgainBtn);
    }

    public void playAgain(View view) {
        playAgainBtn.setVisibility(View.INVISIBLE);
        isGameActive = true;
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        generateQuestion();
        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(View.VISIBLE);
                isGameActive = false;
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + score + "/" + numberOfQuestions);
            }
        }.start();
    }

    private void generateQuestion() {
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(a + " + " + b);
        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a + b) {
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if (isGameActive) {
            if (view.getTag().toString().equals((Integer.toString(locationOfCorrectAnswer)))) {
                score++;
                resultTextView.setText("Correct!");
            } else {
                resultTextView.setText("Wrong!");
            }
            numberOfQuestions++;
            pointsTextView.setText(score + "/" + numberOfQuestions);
            generateQuestion();
        }
    }
}
