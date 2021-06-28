package com.lasiyyema.demirexport;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout Layout;
    private EditText kamyon;
    private EditText kalori;
    private EditText ortalama;
    private Button ekleButon;
    private Button hesaplaButon;
    private List<DataObject> dataObjectList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dataObjectList = new ArrayList<>();
        Layout = (LinearLayout) findViewById(R.id.linearLayout2);
        kalori = (EditText) findViewById(R.id.kalori);
        kalori.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ekleButon.setEnabled(kamyon.getText().length() != 0 & kalori.getText().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        kamyon = (EditText) findViewById(R.id.kamyon);
        kamyon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ekleButon.setEnabled(kamyon.getText().length() != 0 & kalori.getText().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ortalama = (EditText) findViewById(R.id.ortalama);
        ortalama.setEnabled(false);
        ekleButon = (Button) findViewById(R.id.ekle);
        hesaplaButon = (Button) findViewById(R.id.hesapla);
        ekleButon.setEnabled(false);
        ekleButon.setBackgroundColor(Color.rgb(5, 163, 47));
        hesaplaButon.setBackgroundColor(Color.rgb(255, 51, 0));
        hesaplaButon.setEnabled(false);
        ekleButon.setOnClickListener(onClick());

    }

    private View.OnClickListener onClick() {
        return v -> {
            final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            final TextView textView = new TextView(this);
            textView.setLayoutParams(lparams);
            textView.setText("New text: " + kamyon.getText().toString());
            textView.setGravity(Gravity.CENTER);
            textView.setId(View.generateViewId());
            final Button button = new Button(this);
            button.setLayoutParams(lparams);
            button.setText(R.string.delete);
            button.setBackgroundColor(Color.RED);
            button.setTextColor(Color.WHITE);
            button.setGravity(Gravity.CENTER);
            button.setId(View.generateViewId());
            button.setOnClickListener(onClick2());
            Layout.addView(textView);
            Layout.addView(button);
            createDataObject(button.getId());
/*
            Layout.removeView(findViewById(button.getId()));
*/
        };
    }

    private View.OnClickListener onClick2() {
        return v -> {
            System.out.println("deneme");
        };
    }

    public void createDataObject(int id) {
        DataObject dataObject = new DataObject();
        dataObject.setId(id);
        dataObject.setKamyon(Double.parseDouble(String.valueOf(kamyon.getText())));
        dataObject.setKalori(Double.parseDouble(String.valueOf(kalori.getText())));
        dataObjectList.add(dataObject);
        hesaplaButon.setEnabled(dataObjectList.size() != 0);
    }

}