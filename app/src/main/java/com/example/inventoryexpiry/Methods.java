package com.example.inventoryexpiry;

import java.util.ArrayList;

public class Methods {
    public static ArrayList<Product> addToList(ArrayList<Product> list, Product entry) {
        String entry_gtin = entry.getGTIN();
        for (Product p : list) {
            if (p.getGTIN().equals(entry_gtin)) {
                p.setExpiryDate(entry.getExpiryDate());
                return list;
            }
        }
        list.add(entry);
        return list;
    }
}
