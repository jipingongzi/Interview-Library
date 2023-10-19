package org.sean.library.model;

import org.sean.library.util.LogUtil;

public class Book {
    private String name;
    private String author;
    private Integer inventory;
    private Integer availableInventory;

    public Book(String name, String author, Integer inventory) {
        this.name = name;
        this.author = author;
        this.inventory = inventory;
        this.availableInventory = inventory;
    }

    public void addInventory(Integer inventory){
        this.inventory = this.inventory + inventory;
    }

    public Boolean borrowBook(){
        if(this.availableInventory > 0) {
            this.availableInventory = this.availableInventory - 1;
            return true;
        } else {
            LogUtil.log("No available Inventory.");
            return false;
        }
    }

    public void returnBook(){
        this.availableInventory = this.availableInventory + 1;
    }

    public String getKey(){
        return name + ":" + author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getInventory() {
        return inventory;
    }

    public Integer getAvailableInventory() {
        return availableInventory;
    }
}
