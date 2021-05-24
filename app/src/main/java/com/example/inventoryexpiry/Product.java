package com.example.inventoryexpiry;

import java.io.Serializable;

public class Product implements Serializable {
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
}
