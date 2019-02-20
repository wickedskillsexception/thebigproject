package com.siit.thebigproject.domain;

public class Ingredient extends ObjectId {
    private String name;
    private MeasurementUnit unit;
    private int quantity;

    public Ingredient(String name, MeasurementUnit unit) {
        this.name = name;
        this.unit = unit;
    }
}
