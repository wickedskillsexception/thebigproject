package com.siit.thebigproject.domain;

import org.springframework.stereotype.Component;

@Component
public class Ingredient extends ObjectId {

    private String name;

    private String pictureUrl;

    @Override
    public String toString() {
        return name;
    }

    public Ingredient() {
    }

    public Ingredient(int id, String name, String pictureUrl) {
        setId(id);
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Ingredient set(int id, String ingredientName, String ingredientURL) {
        setId(id);
        this.name = name;
        this.pictureUrl = pictureUrl;

        return this;
    }
}
