package com.kofu.brighton.black.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class HistoryDto implements Parcelable {
    public int id;
    public Date date;
    public double interest_rate;
    public double inflation_rate;
    public double gvt_expenditure;
    public double wheat_price;
    public double bread_price;
    public double diesel_price;
    public double petrol_price;
    public double civil_servants;
    public double predicted_value;

    protected HistoryDto(Parcel in) {
        id = in.readInt();
        interest_rate = in.readDouble();
        inflation_rate = in.readDouble();
        gvt_expenditure = in.readDouble();
        wheat_price = in.readDouble();
        bread_price = in.readDouble();
        diesel_price = in.readDouble();
        petrol_price = in.readDouble();
        civil_servants = in.readDouble();
        predicted_value = in.readDouble();
    }

    public static final Creator<HistoryDto> CREATOR = new Creator<HistoryDto>() {
        @Override
        public HistoryDto createFromParcel(Parcel in) {
            return new HistoryDto(in);
        }

        @Override
        public HistoryDto[] newArray(int size) {
            return new HistoryDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(interest_rate);
        dest.writeDouble(inflation_rate);
        dest.writeDouble(gvt_expenditure);
        dest.writeDouble(wheat_price);
        dest.writeDouble(bread_price);
        dest.writeDouble(diesel_price);
        dest.writeDouble(petrol_price);
        dest.writeDouble(civil_servants);
        dest.writeDouble(predicted_value);
    }
}
