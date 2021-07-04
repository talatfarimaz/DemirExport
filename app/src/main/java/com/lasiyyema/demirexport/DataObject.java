package com.lasiyyema.demirexport;

public class DataObject {
    private double truckAmount;
    private double calorie;
    private double tonnage;
    private int id;


    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonaj) {
        this.tonnage = tonaj;
    }

    public double getTruck() {
        return truckAmount;
    }

    public void setTruck(double kamyon) {
        this.truckAmount = kamyon;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double kalori) {
        this.calorie = kalori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
