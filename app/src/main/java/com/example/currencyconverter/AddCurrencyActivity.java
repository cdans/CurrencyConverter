package com.example.currencyconverter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCurrencyActivity extends AppCompatActivity {

    private Button submitButton;
    public static Button setCompareCurrencyButton;

    private EditText name;
    private EditText comparison;

    public static TextView textCompareCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_currency);

        submitButton = (Button) findViewById(R.id.submitButton);
        setCompareCurrencyButton = (Button) findViewById(R.id.setCompareCurrencyButton);

        name = (EditText) findViewById(R.id.editName);
        comparison = (EditText) findViewById(R.id.editComparison);

        textCompareCurrency = (TextView) findViewById(R.id.textCompareCurrency);

        setCompareCurrencyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCurrencyActivity.this, SetCompareCurrency.class);

                startActivity(intent);

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {

                String strname = name.getText().toString();
                String strUnit = textCompareCurrency.getText().toString();


                if (strname.isEmpty() || strUnit.isEmpty() || setCompareCurrencyButton.getText()=="Set compare currency"){

                    Toast.makeText(AddCurrencyActivity.this, "Please edit everything before submitting.", Toast.LENGTH_SHORT).show();
                }
                else{

                    HomeActivity.items.add(new ListItem(strname, R.drawable.comingsoon,  "Empty"));

                    Intent intent = new Intent(AddCurrencyActivity.this, SecondActivity.class);

                    startActivity(intent);
                }

            }
        });


    }
}
