package com.example.inventoryexpiry;

import java.util.Date;

public class Product {
    private String expiryDate;
    private long GTIN;

    public Product(String expiryDate, long GTIN) {
        this.expiryDate = expiryDate;
        this.GTIN = GTIN;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public long getGTIN() {
        return GTIN;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
