package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button secondActivityButton;
    Button sendRequestButton;
    public static Button buttonCurrencyOne;
    public static Button buttonCurrencyTwo;

    TextView title;
    TextView bodyText;

    private ModelPost publication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        buttonCurrencyOne = (Button) findViewById(R.id.buttonCurrencyOne);
        buttonCurrencyTwo = (Button) findViewById(R.id.buttonCurrencyTwo);

        title = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);

        //ChangeActivity Button
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        buttonCurrencyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);
                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyOne");

                startActivity(intent);
            }
        });

        buttonCurrencyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);

                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyTwo");

                startActivity(intent);
            }
        });
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };

    private void sendRequest(){
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();
    }

    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publication != null){
                    title.setText(publication.getTitle());
                    bodyText.setText(publication.getBodyText());
                } else{
                    title.setText("");
                    bodyText.setText("");
                }
            }
        });
    }

    @Override
    public void success(ModelPost publication) {
        this.publication = publication;
        updatePublication();
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        updatePublication();
    }

}
