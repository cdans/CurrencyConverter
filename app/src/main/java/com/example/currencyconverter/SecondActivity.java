package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.R;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView myList;
    private ListAdapter adapter;

    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView (R.layout.secondactivitydesign);
        myList = (ListView) findViewById(R.id.listview);

        List<ListItem> items = new ArrayList<>();

        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)){
            items.add(new ListItem("Jack", R.drawable.ic_3d_rotation_black_48dp, "Math and Chem"));
        }
        else{
            items.add(new ListItem("Mathematics", R.drawable.ic_3d_rotation_black_48dp,"Math is crazy"));
        }

        adapter = new ListAdapter(this, items);
        myList.setAdapter(adapter);
    }
}
