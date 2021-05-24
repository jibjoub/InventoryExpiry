package com.example.inventoryexpiry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.util.Log.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddProductActivity();
            }
        });



        ListView products = (ListView) findViewById(R.id.product_list);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Product tomate = new Product(formatter.format(new Date()), "12345444444444");
        Product jambon = new Product(formatter.format(new Date()),"12345444444444" );


        list.add(tomate);
        list.add(jambon);


        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.list_adapter, list);
        products.setAdapter(adapter);
    }
    public void openAddProductActivity() {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        final String TAG = AddProductActivity.class.getSimpleName();
        super.onResume();
        Intent intent = getIntent();
        Log.d(TAG, "Y a t il un intent : " + intent);
        if (intent.getAction()!=Intent.ACTION_MAIN) {
           final Product p = (Product) intent.getSerializableExtra("product");
            list.add(p);
        }
    }
}