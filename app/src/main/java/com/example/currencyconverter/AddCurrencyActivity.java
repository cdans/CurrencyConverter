package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCurrencyActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText name;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_currency);
        submitButton = (Button) findViewById(R.id.submitButton);
        name = (EditText) findViewById(R.id.editName);
        description = (EditText) findViewById(R.id.editDescription);

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String strname = name.getText().toString();
                String strdescription = description.getText().toString();

                HomeActivity.items.add(new ListItem(strname, R.drawable.comingsoon,  strdescription));

                Intent intent = new Intent(AddCurrencyActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });


    }
}
