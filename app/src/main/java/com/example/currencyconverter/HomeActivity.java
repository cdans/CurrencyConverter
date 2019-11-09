package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static List<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        items.add(new ListItem("Euro", R.drawable.euro, null));
        items.add(new ListItem("US Dollar", R.drawable.dollar, null));

        getSupportActionBar().hide();

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(2500);
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
