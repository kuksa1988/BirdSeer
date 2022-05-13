package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class valitseLajit extends AppCompatActivity implements RecyclerViewInterface {

    RecyclerView recyclerViewLajit;
    private ArrayList<String> lajitArrayList;
    String havainto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valitse_lajit);

        recyclerViewLajit = findViewById(R.id.recyclerViewLajit);
        lajitArrayList = new ArrayList<>();

        MyadapterLajit myadapterLajit = new MyadapterLajit(this, lajitArrayList, this);
        recyclerViewLajit.setAdapter(myadapterLajit);
        recyclerViewLajit.setLayoutManager(new LinearLayoutManager(this));

        //Tämä lisää itemien väliin viivat.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        recyclerViewLajit.addItemDecoration(dividerItemDecoration);

        createListdata();

    }


    private void createListdata() {  //luetaan lajit assetseissa olevasta txt-tiedostosta arraylistiin
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("lajit.txt"), StandardCharsets.UTF_8));
            String laji;
            while((laji = br.readLine()) != null)
            lajitArrayList.add(laji);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override //klikatessa siirtyy paivaSijaintiin ja lisää havainto-olioon lajin nimen
    public void onItemClick(int position) {
        havainto = (lajitArrayList.get(position));
        Intent intent = new Intent(this, paivaSijainti.class);
        intent.putExtra("laji", havainto);
        startActivity(intent);
        Toast.makeText(this, havainto, Toast.LENGTH_SHORT).show();
    }
}