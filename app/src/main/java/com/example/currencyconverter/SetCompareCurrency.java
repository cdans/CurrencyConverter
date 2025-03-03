package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SetCompareCurrency extends AppCompatActivity {

    public static ListView myListView;
    public static ListAdapter adapter;
    private SearchView searchView;
    List<Currency> currencies = HomeActivity.createListC();
    public static Currency currency;

    public static final String EXTRA_BUTTON = "EXTRA_BUTTON";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_compare_currency);

        Intent receivedIntent = getIntent();
        final String button = receivedIntent.getStringExtra(EXTRA_BUTTON);

        myListView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchViewCurrencies);


        adapter = new ListAdapter(this, currencies);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                currency = currencies.get(position);
                AddCurrencyActivity.textCompareCurrency.setText(currencies.get(position).getTitle());
                AddCurrencyActivity.setCompareCurrencyButton.setText(currencies.get(position).getTitle());

                //AddCurrencyActivity.setCompareCurrencyButton.drawable
                finish();
            }
        });


    }
}