package com.example.birdseer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NIMI = "HavaintoKirjasto.db";
    private static final int DATABASE_VERSIO = 1;

    private static final String TABLE_NAME = "Havainto_luettelo";
    private static final String ID = "_id";
    private static final String LAJI = "laji";
    private static final String VUOSI = "vuosi";
    private static final String KUUKAUSI = "kuukausi";
    private static final String PAIVA = "paiva";
    private static final String SIJAINTI = "sijainti";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NIMI, null, DATABASE_VERSIO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LAJI +  " TEXT, " +
                SIJAINTI + " TEXT, " +
                VUOSI + " INTEGER, " +
                KUUKAUSI + " INTEGER, " +
                PAIVA + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void lisaaLaji(String laji, String sijainti, int vuosi, int kuukausi, int paiva){
        //getWritableDatabase mahdollistaa kirjoittamisen tässä olevaan databaseen
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(LAJI, laji);
        cv.put(SIJAINTI, sijainti);
        cv.put(VUOSI, vuosi);
        cv.put(KUUKAUSI, kuukausi);
        cv.put(PAIVA, paiva);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Lisääminen epäonnistui", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Lisääminen onnistui", Toast.LENGTH_SHORT).show();

        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
