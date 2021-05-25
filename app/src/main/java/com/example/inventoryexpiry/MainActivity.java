/**
 * @file MainActivity.java
 * @author Jean-Baptiste Despujol
 * @date 05/25/2020
 * @brief File containing the logic behind the main activity that displays the list of products
 *        with their expiry date
 */

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

    public static ArrayList<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize the button that opens the AddProductActivity
        FloatingActionButton add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddProductActivity();
            }
        });


        //Initialize the listview that will display the products with their expiry date
        ListView products = (ListView) findViewById(R.id.product_list);

        //Link the adapter to the listview in order to format it
        ProductListAdapter adapter = new ProductListAdapter(this, R.layout.list_adapter, list);
        products.setAdapter(adapter);
    }
    //Opens the AddProductActivity with a simple intent
    public void openAddProductActivity() {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    //Override of the onResume method in order to check if the main activity is resumed from the
    //launch of the app or resumed after we created a new entry in the addproduct activity
    @Override
    protected void onResume() {
        final String TAG = AddProductActivity.class.getSimpleName();
        super.onResume();
        Intent intent = getIntent();
        //Log.d(TAG, "Y a t il un intent : " + intent);

        //If we resumed the main activity from the addproduct activity, we add the new Product to the list
        if (intent.getAction()!=Intent.ACTION_MAIN) {
           final Product p = (Product) intent.getSerializableExtra("product");
           list = Methods.addToList(list, p);
        }
    }

}