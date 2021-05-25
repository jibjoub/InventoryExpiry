package com.example.inventoryexpiry;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class MethodsTest extends TestCase {

    ArrayList<Product> list = new ArrayList<>();
    Product p1 = new Product("12/8/2022", "1234567891234");
    Product p2 = new Product("12/8/2021", "1234567891234");
    Product p3 = new Product("11/8/2021", "9876543210987");
    Product p4 = new Product("12/7/2021", "9876543210673");
    Product p5 = new Product("4/8/2021", "9876543212312");
    Product p6 = new Product("21/1/2021", "9876543211342");

    public void testAddToListIsReplaced() {
        Methods.addToList(list, p1);
        Methods.addToList(list, p2);
        assertEquals(list.toArray().length, 1);
        assertEquals(list.get(0).getExpiryDate(), "12/8/2021");
    }


    public void testAddToListDifferentEntries() {
        Methods.addToList(list, p1);
        Methods.addToList(list, p3);
        assertEquals(list.toArray().length, 2);
    }

    public void testSort() {
        Methods.addToList(list, p1);
        Methods.addToList(list, p3);
        Methods.addToList(list, p4);
        Methods.addToList(list, p5);
        Methods.addToList(list, p6);
        Collections.sort(list);
        assertEquals(list.get(0).getExpiryDate(), "21/1/2021");
    }

    public void testRemainingTimeMedium() {
        Methods.Time time = Methods.remainingTime("21/6/2021");
        assertEquals(Methods.Time.MEDIUM, time);
    }

    public void testRemainingTimeCritical() {
        Methods.Time time = Methods.remainingTime("27/5/2021");
        assertEquals(Methods.Time.CRITICAL, time);
    }

    public void testRemainingTimeNear() {
        Methods.Time time = Methods.remainingTime("30/5/2021");
        assertEquals(Methods.Time.NEAR, time);
    }

    public void testRemainingTimeFar() {
        Methods.Time time = Methods.remainingTime("27/8/2021");
        assertEquals(Methods.Time.FAR, time);
    }

    public void testRemainingTimeExpired() {
        Methods.Time time = Methods.remainingTime("27/9/2020");
        assertEquals(Methods.Time.EXPIRED, time);
    }

}