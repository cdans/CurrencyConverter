package com.example.currencyconverter;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class SecondActivity extends AppCompatActivity {


    private CurrencyDatabase mDb;
    public static ListView myListView;
    public static ListAdapter adapter;
    private ImageButton addButton;
    private Button listTransferButton;
    private SearchView searchView;


    public List<Currency> items = HomeActivity.currencies;


    @Override
    protected void onCreate (Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView (R.layout.secondactivitydesign);
        myListView = (ListView) findViewById(R.id.listView);
        addButton = (ImageButton) findViewById(R.id.addButton);
        searchView = (SearchView) findViewById(R.id.searchViewCurrencies);


        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);


        mDb = AppActivity.getDatabase();


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                //  Data transfer: image , name, description
                intent.putExtra("EXTRA_NAME", items.get(position).getTitle());
                intent.putExtra("EXTRA_DESCRIPTION", items.get(position).getDescription());
                intent.putExtra("EXTRA_IMAGE_ID", items.get(position).getImageId());
                startActivity(intent);
            }
        });


        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                items.remove(position);
                myListView.setAdapter(adapter);
                return true;
            }
        });




        addButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this, AddCurrencyActivity.class);


                startActivity(intent);
            }
        });
    }
}