package com.example.inventoryexpiry;

import java.io.Serializable;

public class Product implements Serializable, Comparable<Product> {
    private String expiryDate;
    private String GTIN;

    public Product(String expiryDate, String GTIN) {
        this.expiryDate = expiryDate;
        this.GTIN = GTIN;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getGTIN() {
        return GTIN;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int compareTo(Product p) {
        String[] left = expiryDate.split("/");
        String[] right = p.expiryDate.split("/");
        if (left[2].compareTo(right[2]) != 0)
            return left[2].compareTo(right[2]);
        if (left[1].compareTo(right[1]) != 0)
            return left[1].compareTo(right[1]);
        return left[0].compareTo(right[0]);
    }
}
