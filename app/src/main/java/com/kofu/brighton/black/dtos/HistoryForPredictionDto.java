package com.kofu.brighton.black.dtos;

public class HistoryForPredictionDto {
    public double interest_rate;
    public double inflation_rate;
    public double gvt_expenditure;
    public double wheat_price;
    public double bread_price;
    public double diesel_price;
    public double petrol_price;
    public double civil_servants;

    public HistoryForPredictionDto(
            double interest_rate,
            double inflation_rate,
            double gvt_expenditure,
            double wheat_price,
            double bread_price,
            double diesel_price,
            double petrol_price,
            double civil_servants) {

        this.interest_rate = interest_rate;
        this.inflation_rate = inflation_rate;
        this.gvt_expenditure = gvt_expenditure;
        this.wheat_price = wheat_price;
        this.bread_price = bread_price;
        this.diesel_price = diesel_price;
        this.petrol_price = petrol_price;
        this.civil_servants = civil_servants;
    }
}
