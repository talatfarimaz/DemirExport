package com.lasiyyema.demirexport;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
    private EditText truckAmount;
    private EditText calorieValue;
    private EditText tonnage;
    private EditText averageCalorie;
    private Button addButton;
    private Button calculateButton;
    private List<DataObject> dataObjectList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dataObjectList = new ArrayList<>();
        Layout = findViewById(R.id.dataListLayout);
        addButtonDefinition();
        calculateButtonDefinition();
        setInputDefinitions();
    }

    public void setInputDefinitions() {
        averageCalorie = findViewById(R.id.average);
        averageCalorie.setEnabled(false);
        calorieValue = findViewById(R.id.calorie);
        calorieValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addButton.setEnabled(truckAmount.getText().length() != 0 & calorieValue.getText().length() != 0 & tonnage.getText().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        truckAmount = findViewById(R.id.truck);
        truckAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addButton.setEnabled(truckAmount.getText().length() != 0 & calorieValue.getText().length() != 0 & tonnage.getText().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tonnage = findViewById(R.id.tonnage);
        tonnage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addButton.setEnabled(truckAmount.getText().length() != 0 & calorieValue.getText().length() != 0 & tonnage.getText().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void addButtonDefinition() {
        addButton = findViewById(R.id.add);
        addButton.setEnabled(false);
        addButton.setBackgroundColor(Color.rgb(5, 163, 47));
        addButton.setOnClickListener(onClickAdd());
    }

    public void calculateButtonDefinition() {
        calculateButton = findViewById(R.id.calculate);
        calculateButton.setBackgroundColor(Color.rgb(255, 51, 0));
        calculateButton.setEnabled(false);
        calculateButton.setOnClickListener(calculate());
    }

    private View.OnClickListener onClickAdd() {
        return v -> {
            final LinearLayout componentsLayout = new LinearLayout(this);
            componentsLayout.setOrientation(LinearLayout.HORIZONTAL);
            final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            lparams.setMargins(0, 5, 0, 5);
            final LinearLayout.LayoutParams lparams2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            lparams2.setMargins(0, 5, 0, 5);
            GradientDrawable border = new GradientDrawable();
            border.setCornerRadius(5);
            final TextView textView = new TextView(this);
            textView.setLayoutParams(lparams);
            textView.setText(String.format("%s %s x %s %s x %s %s", calorieValue.getText().toString(), getString((R.string.kcal)), truckAmount.getText().toString(), getString(R.string.truckAmount), tonnage.getText().toString(), getString((R.string.ton))));
            textView.setGravity(Gravity.CENTER);
            textView.setId(View.generateViewId());
            textView.setTypeface(null, Typeface.BOLD);
            final Button button = new Button(this);
            button.setLayoutParams(lparams2);
            button.setText(R.string.delete);
            button.setBackgroundColor(Color.rgb(255, 51, 0));
            button.setTextColor(Color.WHITE);
            button.setGravity(Gravity.CENTER);
            button.setId(View.generateViewId());
            button.getLayoutParams().height = 100;
            button.getLayoutParams().width = 100;
            componentsLayout.addView(textView);
            componentsLayout.addView(button);
            componentsLayout.setId(View.generateViewId());
            button.setOnClickListener(removeValues(button.getId(), textView.getId(), componentsLayout.getId()));
            Layout.addView(componentsLayout);
            createDataObject(button.getId());
            truckAmount.setText(null);
            calorieValue.setText(null);
            tonnage.setText(null);
            addButton.setEnabled(false);
        };
    }

    private View.OnClickListener removeValues(int buttonId, int textViewId, int layoutId) {
        return v -> {
            Layout.removeView(findViewById(buttonId));
            Layout.removeView(findViewById(textViewId));
            Layout.removeView(findViewById(layoutId));
            for (DataObject dataObject : dataObjectList) {
                if (dataObject.getId() == buttonId) {
                    dataObjectList.remove(dataObject);
                    reCalculate();
                    break;
                }
            }
        };
    }

    public View.OnClickListener calculate() {
        return v -> {
            double truck = 0;
            double ton = 0;
            for (DataObject dataObject : dataObjectList) {
                ton += dataObject.getCalorie() * dataObject.getTruck() * dataObject.getTonnage();
                truck += dataObject.getTruck() * dataObject.getTonnage();
            }
            double averageCalorieResult = ton / truck;
            averageCalorie.setText(String.format("%s %s", averageCalorieResult, getString(R.string.kcal)));
        };

    }

    public void reCalculate() {
        double truck = 0;
        double ton = 0;
        for (DataObject dataObject : dataObjectList) {
            ton += dataObject.getCalorie() * dataObject.getTruck();
            truck += dataObject.getTruck();
        }
        double averageCalorieResult = ton / truck;
        if (dataObjectList.size() == 0) {
            averageCalorie.setText(null);
            calculateButton.setEnabled(false);
        } else {
            averageCalorie.setText(String.format("%s %s", averageCalorieResult, getString(R.string.kcal)));
            calculateButton.setEnabled(true);
        }
    }

    public void createDataObject(int id) {
        DataObject dataObject = new DataObject();
        dataObject.setId(id);
        dataObject.setTruck(Double.parseDouble(String.valueOf(truckAmount.getText())));
        dataObject.setCalorie(Double.parseDouble(String.valueOf(calorieValue.getText())));
        dataObject.setTonnage(Double.parseDouble(String.valueOf(tonnage.getText())));
        dataObjectList.add(dataObject);
        calculateButton.setEnabled(dataObjectList.size() != 0);
    }

}