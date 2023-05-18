package com.example.uk2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void compareOnClick(View view) {
        TextView firstTextView = findViewById(R.id.first_textView);
        TextView secondTextView = findViewById(R.id.second_textView);
        int transparentColor = 0x00000000;
        firstTextView.setBackgroundColor(transparentColor);
        secondTextView.setBackgroundColor(transparentColor);
        EditText firstPriceEdit = findViewById(R.id.firstPrice_editText);
        EditText firstWeightEdit = findViewById(R.id.firstWeight_editText);
        EditText secondPriceEdit = findViewById(R.id.secondPrice_editText);
        EditText secondWeightEdit = findViewById(R.id.secondWeight_editText);

        String firstPriceInput = firstPriceEdit.getText().toString();
        String firstWeightInput = firstWeightEdit.getText().toString();
        String secondPriceInput = secondPriceEdit.getText().toString();
        String secondWeightInput = secondWeightEdit.getText().toString();
        double firstPrice;

        try {
            firstPrice = Double.parseDouble(firstPriceInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "First price not correct", Toast.LENGTH_SHORT).show();
            return;
        }

        double firstWeight;
        try {
            firstWeight = Double.parseDouble(firstWeightInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "First weight not correct", Toast.LENGTH_SHORT).show();
            return;
        }
        double secondPrice;
        try {
            secondPrice = Double.parseDouble(secondPriceInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Second price not correct", Toast.LENGTH_SHORT).show();
            return;
        }
        double secondWeight;
        try {
            secondWeight = Double.parseDouble(secondWeightInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Second weight not correct", Toast.LENGTH_SHORT).show();
            return;
        }

        double firstPriceForKg = firstPrice / firstWeight * 1000;
        double secondPriceForKg = secondPrice / secondWeight * 1000;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (firstPriceForKg < secondPriceForKg) {
            firstTextView.setBackgroundColor(Color.GREEN);
            secondTextView.setBackgroundColor(Color.RED);
            builder.setMessage("First is cheaper, costs " + String.format("%.2f", firstPriceForKg) + " per kg, Second costs: " + String.format("%.2f", secondPriceForKg) + " per kg");

        } else if (firstPriceForKg > secondPriceForKg) {
            firstTextView.setBackgroundColor(Color.RED);
            secondTextView.setBackgroundColor(Color.GREEN);
            builder.setMessage("Second is cheaper, costs " + String.format("%.2f", secondPriceForKg) + " per kg, First costs: " + String.format("%.2f", firstPriceForKg) + " per kg");

        } else {
            firstTextView.setBackgroundColor(Color.GREEN);
            secondTextView.setBackgroundColor(Color.GREEN);
            builder.setMessage("Same price per kg: " + String.format("%.2f", firstPriceForKg));
        }

        builder.create().show();
    }

}