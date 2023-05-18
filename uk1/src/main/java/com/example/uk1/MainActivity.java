package com.example.uk1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Validation validation = new Validation();
    Extractor extractor = new Extractor();
    Calculation calculation = new Calculation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateOnClick(View view) {
        String heightInput = ((EditText) findViewById(R.id.height_editText)).getText().toString();
        String weightInput = ((EditText) findViewById(R.id.weight_editText)).getText().toString();
        TextView result = findViewById(R.id.result_textView);
        if (validation.isNotValid(weightInput)) {
            result.setText(R.string.invalidWeightMessage);
            return;
        }
        if (validation.isNotValid(heightInput)) {
            result.setText(R.string.invalidHeightMessage);
            return;
        }

        double height = extractor.extractValue(heightInput);
        double weight = extractor.extractValue(weightInput);

        double bmi = calculation.calculateBMI(weight, height);

        result.setText(String.format(Locale.getDefault(), "%.2f", bmi));
    }
}

class Validation {
    public boolean isNotValid(String weight) {
        try {
            Double.parseDouble(weight);
        } catch (NumberFormatException ex) {
            return true;
        }
        return false;
    }
}

class Extractor {
    public double extractValue(String value) {
        return Double.parseDouble(value);
    }
}

class Calculation {
    public double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }
}