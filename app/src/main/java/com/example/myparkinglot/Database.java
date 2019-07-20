package com.example.myparkinglot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class Database extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "parking_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "parking_lots";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOT_NUMBER = "lot_number";
    private static final String COLUMN_VEHICLE_NUMBER = "vehicle_number";
    SQLiteDatabase db = null;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        String create_table_query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT, " + COLUMN_LOT_NUMBER + " INTEGER, " + COLUMN_VEHICLE_NUMBER + " INTEGER UNIQUE);";
        db.execSQL(create_table_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.db = db;
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    boolean parkVehicle(Vehicle vehicle) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOT_NUMBER, vehicle.getLot());
        contentValues.put(COLUMN_VEHICLE_NUMBER, vehicle.getId());
        db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    Cursor getParkedVehicle() {
        db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    Cursor getParkedVehicle(int vehicleId){
        db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_VEHICLE_NUMBER + " = " + vehicleId, null);
    }

    Cursor getAllBookedLots(){
        db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

}