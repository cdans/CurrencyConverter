package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //public static List<Currency> currencies = new ArrayList<>();

    public static CurrencyDatabase mDb;

    public static List<Currency> createListC (){

        List<Currency> currencies = mDb.currencyDao().getAllCurrencies();
        return currencies;
    }

    public static Currency getCurrencyByCode (String code, List<Currency> currencies){

        Currency currency = null;
        for (int i = 0; i<currencies.size(); i++)
            if (currencies.get(i).getCode()!=null){
                if (currencies.get(i).getCode().equals(code)){
                    currency = currencies.get(i);
                }
            }

        return currency;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDb = AppActivity.getDatabase();

        List<Currency> currencies2 =createListC();

        /*currencies.add(new Currency("EUR", "Euro", 1., R.drawable.eur, "The Euro is the base currency."));
        currencies.add(new Currency("USD", "US dollar", 1.1071, R.drawable.us, "1.1071 US dollar are 1 Euro."));
        currencies.add(new Currency("JPY", "Japanese yen", 120.39, R.drawable.jp, "120.39 Japanese yen are 1 Euro."));
        currencies.add(new Currency("GBP", "Pound sterling", 0.852,R.drawable.gb, "0.852 Pound sterling are 1 Euro."));
        currencies.add(new Currency("AUD", "Australian dollar", 1.6186,R.drawable.au, "1.6186 Australian dollar are 1 Euro."));
        currencies.add(new Currency("CAD", "Canadian dollar",1.4747 ,R.drawable.ca, "1.4747 Canadian dollar are 1 Euro."));*/

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
