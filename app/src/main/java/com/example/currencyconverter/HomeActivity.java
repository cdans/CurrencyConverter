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
