package com.example.myapplication.interfaz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;

public class PresentacionActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_presentacion);
        progressBar=findViewById(R.id.progressBar2);
       progressBar.setVisibility(View.VISIBLE);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences= getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                boolean sesion=preferences.getBoolean("sesion",false);
                if(sesion){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                    startActivity(intent);
                   // overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
                    finish();
                }

            }
        },2000);
    }
}