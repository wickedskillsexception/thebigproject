package com.siit.thebigproject.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

public class Ingredient extends ObjectId{

    @NotEmpty
    private String name;

    @NotEmpty
    private MeasurementUnit unit;

    public Ingredient(String name, String unit) {
        this.name = name;
        this.unit = MeasurementUnit.valueOf(unit);
    }

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit.toString();
    }

    public void setUnit(String unit) {
        this.unit = MeasurementUnit.valueOf(unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name) &&
                unit == that.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }

}
