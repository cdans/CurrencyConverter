package com.example.currencyconverter;

import android.os.Parcel;
import android.os.Parcelable;

public class ListItem  implements Parcelable {

    private String title;
    private int imageId;
    private String description;
    private String code;
    private Double rate;

    public ListItem (){
    }

    public ListItem(String code, String title, Double rate, int imageId, String description){
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.code = code;
        this.rate = rate;
    }

    protected ListItem(Parcel in) {
        title = in.readString();
        imageId = in.readInt();
        description = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

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

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
    return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(imageId);
        dest.writeString(description);
    }
}
