package com.siit.thebigproject.domain;

public class RecipeType extends ObjectId {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /*    VEGETARIAN("Vegetarian"), VEGAN("Vegan"), GLUTENFREE("Gluten Free"), DAIRYFREE("Dairy Free"), VERYHEALTHY("Very Healthy"), CHEAP("Cheap"), VERYPOPULAR("Very Popular"), SUSTAINABLE("Sustainable"), LOWCAL("Low Calories"), COMFORT_FOOD("Comfort Food"), LOWFOODMAP("LowFoodMap");    //add more here

    private String recipeTypeValue;

    RecipeType(String recipeTypeValue) {
        this.recipeTypeValue = recipeTypeValue;
    }

    public String getRecipeTypeValue() {
        return recipeTypeValue;
    }*/
}
