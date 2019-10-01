package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyconverter.R;

public class SecondActivity extends AppCompatActivity {
    private ListView myList;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState)
        setContentView (R.layout,secondactivitydesign);
        myList = (ListView) findViewById(R.id.listview);
        Intent intent = getIntent();
        if (intent.getBooleanExtra("flagf", true)){
            //case1
        }
        else{
            //case2
        }
    }
}
