package com.example.myparkinglot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText vehicle_lot_number_field, vehicle_number_field;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vehicle_number_field = findViewById(R.id.vehicle_no);
        vehicle_lot_number_field = findViewById(R.id.lot_number);
        findViewById(R.id.park_vehicle).setOnClickListener(this);
        db = new Database(this);
    }

    @Override
    public void onClick(View v) {
        if (vehicle_number_field.length() == 0 || vehicle_lot_number_field.length() == 0){
            Toast.makeText(this, getResources().getString(R.string.mandatory_fields), Toast.LENGTH_LONG).show();
            return;
        }
        Vehicle vehicle = new Vehicle(Integer.parseInt(vehicle_number_field.getText().toString()),
                Integer.parseInt(vehicle_lot_number_field.getText().toString()));
        ParkingLot parkingLot= new ParkingLot();
        parkingLot.setDatabase(db, this);
        if (parkingLot.parkVehicle(vehicle, vehicle.getLot()))
            Toast.makeText(this, getResources().getString(R.string.vehicle_parked), Toast.LENGTH_LONG).show();
    }
}
