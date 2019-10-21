package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button secondActivityButton;
    Button removeButton;
    ImageButton buttonchangeCurrencies;
    Button buttonCurrencyOne;
    Button buttonCurrencyTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        removeButton = (Button) findViewById(R.id.removeButton);
        buttonchangeCurrencies = (ImageButton) findViewById(R.id.buttonChangeCurrencies);
        buttonCurrencyOne = (Button) findViewById(R.id.buttonCurrencyOne);
        buttonCurrencyTwo = (Button) findViewById(R.id.buttonCurrencyTwo);

        //ChangeActivity Button
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeButton.setVisibility(View.INVISIBLE);
            }
        });

    }
}
