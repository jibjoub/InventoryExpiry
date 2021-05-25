/**
 * @file Methods.java
 * @author Jean-Baptiste Despujol
 * @date 05/25/2020
 * @brief File containing the different methods that can be unit tested
 */

package com.example.inventoryexpiry;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Methods {

    enum Time {
        CRITICAL,
        NEAR,
        MEDIUM,
        FAR
    }

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

    public static Time remainingTime(String date) {

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);

        String[] splitted = date.split("/");

        int numberOfDayRemaining = 0;
        numberOfDayRemaining = numberOfDayRemaining + (Integer.parseInt(splitted[0]) - dayOfMonth);
        numberOfDayRemaining = numberOfDayRemaining + (30 * (Integer.parseInt(splitted[1]) - month));
        numberOfDayRemaining = numberOfDayRemaining + (365 * (Integer.parseInt(splitted[2]) - year));

        if (numberOfDayRemaining < 3)
            return Time.CRITICAL;
        if (numberOfDayRemaining < 7)
            return Time.NEAR;
        if (numberOfDayRemaining < 30)
            return Time.MEDIUM;
        else
            return Time.FAR;
    }
}
