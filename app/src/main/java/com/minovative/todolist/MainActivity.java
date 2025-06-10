package com.minovative.todolist;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;

    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        try {
            itemList = FileHelper.readData(this);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        adapter = new CustomAdapter(this, itemList);
        listView.setAdapter(adapter);

        add.setOnClickListener(view -> {
            String itemName = item.getText().toString();

            itemList.add(itemName);
            item.setText("");

            try {
                FileHelper.writeData(itemList, getApplicationContext());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            adapter.notifyDataSetChanged();
        });
    }
}