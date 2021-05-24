package com.example.inventoryexpiry;

import java.util.Date;

public class Product {
    private Date expiryDate;
    private long GTIN;

    public Product(Date expiryDate, long GTIN) {
        this.expiryDate = expiryDate;
        this.GTIN = GTIN;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public long getGTIN() {
        return GTIN;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
