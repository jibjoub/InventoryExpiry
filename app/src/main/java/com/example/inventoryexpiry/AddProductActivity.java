/**
 * @file AddProductActivity.java
 * @author Jean-Baptiste Despujol
 * @date 05/25/2020
 * @brief File containing the logic behind the activity that sends
 *        to the main activity a new entry in the list of products with their expiry date
 */

package com.example.inventoryexpiry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class AddProductActivity extends AppCompatActivity {

    //Declare all the interactive part of the activity
    private TextView dateView;
    private DatePickerDialog.OnDateSetListener DateSetListener;
    EditText GTIN;
    Button validerButton;
    TextView instructions;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = AddProductActivity.class.getSimpleName();
        //Log.d(TAG, "do we make to here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //Link all the interactive part with their resources counterparts
        instructions = findViewById(R.id.howToAdd);
        dateView = (TextView) findViewById(R.id.add_product_date);
        GTIN = (EditText) findViewById(R.id.editTextGTIN);
        validerButton = (Button) findViewById(R.id.valider_button);

        //Typeface nunito_font = Typeface.createFromAsset(getAssets(), "fonts.Nunito/nunito.ttf");
        //instructions.setTypeface(nunito_font);

        //Disable it, it will need to check certain conditions to be enabled
        validerButton.setEnabled(false);

        //Link the textWatcher with the editText
        GTIN.addTextChangedListener(textWatcher);

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

        //Validate the date entered
        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                dateView.setText(dayOfMonth + "/" + month + "/" + year);
            }
        };


        //Create an intent to go back with the main activity with an extra Product object newly created
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

    //Create a new TextWatcher object to check condition after each time the text in GTIN editText is changed
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //Enabled the validerButton only when GTIN text is a 13 long number and the date is not empty
        @Override
        public void afterTextChanged(Editable s) {
            final String TAG = AddProductActivity.class.getSimpleName();
            Log.d(TAG, "length of editText :" + s.length());
            if (GTIN.getText().toString().matches("^[0-9]{13}$") && dateView.getText().length() > 0) {
                GTIN.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.keppel));
                validerButton.setEnabled(true);
            }
            else {
                GTIN.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.ships_officer));
                validerButton.setEnabled(false);
            }
    }

    };
}