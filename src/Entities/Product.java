package Entities;

import java.util.UUID;

public class Product {
    private final UUID id;
    private Amount perUnitPrice;
    private String name;

    public Product(Amount perUnitPrice, String name) {
        this.perUnitPrice = perUnitPrice;
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public Amount getPerUnitPrice() {
        return perUnitPrice;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
