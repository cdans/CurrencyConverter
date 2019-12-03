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
    public List<ListItem> items = HomeActivity.items;

    public static final String EXTRA_BUTTON = "EXTRA_BUTTON";


    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_choose_currency);

        Intent receivedIntent = getIntent();
        final String button = receivedIntent.getStringExtra(EXTRA_BUTTON);

        myListView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchViewCurrencies);


        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = items.get(position).getImageId();
                if (button.equals("buttonCurrencyOne")) {
                    MainActivity.currencyOne = items.get(position);
                    MainActivity.buttonCurrencyOne.setText(items.get(position).getTitle());
                }
                else{
                    MainActivity.currencyTwo = items.get(position);
                    MainActivity.buttonCurrencyTwo.setText(items.get(position).getTitle());

                }
                //MainActivity.buttonCurrencyOne.drawable
                finish();
                //startActivity(intent);
            }
        });


            }
        }