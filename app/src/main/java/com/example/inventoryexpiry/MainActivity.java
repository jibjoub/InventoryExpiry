package com.example.inventoryexpiry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView products = (ListView) findViewById(R.id.product_list);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Product tomate = new Product(formatter.format(new Date()), 12345678923L);
        Product jambon = new Product(formatter.format(new Date()), 12345678910L);
        Product huile = new Product(formatter.format(new Date()), 123456888888L);
        Product carotte = new Product(formatter.format(new Date()), 1234999999L);
        Product orange = new Product(formatter.format(new Date()), 123456111111L);
        Product lardon = new Product(formatter.format(new Date()), 123452222222L);
        Product poulet = new Product(formatter.format(new Date()), 12345444444444L);

        ArrayList<Product> list = new ArrayList<>();

        list.add(tomate);
        list.add(jambon);
        list.add(huile);
        list.add(carotte);
        list.add(orange);
        list.add(lardon);
        list.add(poulet);

        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.list_adapter, list);
        products.setAdapter(adapter);
    }
}