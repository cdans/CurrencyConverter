package com.example.currencyconverter;

import android.app.Application;

import androidx.room.Room;

import java.util.List;

public class AppActivity extends Application {
    static CurrencyDatabase db;

    @Override
    public void onCreate(){
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                CurrencyDatabase.class,
                "my_app_db")
                .allowMainThreadQueries()
                .build();


        List<Currency> currencies = db.currencyDao().getAllCurrencies();

        if(currencies.size()==0){
            db.currencyDao().insert(new Currency("EUR", "Euro", 1., R.drawable.eur, "The Euro is the base currency."));
            db.currencyDao().insert(new Currency("USD", "US dollar", 1.1071, R.drawable.us, "1.1071 US dollar are 1 Euro."));
            db.currencyDao().insert(new Currency("JPY", "Japanese yen", 120.39, R.drawable.jp, "120.39 Japanese yen are 1 Euro."));
            db.currencyDao().insert(new Currency("GBP", "Pound sterling", 0.852,R.drawable.gb, "0.852 Pound sterling are 1 Euro."));
            db.currencyDao().insert(new Currency("AUD", "Australian dollar", 1.6186,R.drawable.au, "1.6186 Australian dollar are 1 Euro."));
            db.currencyDao().insert(new Currency("CAD", "Canadian dollar",1.4747 ,R.drawable.ca, "1.4747 Canadian dollar are 1 Euro."));

        }
    }
    public static CurrencyDatabase getDatabase(){
        return db;
    }
}
