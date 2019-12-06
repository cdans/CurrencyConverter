package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ChooseCurrency extends AppCompatActivity {

    public static ListView myListView;
    public static ListAdapter adapter;
    private SearchView searchView;
    public List<Currency> currencies = HomeActivity.currencies;

    public static final String EXTRA_BUTTON = "EXTRA_BUTTON";


    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_choose_currency);

        Intent receivedIntent = getIntent();
        final String button = receivedIntent.getStringExtra(EXTRA_BUTTON);

        myListView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchViewCurrencies);


        adapter = new ListAdapter(this, currencies);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = currencies.get(position).getImageId();
                if (button.equals("buttonCurrencyOne")) {
                    MainActivity.currencyOne = currencies.get(position);
                    MainActivity.buttonCurrencyOne.setText(currencies.get(position).getTitle());
                }
                else{
                    MainActivity.currencyTwo = currencies.get(position);
                    MainActivity.buttonCurrencyTwo.setText(currencies.get(position).getTitle());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.valueCurrencyOne.setText("1");

                        Double converted = MainActivity.currencyConversion(1., MainActivity.currencyOne, MainActivity.currencyTwo);
                        MainActivity.valueCurrencyTwo.setText(converted.toString());

                        MainActivity.currencyText.setText("1 "+  MainActivity.currencyOne.getTitle() + " equals " +
                                converted.toString() + " " + MainActivity.currencyTwo.getTitle());
                    }
                });

                //MainActivity.buttonCurrencyOne.drawable
                finish();
                //startActivity(intent);
            }
        });


            }
        }