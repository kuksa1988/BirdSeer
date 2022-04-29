package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class valitseLajit extends AppCompatActivity {

    RecyclerView recyclerViewLajit;
    private ArrayList<String> lajitArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valitse_lajit);



        recyclerViewLajit = findViewById(R.id.recyclerViewLajit);
        lajitArrayList = new ArrayList<>();

        MyadapterLajit myadapterLajit = new MyadapterLajit(this, lajitArrayList);
        recyclerViewLajit.setAdapter(myadapterLajit);
        recyclerViewLajit.setLayoutManager(new LinearLayoutManager(this));

        createListdata();


    }

    private void createListdata() {
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
}