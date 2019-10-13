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

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private ListView myListView;
    private ListAdapter adapter;
    private ImageButton addButton;
    private Button listTransferButton;
    private SearchView searchView;
    //public List<ListItem> items = MainActivity.items;


    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView (R.layout.secondactivitydesign);
        myListView = (ListView) findViewById(R.id.listView);
        addButton = (ImageButton) findViewById(R.id.addButton);
        searchView = (SearchView) findViewById(R.id.searchViewCurrencies);
       // listTransferButton = (Button) findViewById(R.id.listTransferButton);


        final List<ListItem> items = new ArrayList<>();
        items.add(new ListItem("Euro", R.drawable.euro, "The euro is the official currency of 19 of the 28 member states of the European Union. This group of states is known as the eurozone or euro area, and counts about 343 million citizens as of 2019. The euro, which is divided into 100 cents, is the second-largest and second-most traded currency in the foreign exchange market after the United States dollar."));
        items.add(new ListItem("United States dollar", R.drawable.dollar, "The United States dollar is the official currency of the United States and its territories per the United States Constitution since 1792. In practice, the dollar is divided into 100 smaller cent (¢) units, but is occasionally divided into 1000 mills (₥) for accounting. The circulating paper money consists of Federal Reserve Notes that are denominated in United States dollars."));


        adapter = new ListAdapter(this, items);
        myListView.setAdapter(adapter);

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
                items.add(new ListItem("Euro", R.drawable.euro, "The euro is the official currency of 19 of the 28 member states of the European Union. This group of states is known as the eurozone or euro area, and counts about 343 million citizens as of 2019. The euro, which is divided into 100 cents, is the second-largest and second-most traded currency in the foreign exchange market after the United States dollar."));
                myListView.setAdapter(adapter);
            }
        });



       /* listTransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ListAgain.class);
                intent.putParcelableArrayListExtra("EXTRA_LIST", (ArrayList<? extends Parcelable>) items);
                startActivity(intent);
            }

        });
        */

        }
}