package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static List<ListItem> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        items.add(new ListItem("EUR", "Euro", 1., R.drawable.eur, null));
        items.add(new ListItem("USD", "US dollar", 1.1071, R.drawable.us, null));
        items.add(new ListItem("JPY", "Japanese yen", 120.39, R.drawable.jp, null));
        items.add(new ListItem("GBP", "Pound sterling", 0.852,R.drawable.gb, null));
        items.add(new ListItem("AUD", "Australian dollar", 1.6186,R.drawable.au, null));
        items.add(new ListItem("CAD", "Canadian dollar",1.4747 ,R.drawable.ca, null));


        getSupportActionBar().hide();

        YoYo.with(Techniques.Shake)
                .duration(3500)
                .repeat(2)
                .playOn(findViewById(R.id.dollarSign));

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try {

                    sleep(3500);
                    Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
