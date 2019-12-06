package com.example.currencyconverter;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class CurrencyRepository {
    private CurrencyDao currencyDao;
    private List<Currency> allCurrencies;

    public CurrencyRepository(Application application){
        CurrencyDatabase database = CurrencyDatabase.getInstance(application);
        currencyDao = database.currencyDao();
        allCurrencies = currencyDao.getAllCurrencies();
    }

    public void insert (Currency currency){
        new InsertCurrencyAsyncTask(currencyDao).execute(currency);
    }

    public void update (Currency currency){
        new UpdateCurrencyAsyncTask(currencyDao).execute(currency);
    }

    public void delete (Currency currency){
        new DeleteCurrencyAsyncTask(currencyDao).execute(currency);

    }

    public List<Currency> getAllCurrencies(){
        return allCurrencies;
    }

    private static class InsertCurrencyAsyncTask extends AsyncTask<Currency, Void, Void>{
        private CurrencyDao currencyDao;

        private InsertCurrencyAsyncTask(CurrencyDao currencyDao){
            this.currencyDao = currencyDao;
        }

        @Override
        protected Void doInBackground(Currency... currencies) {
            currencyDao.insert(currencies[0]);
            return null;
        }
    }

    private static class UpdateCurrencyAsyncTask extends AsyncTask<Currency, Void, Void>{
        private CurrencyDao currencyDao;

        private UpdateCurrencyAsyncTask(CurrencyDao currencyDao){
            this.currencyDao = currencyDao;
        }

        @Override
        protected Void doInBackground(Currency... currencies) {
            currencyDao.update(currencies[0]);
            return null;
        }
    }

    private static class DeleteCurrencyAsyncTask extends AsyncTask<Currency, Void, Void>{
        private CurrencyDao currencyDao;

        private DeleteCurrencyAsyncTask(CurrencyDao currencyDao){
            this.currencyDao = currencyDao;
        }

        @Override
        protected Void doInBackground(Currency... currencies) {
            currencyDao.delete(currencies[0]);
            return null;
        }
    }
}
