package com.example.currencyconverter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currency_table")
public class Currency{

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String title;
    private int imageId;
    private String description;
    private String code;
    private Double rate;

    public Currency(String code, String title, Double rate, int imageId, String description){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.code = code;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
