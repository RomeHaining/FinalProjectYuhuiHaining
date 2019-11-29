package com.example.finalprojectyuhuihaining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // link "add event" button
        Button addEvent = findViewById(R.id.addEvent);
        addEvent.setOnClickListener(unused -> startActivity(new Intent(this, addEventActivity.class)));
    }
}
