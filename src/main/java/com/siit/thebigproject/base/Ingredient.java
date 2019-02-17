package com.siit.thebigproject.base;

public class Ingredient {
    private String name;
    private MeasurmentUnit unit;
    private int quantity;

    public Ingredient(String name, MeasurmentUnit unit) {
        this.name = name;
        this.unit = unit;
    }
}
