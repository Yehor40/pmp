package com.example.uk4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<Item> items = new ArrayList<>();
    private RecyclerView recyclerView;

    private final ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    if (result.getData().hasExtra("item")) {
                        Item item = (Item) result.getData().getExtras().getSerializable("item");
                        items.add(item);
                        recyclerView.getAdapter().notifyItemInserted(items.size() - 1);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        ItemAdapter adapter = new ItemAdapter(items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddObject.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void onClickLoad(View view) {
        new Thread(() -> {
            try {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                FileInputStream fis = new FileInputStream(path.getAbsoluteFile() + "/file.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                items.clear();
                items.addAll((List<Item>) ois.readObject());
                this.runOnUiThread(() -> {
                    recyclerView.getAdapter().notifyDataSetChanged();
                    Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show();
                });
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void onClickSave(View view) {
        new Thread(()-> {
            try {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                FileOutputStream fos = new FileOutputStream(path.getAbsoluteFile() + "/file.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(items);
                oos.close();
                this.runOnUiThread(() -> {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}