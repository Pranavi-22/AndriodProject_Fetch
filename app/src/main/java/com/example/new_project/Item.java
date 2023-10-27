package com.example.new_project;

// This class represents an Item with id, listId, and name attributes.
public class Item {
    // Private variables to hold id, listId, and name.
    private int id;
    private int listId;
    private String name;

    // Constructor to initialize id, listId, and name.
    public Item(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    // Getter method for id.
    public int getId() {
        return id;
    }

    // Getter method for listId.
    public int getListId() {
        return listId;
    }

    // Getter method for name.
    public String getName() {
        return name;
    }

    // Override the toString method to provide a meaningful string representation of the Item.
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                '}';
    }
}
