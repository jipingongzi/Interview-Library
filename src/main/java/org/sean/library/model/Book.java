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
    }

    public void addInventory(Integer inventory){
        this.inventory = this.inventory + inventory;
    }

    public void borrowBook(){
        if(this.availableInventory > 0) {
            this.availableInventory = this.availableInventory - 1;
        } else {
            LogUtil.log("No available Inventory.");
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
