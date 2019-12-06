package com.example.currencyconverter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddCurrencyActivity extends AppCompatActivity {

    private Button submitButton;
    public static Button setCompareCurrencyButton;

    private EditText name;
    private EditText comparison;

    public static TextView textCompareCurrency;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        setContentView(R.layout.activity_add_currency);

        submitButton = (Button) findViewById(R.id.submitButton);
        setCompareCurrencyButton = (Button) findViewById(R.id.setCompareCurrencyButton);

        name = (EditText) findViewById(R.id.editName);
        comparison = (EditText) findViewById(R.id.editComparison);

        textCompareCurrency = (TextView) findViewById(R.id.textCompareCurrency);

        setCompareCurrencyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCurrencyActivity.this, SetCompareCurrency.class);

                startActivity(intent);

            }
        });

        final List<Currency> currencies = HomeActivity.createListC();

        submitButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {

                String strname = name.getText().toString();
                String strUnit = comparison.getText().toString();


                if (strname.isEmpty() || strUnit.isEmpty() || setCompareCurrencyButton.getText()=="Set compare currency"){

                    Toast.makeText(AddCurrencyActivity.this, "Please edit everything before submitting.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Double rate = Double.parseDouble(strUnit);

                    if (setCompareCurrencyButton.getText().toString().equals("Euro")){
                        rate = 1/rate;
                        rate =  Math.round(10000.0 * rate) / 10000.0;
                    }
                    else {
                        Currency c1 = SetCompareCurrency.currency;
                        Currency c2 = HomeActivity.getCurrencyByCode("EUR", currencies);
                        rate = MainActivity.currencyConversion(rate, c1, c2);

                        rate = 1/rate;

                        rate =  Math.round(10000.0 * rate) / 10000.0;
                    }

                    HomeActivity.mDb.currencyDao().insert(new Currency(null, strname, rate, R.drawable.comingsoon,  "One unit of " + strname + " are " + strUnit + " " + SetCompareCurrency.currency.getTitle() + " " + rate));

                    Intent intent = new Intent(AddCurrencyActivity.this, SecondActivity.class);

                    startActivity(intent);
                }

            }
        });


    }
}