package com.siit.thebigproject.base;

import java.util.Objects;

public class Ingredient extends ObjectId{

    private String name;
    private String unit;
    private Double quantity;

    public Ingredient (){

    }
    public Ingredient(String name, String unit, double quantity) {

        this.name = name;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
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

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


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


}
