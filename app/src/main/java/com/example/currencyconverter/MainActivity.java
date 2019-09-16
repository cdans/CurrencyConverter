package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button _button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Our button
        _button = (Button) findViewById(R.id.button);
        _button.setOnClickListener(new View.OnClickListener();

        @Override
                public void onClick(View view) {
            //onClick
        }
       });
    }
}
