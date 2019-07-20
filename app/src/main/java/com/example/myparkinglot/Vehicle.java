package com.example.myparkinglot;

public class Vehicle {
    Integer id;
    Integer lot;

    Vehicle(int Id, int lot){
        this.id = Id;
        this.lot = lot;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLot() {
        return lot;
    }
}
