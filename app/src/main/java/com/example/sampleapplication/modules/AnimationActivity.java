package com.example.sampleapplication.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sampleapplication.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ImageView homer = findViewById(R.id.homer);
        homer.setTranslationY(-1000f);
    }

    public void fade(View view) {
        ImageView bart = findViewById(R.id.bart);
        ImageView homer = findViewById(R.id.homer);
        bart.animate().alpha(0f).translationYBy(2000f).setDuration(2000);
        homer.animate().alpha(1f).translationYBy(1000f).setDuration(3000);
    }
}
