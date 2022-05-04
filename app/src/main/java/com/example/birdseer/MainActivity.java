package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button changeActivityToLisaaLajiBTN = findViewById(R.id.buttonlisaaLaji);

        changeActivityToLisaaLajiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityLisaaLaji();
            }
        });
    }

    private void changeActivityLisaaLaji() {
        Intent intent = new Intent(this, valitseLajit.class);
        startActivity(intent);
    }
}