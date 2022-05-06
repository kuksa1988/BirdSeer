package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class paivaSijainti extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView dateButton;
    private EditText locationButton;
    private Button confirmButton;
    private int paiva;
    private int kuukausi;
    private int vuosi;
    private String sijainti;
    ArrayList<String> lajinimiArrayList, sijaintiArrayList;
    ArrayList<Integer> dayArrayList, monthArrayList, yearArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiva_sijainti);
        dateButton = findViewById(R.id.datePickerButton);
        locationButton = (EditText) findViewById(R.id.sijaintibutton);
        confirmButton = (Button) findViewById(R.id.lisaaLaji);
        confirmButton.setEnabled(false);


        dateButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Tämä kohta poimii käyttäjän puhelimelta hänen päivämääränsä ja syöttää
                //Sen ensimmäisenä näkyviin käyttäjälle, kun avaa kalenterin
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Tämä kohta kuuntelee sitä, kun painetaan "lisää laji napia ja
                //Ottaa sitten vasta tiedon paikasta Sijainti -Stringiin
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sijainti = locationButton.getText().toString().trim();
                        openMain();
                    }
                });
                //Tämä aukaisee päivämäärä-näkymän erillisenä "pop-up" ikkunana
                DatePickerDialog dialog = new DatePickerDialog(paivaSijainti.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //Tämä katsoo, minkä päivämäärän käyttäjä lopulta valitsee ja lisää
        //ne havaintoihin
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                //Kuukauteen tulee lisätä 1, sillä muuten numeroina päivämäärä on yhdellä väärä
            month = month + 1;
            String date = day + "/" + month + "/" + year;
            paiva = day;
            kuukausi = month;
            vuosi = year;
            dateButton.setText(date);
            confirmButton.setEnabled(true);



            }
        };
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        String ilmoitus = "Ajaksi lisättiin: " + paiva + " " + kuukausi + " " + vuosi + ", Ja sijainniksi: " + sijainti + " Sekä lajina on: " + getIntent().getStringExtra("laji");
        Toast.makeText(this, ilmoitus, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

}