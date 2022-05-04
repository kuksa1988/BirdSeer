package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class paivaSijainti extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView dateButton;
    private EditText locationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_sijainti);
        dateButton = findViewById(R.id.datePickerButton);
        locationButton = (EditText) findViewById(R.id.sijaintibutton);
        String sijainti = locationButton.getText().toString().trim();
        System.out.println("sijainti = " + sijainti);

        dateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Tämä kohta poimii käyttäjän puhelimelta hänen päivämääränsä ja syöttää
                //Sen ensimmäisenä näkyviin käyttäjälle, kun avaa kalenterin
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(paivaSijainti.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month + 1;
            String date = day + "/" + month + "/" + year;
            dateButton.setText(date);

            }
        };
    }

}