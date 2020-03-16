package com.example.sampleapplication.modules;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sampleapplication.R;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        final ListView listView = findViewById(R.id.list_view);
        final ArrayList<String> list = new ArrayList<>();
        list.add("Java");
        list.add("C#");
        list.add("JavaScript");
        list.add("HTML");
        list.add("CSS");
        list.add("Android");
        list.add("Asp.Net");
        list.add("PHP");
        list.add("MySQL");
        list.add("Apache");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, list.get(position) + " selected!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
