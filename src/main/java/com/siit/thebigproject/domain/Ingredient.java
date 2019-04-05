package com.siit.thebigproject.domain;

import java.util.Objects;

public class Ingredient extends ObjectId {

    private String name;

    private String unit;

    private int unitFactorTransformation;


    public Ingredient() {
    }

    public Ingredient(long id, String name, String unit, int unitFactorTransformation) {
        setId(id);
        this.name = name;
        this.unit = unit;
        this.unitFactorTransformation = unitFactorTransformation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setUnitFactorTransformation(int unitFactorTransformation) {
        this.unitFactorTransformation = unitFactorTransformation;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public int getUnitFactorTransformation() {
        return unitFactorTransformation;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                ", id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", unitFactorTransformation=" + unitFactorTransformation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return unitFactorTransformation == that.unitFactorTransformation &&
                Objects.equals(name, that.name) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, unitFactorTransformation);
    }
}
