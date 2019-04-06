package com.siit.thebigproject.domain;

public class Ingredient extends ObjectId {

    private String name;

    private String pictureUrl;

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

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
