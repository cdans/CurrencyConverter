package com.example.currencyconverter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrencyDao {

    @Insert
    void insert(Currency currency);

    @Update
    void update(Currency currency);

    @Delete
    void delete(Currency currency);

    @Query("SELECT * FROM currency_table ORDER BY title ASC")
    List<Currency> getAllCurrencies(); //LiveData<...>?

   /*@Query("SELECT * FROM currency_table WHERE title LIKE :currencyText")
    public List<Currency> getCurrenciesFilter (String currencyText);*/


}
