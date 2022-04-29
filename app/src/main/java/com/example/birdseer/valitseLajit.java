package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class valitseLajit extends AppCompatActivity {

    RecyclerView recyclerViewLajit;
    String lajit[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valitse_lajit);




        recyclerViewLajit = findViewById(R.id.recyclerViewLajit);
        lajit = getResources().getStringArray(R.array.lajit);

        MyadapterLajit myadapterLajit = new MyadapterLajit(this, lajit);
        recyclerViewLajit.setAdapter(myadapterLajit);
        recyclerViewLajit.setLayoutManager(new LinearLayoutManager(this));


    }
}