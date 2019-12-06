package com.example.currencyconverter;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Currency.class, version = 1)
public abstract class CurrencyDatabase extends RoomDatabase {

    private static CurrencyDatabase instance;

    public abstract CurrencyDao currencyDao();

    public static synchronized CurrencyDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CurrencyDatabase.class, "currency_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(rooCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback rooCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private CurrencyDao currencyDao;

        private PopulateDbAsyncTask (CurrencyDatabase db){
            currencyDao = db.currencyDao();
        }

        @Override
        public Void doInBackground(Void... voids) {
            currencyDao.insert(new Currency("EUR", "Euro", 1., R.drawable.eur, "The Euro is the base currency."));
            currencyDao.insert(new Currency("USD", "US dollar", 1.1071, R.drawable.us, "1.1071 US dollar are 1 Euro."));
            currencyDao.insert(new Currency("JPY", "Japanese yen", 120.39, R.drawable.jp, "120.39 Japanese yen are 1 Euro."));
            currencyDao.insert(new Currency("GBP", "Pound sterling", 0.852,R.drawable.gb, "0.852 Pound sterling are 1 Euro."));
            currencyDao.insert(new Currency("AUD", "Australian dollar", 1.6186,R.drawable.au, "1.6186 Australian dollar are 1 Euro."));
            currencyDao.insert(new Currency("CAD", "Canadian dollar",1.4747 ,R.drawable.ca, "1.4747 Canadian dollar are 1 Euro."));

            return null;
        }
    }
}
