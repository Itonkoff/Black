package com.kofu.brighton.black;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.kofu.brighton.black.adapters.HistoryRecyclerAdapter;
import com.kofu.brighton.black.dtos.HistoryDto;
import com.kofu.brighton.black.services.APIServiceBuilder;
import com.kofu.brighton.black.services.ApiService;
import com.kofu.brighton.black.user.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private RecyclerView historyRecycler;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        sharedPref = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);

        editor = sharedPref.edit();

        historyRecycler = findViewById(R.id.history_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        historyRecycler.setLayoutManager(layoutManager);

//        List<HistoryDto> history = getHistoryList();
//        boolean ok = history.size() > 0;
//        HistoryRecyclerAdapter historyRecyclerAdapter;
//        if (ok) {
//
//        }
    }


    @Override
    protected void onResume() {
        fetchAndPopulateHistory();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_out:
                logout();
                break;
            case R.id.menu_predict:
                Intent intent = new Intent(HistoryActivity.this, MakePredictionActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        String token = sharedPref.getString(getString(R.string.token_key), "");
        ApiService taskService = APIServiceBuilder.buildService(ApiService.class);
        Call<Credentials> call = taskService.logOut("Token " + token);

        call.enqueue(new Callback<Credentials>() {
            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {
                if (response.code() == 204) {
                    editor.putString(getString(R.string.token_key), "");
                    editor.putString(getString(R.string.user_key), "");
                    editor.putString(getString(R.string.txt_password_key), "");
                    editor.commit();
                    finish();
                } else {
                    Toast.makeText(
                            HistoryActivity.this,
                            "Either username or password is incorrect",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {

            }
        });
    }

    private void fetchAndPopulateHistory() {
        String token = sharedPref.getString(getString(R.string.token_key), "");
        if (!token.equals("")) {
            ApiService taskService = APIServiceBuilder.buildService(ApiService.class);
            Call<List<HistoryDto>> call = taskService.getHistory("Token " + token);

            call.enqueue(new Callback<List<HistoryDto>>() {
                @Override
                public void onResponse(Call<List<HistoryDto>> call, Response<List<HistoryDto>> response) {
                    if (response.code() == 200) {
                        HistoryRecyclerAdapter historyRecyclerAdapter =
                                new HistoryRecyclerAdapter(HistoryActivity.this, response.body());
                        historyRecycler.setAdapter(historyRecyclerAdapter);
//                        List<HistoryDto> hist = response.body();
//                        for (HistoryDto historyItem : hist) {
//                            historyList.add(historyItem);
//                        }
                    } else {
                        Toast.makeText(
                                HistoryActivity.this,
                                "Got invalid response from server",
                                Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<List<HistoryDto>> call, Throwable t) {
                    Toast.makeText(
                            HistoryActivity.this,
                            "Could not reach the Server. Try again later",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });
        } else {
            finish();
        }
    }
}