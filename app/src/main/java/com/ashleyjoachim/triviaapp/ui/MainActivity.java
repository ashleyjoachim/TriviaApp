package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.ashleyjoachim.triviaapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.start);
        button.setOnClickListener(view -> startCategoryActivity());
    }

    private void startCategoryActivity() {
        startActivity(new Intent(this, CategoryActivity.class));
    }
}
