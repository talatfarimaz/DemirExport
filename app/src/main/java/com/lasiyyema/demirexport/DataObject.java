package com.lasiyyema.demirexport;

import android.widget.Button;
import android.widget.TextView;

public class DataObject {
    private double kamyon;
    private double kalori;
    private int id;
    private Button delete;
    private TextView information;

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public TextView getInformation() {
        return information;
    }

    public void setInformation(TextView information) {
        this.information = information;
    }

    public double getKamyon() {
        return kamyon;
    }

    public void setKamyon(double kamyon) {
        this.kamyon = kamyon;
    }

    public double getKalori() {
        return kalori;
    }

    public void setKalori(double kalori) {
        this.kalori = kalori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
