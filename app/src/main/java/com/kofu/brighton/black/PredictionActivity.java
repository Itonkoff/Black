package com.kofu.brighton.black;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kofu.brighton.black.dtos.HistoryDto;

public class PredictionActivity extends AppCompatActivity {

    private HistoryDto prediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        Intent intent = getIntent();
        prediction = intent.getParcelableExtra("prediction");

        if (!(prediction == null)) {
            populateValues();
        }
    }

    private void populateValues() {
        TextView textView = findViewById(R.id.tv_pred);
        TextView textView1 = findViewById(R.id.tv_int);
        TextView textView2 = findViewById(R.id.tv_inf);
        TextView textView3 = findViewById(R.id.tv_gvt);
        TextView textView4 = findViewById(R.id.tv_wh);
        TextView textView5 = findViewById(R.id.tv_bread);
        TextView textView6 = findViewById(R.id.tv_die);
        TextView textView7 = findViewById(R.id.tv_petrol);
        TextView textView8 = findViewById(R.id.tv_civil);

        textView.setText(format(prediction.predicted_value));
        textView1.setText(format(prediction.interest_rate));
        textView2.setText(format(prediction.inflation_rate));
        textView3.setText(format(prediction.gvt_expenditure));
        textView4.setText(format(prediction.wheat_price));
        textView5.setText(format(prediction.bread_price));
        textView6.setText(format(prediction.diesel_price));
        textView7.setText(format(prediction.petrol_price));
        textView8.setText(format(prediction.civil_servants));
    }

    private String format(double value){
        return String.valueOf(value);
    }
}