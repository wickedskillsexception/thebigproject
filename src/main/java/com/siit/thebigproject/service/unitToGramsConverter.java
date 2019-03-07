package com.siit.thebigproject.service;

public class unitToGramsConverter {

    private double convertedValue;

    public double getConvertedValue() {
        return convertedValue;
    }

    public void converToGrams(String unit) {

        switch (unit) {
            case "":
                convertedValue = 50;
                break;
            case "teaspoons":
            case "teaspoon":
                convertedValue = 5;
                break;
            case "grams":
            case "gms":
                convertedValue = 1;
                break;
            case "large":
            case "larges":
            case "extra large":
            case "extra larges":
                convertedValue = 100;
                break;
            case "Tbsps":
            case "Tbsp":
            case "Tbs":
            case "tb":
            case "Tb":
                convertedValue = 10;
                break;
            case "servings":
            case "serving":
                convertedValue = 50;
                break;
            case "6-inchs":
                convertedValue = 100;
                break;
            case "9-inchs":
                convertedValue = 150;
                break;
            case "cloves":
            case "clove":
                convertedValue = 5;
                break;
            case "leave":
            case "leaves":
                convertedValue = 5;
                break;
            case "Stick":
                convertedValue = 5;
                break;
            case "kgs":
            case "kilograms":
                convertedValue = 1000;
                break;
            case "pinch":
            case "pinches":
                convertedValue = 2;
                break;
            case "handful":
            case "Handful":
                convertedValue = 100;
                break;
            case "mediums":
            case "medium":
                convertedValue = 75;
                break;
            case "milliliters":
                convertedValue = 1;
                break;
            case "smalls":
                convertedValue = 1;
                break;
            case "box":
                convertedValue = 200;
                break;
            case "Dash":
            case "dash":
                convertedValue = 2;
                break;
            case "bottles":
                convertedValue = 2;
                break;
            case "strips":
                convertedValue = 10;
                break;
            case "drops":
                convertedValue = 1;
                break;
            case "slices":
            case "Slices":
                convertedValue = 25;
                break;
            case "head":
                convertedValue = 500;
                break;
            case "sheets":
                convertedValue = 100;
                break;
            case "sprig":
                convertedValue = 100;
                break;
            case "stalks":
                convertedValue = 100;
                break;
            case "ball":
                convertedValue = 100;
                break;
            case "small bunches":
            case "bunches":
            case "bunch":
                convertedValue = 100;
                break;
            case "small can":
                convertedValue = 50;
                break;
            case "inches":
            case "inch":
                convertedValue = 50;
                break;
            case "pint":
                convertedValue = 50;
                break;
            case "Loaf":
                convertedValue = 100;
                break;
        }

    }
}
