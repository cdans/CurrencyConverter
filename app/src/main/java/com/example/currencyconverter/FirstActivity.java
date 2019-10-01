package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    private Button myButton;
    private TextView mytextField;
    private Button secondactivtyButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivitydesign);

        myButton = (Button) findViewById(R.id.button);
        secondactivtyButton = (Button) findViewById(R.id.secondActivityButton);
        mytextField = (TextView) findViewById(R.id.textfield);

        myButton.setOnClickListener(myButtonClick);
        secondactivtyButton.setOnClickListener(startSecondActivity);
        secondactivtyButton.setOnLongClickListener(startSecondActivityLong);
    }

    View.OnClickListener myButtonClick = new View.OnClickListener() {
        @Override
        public void onClick (View v){
            mytextField.setText(mytextField.getText() + "\n" + "Next line");
        }
    };

    public void runSecondActivity (boolean b){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("flag", b);
        context.startActivity(intent);
    }

    View.OnClickListener startSecondActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            runSecondActivity(true);
        }
    };

    View.OnLongClickListener startSecondActivityLong = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            runSecondActivity(false);
            return true;
        }
    };

}
