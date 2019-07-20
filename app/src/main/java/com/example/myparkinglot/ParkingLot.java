package com.example.myparkinglot;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import java.util.HashMap;

public class ParkingLot {

    Database db;
    Context context;

    void setDatabase(Database db, Context context){
        this.db = db;
        this.context = context;
    }

    HashMap<Integer, Vehicle> parkedVehicle = new HashMap<>();
    HashMap<Integer, Integer> vehicle_lot_data = new HashMap<>();

    boolean parkVehicle(Vehicle vehicle, int lot) {
        if (isLotBooked(lot) && isVehicleExists(vehicle.getId())){
            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.already_chooson), Toast.LENGTH_LONG).show();
        }else if (isLotBooked(lot)) {
            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.lot_taken), Toast.LENGTH_LONG).show();
            return false;
        } else if (isVehicleExists(vehicle.getId())) {
            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.vehicle_present), Toast.LENGTH_LONG).show();
            return false;
        }
        return db.parkVehicle(vehicle);
    }

    boolean isLotBooked(int lot) {
        Cursor cursor = db.getAllBookedLots();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getInt(1) == lot)
                        return true;
                } while (cursor.moveToNext());
            }
        }
        return false;
    }

    boolean isVehicleExists(int vehicleId){
        Cursor cursor = db.getParkedVehicle(vehicleId);
        if (cursor!=null)
            return cursor.getCount() > 0;
        else
            return false;
    }
}
