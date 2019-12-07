package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button secondActivityButton;
    public static Button sendRequestButton;
    public static Button buttonCurrencyOne;
    public static Button buttonCurrencyTwo;
    Button lab3;


    public static List<Currency> currencies = HomeActivity.createListC();

    public static Currency eur = HomeActivity.getCurrencyByCode("EUR", currencies);
    public static int posEur = currencies.indexOf(eur);
    public static Currency currencyOne = currencies.get(posEur);

    public static Currency usd = HomeActivity.getCurrencyByCode("USD", currencies);
    public static int posUsd = currencies.indexOf(usd);
    public static Currency currencyTwo = currencies.get(posUsd);

    public static EditText valueCurrencyOne;
    public static EditText valueCurrencyTwo;


    public static TextView currencyText;


    public static ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        lab3 = (Button) findViewById(R.id.lab3);


        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        buttonCurrencyOne = (Button) findViewById(R.id.buttonCurrencyOne);
        buttonCurrencyTwo = (Button) findViewById(R.id.buttonCurrencyTwo);

        valueCurrencyOne = (EditText) findViewById(R.id.valueCurrencyOne);
        valueCurrencyOne.setText("1");
        valueCurrencyTwo = (EditText) findViewById(R.id.valueCurrencyTwo);
        Double rateUSD = Math.round(100.0 * currencies.get(posUsd).getRate()) / 100.0;
        valueCurrencyTwo.setText(rateUSD.toString());

        currencyText = (TextView) findViewById(R.id.currencyText);
        currencyText.setText("1 Euro equals " + rateUSD.toString() + " US dollar");


        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //ChangeActivity Button
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Lab3.class);

                startActivity(intent);
            }
        });

        buttonCurrencyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);
                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyOne");
                /*Drawable myDrawable = getResources().getDrawable(R.drawable.dollar);
                int h = myDrawable.getIntrinsicHeight();
                int w = myDrawable.getIntrinsicWidth();
                myDrawable.setBounds( 0 , 0, 0, 0 );
                buttonCurrencyOne.setCompoundDrawables(null, null, null, null);*/

                startActivity(intent);
            }
        });


        buttonCurrencyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);

                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyTwo");

               /* Drawable myDrawable = getResources().getDrawable(R.drawable.dollar);
                int h = myDrawable.getIntrinsicHeight();
                int w = myDrawable.getIntrinsicWidth();
                myDrawable.setBounds( 0 , 0, 0, 0 );
                buttonCurrencyTwo.setCompoundDrawables(null, null, null, null);*/
                startActivity(intent);
            }
        });

        valueCurrencyOne.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthValueCurrencyOne = valueCurrencyOne.getText().toString().trim().length();
                if (valueCurrencyOne.hasFocus() && lengthValueCurrencyOne != 0) {
                    Double conversion = currencyConversion(Double.parseDouble(s.toString()), currencyOne, currencyTwo);
                    valueCurrencyTwo.setText(conversion.toString());
                } else if (valueCurrencyOne.hasFocus() && lengthValueCurrencyOne == 0) {
                    valueCurrencyTwo.setText("");
                }
            }
        });

        valueCurrencyTwo.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthValueCurrencyTwo = valueCurrencyTwo.getText().toString().trim().length();
                if (valueCurrencyTwo.hasFocus() && lengthValueCurrencyTwo != 0) {
                    Double conversion = currencyConversion(Double.parseDouble(s.toString()), currencyTwo, currencyOne);
                    valueCurrencyOne.setText(conversion.toString());
                } else if (valueCurrencyTwo.hasFocus() && lengthValueCurrencyTwo == 0) {
                    valueCurrencyOne.setText("");
                }
            }
        });
    }


    public static Double currencyConversion(Double amount, Currency currencyOne, Currency currencyTwo) {

        Double converted;

        if (currencyOne.getCode() != null) {
            if (currencyOne.getCode().equals("EUR")) {
                converted = amount * currencyTwo.getRate();
                converted = Math.round(100.0 * converted) / 100.0;
                return converted;
            }
        } else if (currencyTwo.getCode() != null) {
            if (currencyTwo.getCode().equals("EUR")) {
                converted = amount / currencyOne.getRate();
                converted = Math.round(100.0 * converted) / 100.0;
                return converted;
            }
        }

        converted = amount / currencyOne.getRate();
        converted = converted * currencyTwo.getRate();

        converted = Math.round(100.0 * converted) / 100.0;
        return converted;
    }


    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };

    private void sendRequest() {

        progressBar.setVisibility(View.VISIBLE);

        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();


    }
}
