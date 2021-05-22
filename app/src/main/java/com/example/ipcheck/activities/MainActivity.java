package com.example.ipcheck.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ipcheck.R;
import com.example.ipcheck.api.APICall;
import com.example.ipcheck.dialogs.DialogInternetConnection;
import com.example.ipcheck.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewStartOrIP;
    private ImageView imageViewButtonStart;
    private TextView textView;
    private ProgressBar progressBar;
    private Button buttonYandexWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //open activity YandexWebView
        buttonYandexWebView = findViewById(R.id.buttonYandexWebView);
        buttonYandexWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YandexWebView.class);
                startActivity(intent);
            }
        });

        textViewStartOrIP = findViewById(R.id.textViewStartOrIP);
        textView = findViewById(R.id.textViewIP);
        progressBar = findViewById(R.id.progressBar);
        imageViewButtonStart = findViewById(R.id.imageViewButtonStart);
        imageViewButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();
            }
        });
    }

    public void run() {

        textViewStartOrIP.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        // Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.ipstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance for interface
        APICall apiCall = retrofit.create(APICall.class);
        Call<DataModel> call = apiCall.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                textView.setText(R.string.your_ip);
                progressBar.setVisibility(View.INVISIBLE);
                imageViewButtonStart.setVisibility(View.INVISIBLE);
                textViewStartOrIP.setVisibility(View.VISIBLE);
                assert response.body() != null;
                textViewStartOrIP.setText(response.body().getIp());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                DialogInternetConnection dialogInternetConnection = new DialogInternetConnection();
                dialogInternetConnection.show(getSupportFragmentManager(), "custom");
                textViewStartOrIP.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                textView.setText(R.string.ip_text);
            }
        });
    }
}