package com.example.currencyconverter;

        import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ChooseCurrency extends AppCompatActivity {

    //public static ArrayList<ListItem> items;
    public static ListView myListView;
    public static ListAdapter adapter;
    private ImageButton addButton;
    private Button listTransferButton;
    private SearchView searchView;
    public List<ListItem> items = HomeActivity.items;


    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_choose_currency);

        myListView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchViewCurrencies);




        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);


            }
        }