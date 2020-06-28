package com.kofu.brighton.black;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.kofu.brighton.black.dtos.AuthenticationDto;
import com.kofu.brighton.black.dtos.UserLoginDto;
import com.kofu.brighton.black.services.APIServiceBuilder;
import com.kofu.brighton.black.services.ApiService;
import com.kofu.brighton.black.user.Credentials;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText username;
    private TextInputEditText password;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
//        SharedPreferences sharedPref =
//                MainActivity.this
//                        .Preferences(getString(R.string.shared_pref), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        String token = sharedPref.getString(getString(R.string.token_key), "");
        if (!token.equals(""))
            proceed();

        final Button login = findViewById(R.id.btn_sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        final Button signUp = findViewById(R.id.btn_sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        username = findViewById(R.id.text_uname);
        password = findViewById(R.id.txt_password);
    }

    private void proceed() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    private void signUp() {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }

    private void login() {
        UserLoginDto user = new UserLoginDto(username.getText().toString(), password.getText().toString());

        ApiService taskService = APIServiceBuilder.buildService(ApiService.class);
        Call<AuthenticationDto> call = taskService.login(user);

        call.enqueue(new Callback<AuthenticationDto>() {
            @Override
            public void onResponse(Call<AuthenticationDto> call, Response<AuthenticationDto> response) {
                if (response.code() == 200) {
                    Toast.makeText(MainActivity.this, response.body().auth_token, Toast.LENGTH_LONG).show();
                    editor.putString(getString(R.string.token_key), response.body().auth_token);
                    editor.putString(getString(R.string.user_key), user.username);
                    editor.putString(getString(R.string.txt_password_key),user.password);
                    editor.commit();
                    proceed();
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Either username or password is incorrect",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationDto> call, Throwable t) {
                Toast.makeText(
                        MainActivity.this,
                        "Could not reach the Server. Try again later",
                        Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}
