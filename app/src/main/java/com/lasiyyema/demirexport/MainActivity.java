package com.lasiyyema.demirexport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText kamyon;
    private EditText kalori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kamyon = findViewById(R.id.kamyon);
        kalori = findViewById(R.id.kalori);
    }

}