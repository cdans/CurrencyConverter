package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListAgain extends AppCompatActivity {
    private ListView myListView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_again);

        myListView = (ListView) findViewById(R.id.transferList);

        Intent intent = getIntent();

        ArrayList items = intent.getIntegerArrayListExtra("EXTRA_INTENT");

        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);


    }
}