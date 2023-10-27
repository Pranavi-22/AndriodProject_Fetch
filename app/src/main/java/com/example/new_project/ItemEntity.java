package com.example.new_project;


// This is a simple class representing an entity with id and name attributes.
public class ItemEntity {
    // Variables to hold id and name.
    String id;
    String name;

    // Constructor with parameters to initialize id and name.
    public ItemEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Default constructor. No initialization.
    public ItemEntity() {
    }

    // Getter method for id.
    public String getId() {
        return id;
    }

    // Setter method for id.
    public void setId(String id) {
        this.id = id;
    }

    // Getter method for name.
    public String getName() {
        return name;
    }

    // Setter method for name.
    public void setName(String name) {
        this.name = name;
    }
}

