package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button secondActivityButton;
    Button removeButton;
    public static List<ListItem> items = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);


        items.add(new ListItem("Euro", R.drawable.euro, "The euro is the official currency of 19 of the 28 member states of the European Union. This group of states is known as the eurozone or euro area, and counts about 343 million citizens as of 2019. The euro, which is divided into 100 cents, is the second-largest and second-most traded currency in the foreign exchange market after the United States dollar."));
        items.add(new ListItem("United States dollar", R.drawable.dollar, "The United States dollar is the official currency of the United States and its territories per the United States Constitution since 1792. In practice, the dollar is divided into 100 smaller cent (¢) units, but is occasionally divided into 1000 mills (₥) for accounting. The circulating paper money consists of Federal Reserve Notes that are denominated in United States dollars."));

        //ChangeActivity Button
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        removeButton = (Button) findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeButton.setVisibility(View.INVISIBLE);
            }
        });
    }
}
