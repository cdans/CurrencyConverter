package com.example.currencyconverter;

import java.util.Date;

public class ModelConversion {

    Date time;
    Double amount;

    public ModelConversion (){ }

    public ModelConversion(Date time, Double amount) {
        this.time = time;
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Number getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
