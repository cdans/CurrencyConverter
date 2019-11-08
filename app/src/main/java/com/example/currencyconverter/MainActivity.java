package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button secondActivityButton;
    Button sendRequestButton;
    public static Button buttonCurrencyOne;
    public static Button buttonCurrencyTwo;

    TextView title;
    TextView bodyText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        buttonCurrencyOne = (Button) findViewById(R.id.buttonCurrencyOne);
        buttonCurrencyTwo = (Button) findViewById(R.id.buttonCurrencyTwo);

        title = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);

        //ChangeActivity Button
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        buttonCurrencyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);
                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyOne");

                startActivity(intent);
            }
        });

        buttonCurrencyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);

                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyTwo");

                startActivity(intent);
            }
        });
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };

    private void sendRequest(){

    }

}
