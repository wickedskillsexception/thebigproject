package com.siit.thebigproject.domain;

public class Role extends ObjectId {

    private String name;

    public String getRoleName() {
        return name;
    }

    public void setRoleName(String roleName) {
        this.name = roleName;
    }
}
