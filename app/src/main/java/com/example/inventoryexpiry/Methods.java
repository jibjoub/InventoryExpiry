/**
 * @file Methods.java
 * @author Jean-Baptiste Despujol
 * @date 05/25/2020
 * @brief File containing the different methods that can be unit tested
 */

package com.example.inventoryexpiry;

import java.util.ArrayList;
import java.util.Collections;

public class Methods {
    /** Add a new product to a list or replace the expiry date if the GTIN already exists
     @param list ArrayList of Products
     @param entry new Product to maybe add to the list
     @return list return the modified list
     */
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
