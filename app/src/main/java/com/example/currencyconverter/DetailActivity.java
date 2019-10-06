package com.example.currencyconverter;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_ID = "EXTRA_IMAGE_ID";
    public static final String EXTRA_NAME = "EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detaildesign);

        Intent receivedIntent = getIntent();
        String name = receivedIntent.getStringExtra(EXTRA_NAME);
        String description = receivedIntent.getStringExtra(EXTRA_DESCRIPTION);

        TextView textName = (TextView) findViewById(R.id.textName);
        textName.setText(name);

        TextView textDescription = (TextView) findViewById(R.id.textDescription);
        textDescription.setText(description);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewCurrency);

        int index = (receivedIntent.getIntExtra("EXTRA_IMAGE_ID", -1));
        if(!(index == -1)){
            imageView.setImageResource(index);
        }
    }
}
