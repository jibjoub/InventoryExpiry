package com.example.inventoryexpiry;

import junit.framework.TestCase;

import java.util.ArrayList;

public class MethodsTest extends TestCase {

    public void testAddToList() {
        ArrayList<Product> list = new ArrayList<>();
        Product p1 = new Product("12/08/2022", "1234567891234");
        Product p2 = new Product("12/08/2021", "1234567891234");
        Methods.addToList(list, p1);
        Methods.addToList(list, p2);
        assertEquals(list.toArray().length, 1);
        assertEquals(list.get(0).getExpiryDate(), "12/08/2021");
    }
}