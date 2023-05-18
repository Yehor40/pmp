package com.example.uk4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;


public class AddObject extends AppCompatActivity {
    EditText nameEditText;
    EditText priceEditText;
    EditText countEditText;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);
        nameEditText = findViewById(R.id.name_editTextText);
        priceEditText = findViewById(R.id.price_editText);
        countEditText = findViewById(R.id.count_editText);
        checkBox = findViewById(R.id.done_checkBox);
    }

    public void onAddClick(View view) {
        Intent intent = new Intent();
        Item item = new Item(nameEditText.getText().toString(),
                Double.parseDouble(priceEditText.getText().toString()),
                Integer.parseInt(countEditText.getText().toString()),
                checkBox.isChecked());
        intent.putExtra("item", item);
        setResult(RESULT_OK, intent);
        finish();
    }

}