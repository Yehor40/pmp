package com.example.uk3_cv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddObject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_object);
    }

    public void onAddClick(View view) {
        Intent intent = new Intent();
        EditText nameEditText = findViewById(R.id.name_editTextText);
        EditText priceEditText = findViewById(R.id.price_editText);
        EditText countEditText = findViewById(R.id.count_editText);
        CheckBox checkBox = findViewById(R.id.done_checkBox);

        Item item = new Item(nameEditText.getText().toString(),
                Double.parseDouble(priceEditText.getText().toString()),
                Integer.parseInt(countEditText.getText().toString()),
                checkBox.isChecked());
        intent.putExtra("item", item);
        setResult(RESULT_OK, intent);
        finish();
    }

}