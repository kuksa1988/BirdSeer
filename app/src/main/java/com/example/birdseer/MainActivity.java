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

        Button changeActivityToVuodenHavainnotBTN = findViewById(R.id.buttonHavainnot);
        Button changeActivityToLisaaLajiBTN = findViewById(R.id.buttonlisaaLaji);

        changeActivityToLisaaLajiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityLisaaLaji();
            }
        });

        changeActivityToVuodenHavainnotBTN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){ changeActivityHavainnot();

            }
        });
    }

    private void changeActivityHavainnot() {
        Intent intent = new Intent (this, elamanHavainnot.class);
        startActivity(intent);
    }

    private void changeActivityLisaaLaji() {
        Intent intent = new Intent(this, valitseLajit.class);
        startActivity(intent);
    }
}