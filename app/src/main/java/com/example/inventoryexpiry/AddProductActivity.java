package com.example.inventoryexpiry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class AddProductActivity extends AppCompatActivity {

    private TextView dateView;
    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dateView = (TextView) findViewById(R.id.add_product_date);
        EditText GTIN = (EditText) findViewById(R.id.editTextGTIN);
        Button validerButton = (Button) findViewById(R.id.valider_button);

        //Getting the data about the date with a scrolling selection
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddProductActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        DateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        //When validating the date entered
        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                dateView.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };

        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product(dateView.getText().toString(), GTIN.getText().toString());
                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                intent.putExtra("product", p);
                startActivity(intent);
            }
        });




    }
}