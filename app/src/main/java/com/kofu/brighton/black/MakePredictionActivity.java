package com.kofu.brighton.black;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.kofu.brighton.black.dtos.HistoryDto;
import com.kofu.brighton.black.dtos.HistoryForPredictionDto;
import com.kofu.brighton.black.services.APIServiceBuilder;
import com.kofu.brighton.black.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakePredictionActivity extends AppCompatActivity {

    private TextInputEditText interest;
    private TextInputEditText inflation;
    private TextInputEditText gvt;
    private TextInputEditText wheat;
    private TextInputEditText bread;
    private TextInputEditText diesel;
    private TextInputEditText petrol;
    private TextInputEditText servants;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_prediction);

        sharedPref = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);

        interest = findViewById(R.id.te_intrest);
        inflation = findViewById(R.id.te_inflation);
        gvt = findViewById(R.id.te_gvt);
        wheat = findViewById(R.id.te_wheat);
        bread = findViewById(R.id.te_bread);
        diesel = findViewById(R.id.te_diesel);
        petrol = findViewById(R.id.te_petrol);
        servants = findViewById(R.id.te_civil);

        Button submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitValuesForPrediction();
            }
        });
    }


    private void submitValuesForPrediction() {
        HistoryForPredictionDto values = constructValueObject();
        String token = sharedPref.getString(getString(R.string.token_key), "");

        if (!token.equals("")) {
            ApiService taskService = APIServiceBuilder.buildService(ApiService.class);
            Call<HistoryDto> call = taskService.placePrediction("Token " + token, values);

            call.enqueue(new Callback<HistoryDto>() {
                @Override
                public void onResponse(Call<HistoryDto> call, Response<HistoryDto> response) {
                    if (response.code() == 201) {
                        Intent intent =
                                new Intent(MakePredictionActivity.this, PredictionActivity.class);
                        intent.putExtra("prediction", response.body());
                        startActivity(intent);
                    } else {
                        Toast.makeText(
                                MakePredictionActivity.this,
                                "Could not make prediction. Try again later", Toast.LENGTH_LONG)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<HistoryDto> call, Throwable t) {
                    Toast.makeText(
                            MakePredictionActivity.this,
                            "Could not connect to server. Try again later", Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
    }

    private HistoryForPredictionDto constructValueObject() {
        double interestValue = format(interest.getText().toString());
        double inflationValue = format(inflation.getText().toString());
        double gvtValue = format(gvt.getText().toString());
        double wheatValue = format(wheat.getText().toString());
        double breadValue = format(bread.getText().toString());
        double dieselValue = format(diesel.getText().toString());
        double petrolValue = format(petrol.getText().toString());
        double servantValue = format(servants.getText().toString());

        return new HistoryForPredictionDto(
                interestValue,
                inflationValue,
                gvtValue,
                wheatValue,
                breadValue,
                dieselValue,
                petrolValue,
                servantValue
        );
    }

    private double format(String text) {
        try {
            return Double.parseDouble(text);
        } catch (Exception ex) {
            Toast.makeText(MakePredictionActivity.this,
                    "You entered invalid input. Use DECIMAL or INTEGER values only",
                    Toast.LENGTH_LONG).show();
        }
        return 0;
    }
}