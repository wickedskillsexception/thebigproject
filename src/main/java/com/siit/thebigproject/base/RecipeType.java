package com.siit.thebigproject.base;

public enum RecipeType {

    VEGETARIAN("Vegetarian"), VEGAN("Vegan"), GLUTENFREE("Gluten Free"), DAIRYFREE("Dairy Free"), VERYHEALTHY("Very Healthy"), CHEAP("Cheap"), VERYPOPULAR("Very Popular"), SUSTEINABLE("Susteinable"), LOWCAL("Low Calories"), COMFORT_FOOD("Comfort Food"), LOWFOODMAP("LowFoodMap");    //add more here

    private String recipeTypeValue;

    RecipeType(String recipeTypeValue) {
        this.recipeTypeValue = recipeTypeValue;
    }

    public String getRecipeTypeValue() {
        return recipeTypeValue;
    }
}
