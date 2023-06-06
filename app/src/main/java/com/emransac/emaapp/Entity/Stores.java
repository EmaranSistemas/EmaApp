package com.emransac.emaapp.Entity;

public class Stores {
    String id,name;
    public Stores() {
    }

    public Stores(String id, String name){
        this.id = id;
        this.name = name;
    }
    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }
}
