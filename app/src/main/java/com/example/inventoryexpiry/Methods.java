package com.example.inventoryexpiry;

import java.util.ArrayList;
import java.util.Collections;

public class Methods {
    public static ArrayList<Product> addToList(ArrayList<Product> list, Product entry) {
        String entry_gtin = entry.getGTIN();
        for (Product p : list) {
            if (p.getGTIN().equals(entry_gtin)) {
                p.setExpiryDate(entry.getExpiryDate());
                Collections.sort(list);
                return list;
            }
        }
        list.add(entry);
        Collections.sort(list);
        return list;
    }
}
