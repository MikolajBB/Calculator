package com.example.mikloaj.kalkulator2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView aboutView;
    TextView authorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutView = findViewById(R.id.aboutCalc);
        authorView = findViewById(R.id.authorView);
        aboutView.setText(R.string.short_info);
        authorView.setText(R.string.auhor);
    }
}