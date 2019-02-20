package com.siit.thebigproject.base;

import java.util.Objects;

public class Ingredient {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasurmentUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", unit=" + unit +
                ", quantity=" + quantity +
                '}';
    }

    public void setUnit(MeasurmentUnit unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private String name;
    private MeasurmentUnit unit;
    private int quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return quantity == that.quantity &&
                name.equals(that.name) &&
                unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, quantity);
    }

    public Ingredient(String name, MeasurmentUnit unit, int quantity) {
        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }
}
