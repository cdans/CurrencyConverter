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

        items.add(new ListItem("Euro", R.drawable.euro, "The euro is the official currency of 19 of the 28 member states of the European Union. This group of states is known as the eurozone or euro area, and counts about 343 million citizens as of 2019. The euro, which is divided into 100 cents, is the second-largest and second-most traded currency in the foreign exchange market after the United States dollar."));
        items.add(new ListItem("US Dollar", R.drawable.dollar, "The United States dollar is the official currency of the United States and its territories per the United States Constitution since 1792. In practice, the dollar is divided into 100 smaller cent (¢) units, but is occasionally divided into 1000 mills (₥) for accounting. The circulating paper money consists of Federal Reserve Notes that are denominated in United States dollars."));

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
