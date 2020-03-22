package com.example.sampleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapplication.modules.AnimationActivity;
import com.example.sampleapplication.modules.AppTimerActivity;
import com.example.sampleapplication.modules.AudioControlActivity;
import com.example.sampleapplication.modules.BrainTrainActivity;
import com.example.sampleapplication.modules.GameActivity;
import com.example.sampleapplication.modules.ListViewActivity;
import com.example.sampleapplication.modules.LocationActivity;
import com.example.sampleapplication.modules.MapsActivity;
import com.example.sampleapplication.modules.VideoPlayerActivity;
import com.example.sampleapplication.modules.WeatherAppActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void LoadModule(View view) {
        String TAG = view.getTag().toString();
        Intent intent = null;
        switch (TAG) {
            case "MODULE_1":
                intent = new Intent(this, AnimationActivity.class);
                break;
            case "MODULE_2":
                intent = new Intent(this, GameActivity.class);
                break;
            case "MODULE_3":
                intent = new Intent(this, VideoPlayerActivity.class);
                break;
            case "MODULE_4":
                intent = new Intent(this, AudioControlActivity.class);
                break;
            case "MODULE_5":
                intent = new Intent(this, ListViewActivity.class);
                break;
            case "MODULE_6":
                intent = new Intent(this, AppTimerActivity.class);
                break;
            case "MODULE_7":
                intent = new Intent(this, BrainTrainActivity.class);
                break;
            case "MODULE_8":
                intent = new Intent(this, WeatherAppActivity.class);
                break;
            case "MODULE_9":
                intent = new Intent(this, MapsActivity.class);
                break;
            case "MODULE_10":
                intent = new Intent(this, LocationActivity.class);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Unsupported command!", Toast.LENGTH_SHORT).show();
                break;
        }
        startActivity(intent);
    }
}
