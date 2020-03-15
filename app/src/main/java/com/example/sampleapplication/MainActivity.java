package com.example.sampleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapplication.modules.AnimationActivity;
import com.example.sampleapplication.modules.GameActivity;
import com.example.sampleapplication.modules.VideoPlayerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickAnimation(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    public void ClickGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void ClickVideoPlayer(View view) {
        Intent intent = new Intent(this, VideoPlayerActivity.class);
        startActivity(intent);
    }
}
