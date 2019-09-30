package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button _button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        //Our button
        _button = (Button) findViewById(R.id.button);
        _button.setOnClickListener(new View.OnClickListener(){

        @Override
                public void onClick(View view) {
            //onClick
        }
       });
    }
}
