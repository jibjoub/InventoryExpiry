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

    //Enum class representing different states of days remaining before the expiry date
    enum Time {
        EXPIRED, // < 0
        CRITICAL, // >= 0 and < 4
        NEAR, // >= 4 and < 8
        MEDIUM, // >=8 and < 30
        FAR // > 30
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

    /** Evaluate the time remaining before the expiry date
     @param date date to evaluate
     @return time return the time remaining evaluation
     */
    public static Time remainingTime(String date) {

        //Get the current date
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);

        String[] splitted = date.split("/");

        //Calculate the number of days of difference
        int numberOfDayRemaining = 0;
        numberOfDayRemaining = numberOfDayRemaining + (Integer.parseInt(splitted[0]) - dayOfMonth);
        numberOfDayRemaining = numberOfDayRemaining + (30 * (Integer.parseInt(splitted[1]) - month));
        numberOfDayRemaining = numberOfDayRemaining + (365 * (Integer.parseInt(splitted[2]) - year));

        if (numberOfDayRemaining < 0)
            return Time.EXPIRED;
        if (numberOfDayRemaining < 4)
            return Time.CRITICAL;
        if (numberOfDayRemaining < 8)
            return Time.NEAR;
        if (numberOfDayRemaining < 30)
            return Time.MEDIUM;
        else
            return Time.FAR;
    }
}
