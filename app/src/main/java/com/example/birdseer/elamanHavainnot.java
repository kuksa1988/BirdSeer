package com.example.birdseer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class elamanHavainnot extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> havaittuLajiArrayList, havainnonSijaintiArrayList;
    ArrayList<Integer> havainnonIdArrayList, havainnonPaivaArrayList, havainnonKkArrayList, havainnonVuosiArrayList;

    MyAdapterHavainnot myAdapterHavainnot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elaman_havainnot);

        recyclerView = findViewById(R.id.recyclerview_elamanHavainnot);

        myDB = new MyDatabaseHelper((this));
        havainnonIdArrayList = new ArrayList<>();
        havaittuLajiArrayList = new ArrayList<>();
        havainnonVuosiArrayList = new ArrayList<>();
        havainnonKkArrayList = new ArrayList<>();
        havainnonPaivaArrayList = new ArrayList<>();
        havainnonSijaintiArrayList = new ArrayList<>();

        storeDataInArrays();

        myAdapterHavainnot = new MyAdapterHavainnot(this, havaittuLajiArrayList, havainnonSijaintiArrayList,
                havainnonIdArrayList, havainnonPaivaArrayList, havainnonKkArrayList, havainnonVuosiArrayList);

        recyclerView.setAdapter(myAdapterHavainnot);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setTitle("Havainnot - " + myAdapterHavainnot.getItemCount());
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                havainnonIdArrayList.add(cursor.getInt(0));
                havaittuLajiArrayList.add(cursor.getString(1));
                havainnonSijaintiArrayList.add(cursor.getString(2));
                havainnonVuosiArrayList.add(cursor.getInt(3));
                havainnonKkArrayList.add(cursor.getInt(4));
                havainnonPaivaArrayList.add(cursor.getInt(5));
            }
        }

    }
}