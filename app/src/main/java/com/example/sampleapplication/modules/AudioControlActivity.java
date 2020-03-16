package com.example.sampleapplication.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.example.sampleapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class AudioControlActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_control);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager != null ? audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) : 100;
        int curVolume = audioManager != null ? audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) : 40;

        SeekBar volumeControl = findViewById(R.id.audioControlBar);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curVolume);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
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

        final SeekBar seekAudio = findViewById(R.id.seekAudioBar);
        seekAudio.setMax(mediaPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekAudio.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 100);
        seekAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
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

    public void playAudio(View view) {
        mediaPlayer.start();
    }

    public void pauseAudio(View view) {
        mediaPlayer.pause();
    }

}
