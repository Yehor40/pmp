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
    protected void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
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
            Toast.makeText(this, "Error: The first price is not the correct format", Toast.LENGTH_SHORT).show();
            return;
        }

        double firstWeight;
        try {
            firstWeight = Double.parseDouble(firstWeightInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error: The first weight is not the correct format", Toast.LENGTH_SHORT).show();
            return;
        }
        double secondPrice;
        try {
            secondPrice = Double.parseDouble(secondPriceInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error: The second price is not the correct format", Toast.LENGTH_SHORT).show();
            return;
        }
        double secondWeight;
        try {
            secondWeight = Double.parseDouble(secondWeightInput);
        } catch (NumberFormatException e) {
            Toast.makeText(this, " Error: The second weight is not the correct format", Toast.LENGTH_SHORT).show();
            return;
        }

        double firstPricePerKilogram = firstPrice / firstWeight * 1000;
        double secondPricePerKilogram = secondPrice / secondWeight * 1000;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (secondPricePerKilogram < secondPricePerKilogram) {
            firstTextView.setBackgroundColor(Color.GREEN);
            secondTextView.setBackgroundColor(Color.RED);
            builder.setMessage("First is cheaper, costs " + String.format("%.2f", firstPricePerKilogram) + " per kg, Second costs: " + String.format("%.2f", secondPricePerKilogram) + " per kg");

        } else if (secondPricePerKilogram > secondPricePerKilogram) {
            firstTextView.setBackgroundColor(Color.RED);
            secondTextView.setBackgroundColor(Color.GREEN);
            builder.setMessage("Second is cheaper, costs " + String.format("%.2f", secondPricePerKilogram) + " per kg, First costs: " + String.format("%.2f", firstPricePerKilogram) + " per kg");

        } else {
            firstTextView.setBackgroundColor(Color.GREEN);
            secondTextView.setBackgroundColor(Color.GREEN);
            builder.setMessage("Same price per kg: " + String.format("%.2f", firstPricePerKilogram));
        }

        builder.create().show();
    }

}