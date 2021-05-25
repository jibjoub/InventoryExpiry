/**
 * @file Product.java
 * @author Jean-Baptiste Despujol
 * @date 05/25/2020
 * @brief File containing the class representing a product
 */

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

    //Override the compareTo method to use the Collection.sort on ArrayList<Product>
    @Override
    public int compareTo(Product p) {
        String[] left = expiryDate.split("/");
        String[] right = p.expiryDate.split("/");
        int yeardiff = Integer.parseInt(left[2]) - Integer.parseInt(right[2]);
        int monthdiff = Integer.parseInt(left[1]) - Integer.parseInt(right[1]);
        int daydiff = Integer.parseInt(left[0]) - Integer.parseInt(right[0]);
        if (yeardiff != 0)
            return yeardiff;
        if (monthdiff != 0)
            return monthdiff;
        return daydiff;
    }
}
