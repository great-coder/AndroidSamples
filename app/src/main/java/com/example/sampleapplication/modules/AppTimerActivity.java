package com.example.sampleapplication.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleapplication.R;

public class AppTimerActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerBtn;
    CountDownTimer countDownTimer;
    boolean isCounterActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_timer);
        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        controllerBtn = findViewById(R.id.controllerBtn);

        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;
        String secondString = Integer.toString(seconds);
        String minuteString = Integer.toString(minutes);
        if (seconds <= 9) {
            secondString = "0" + secondString;
        }
        if (minutes <= 9) {
            minuteString = "0" + minuteString;
        }
        timerTextView.setText(minuteString + ":" + secondString);
    }

    public void resetTimer() {
        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        isCounterActive = false;
        timerSeekBar.setEnabled(true);
        controllerBtn.setText("Go!");
        countDownTimer.cancel();
    }

    public void controlTimer(View view) {
        if (!isCounterActive) {
            isCounterActive = true;
            timerSeekBar.setEnabled(false);
            controllerBtn.setText("Stop");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    /* TODO: you can play a sound here to notify user that timer is done
                    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound);
                    mediaPlayer.start();
                     */
                    Toast.makeText(getApplicationContext(), "Timer has finished!", Toast.LENGTH_SHORT).show();
                }
            }.start();
        } else {
            resetTimer();
        }
    }
}
