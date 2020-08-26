package com.company.java.model;

import com.company.java.model.NamedEntity;

//Клас багаж
public class Cargo extends NamedEntity {
    private String typeCargo;   // тип вантажу
    private double weightCargo;  // вага вантажу
    private double volumeCargo;  // обєм вантажу
    private int quantityCargo;    // кількість вантажу

    public Cargo() {
    }

    public Cargo(String typeCargo, double weightCargo, double volumeCargo, int quantityCargo) {
        this.typeCargo = typeCargo;
        this.weightCargo = weightCargo;
        this.volumeCargo = volumeCargo;
        this.quantityCargo = quantityCargo;
    }

    public String getTypeCargo() {
        return typeCargo;
    }

    public void setTypeCargo(String typeCargo) {
        this.typeCargo = typeCargo;
    }

    public double getWeightCargo() {
        return weightCargo;
    }

    public void setWeightCargo(double weightCargo) {
        this.weightCargo = weightCargo;
    }

    public double getVolumeCargo() {
        return volumeCargo;
    }

    public void setVolumeCargo(double volumeCargo) {
        this.volumeCargo = volumeCargo;
    }

    public int getQuantityCargo() {
        return quantityCargo;
    }

    public void setQuantityCargo(int quantityCargo) {
        this.quantityCargo = quantityCargo;
    }

    @Override
    public String toString() {
        return "Cargo{" + "type cargo :" + typeCargo +
                ",weightCargo :" + weightCargo +
                ",volumeCargo :" + volumeCargo +
                ",quantityCargo :" + quantityCargo + '}' + "\n";
    }
}

