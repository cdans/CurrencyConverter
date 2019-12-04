package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button secondActivityButton;
    Button sendRequestButton;
    public static Button buttonCurrencyOne;
    public static Button buttonCurrencyTwo;

    public static ListItem currencyOne = HomeActivity.items.get(0);
    public static ListItem currencyTwo = HomeActivity.items.get(1);

    public static EditText valueCurrencyOne;
    public static EditText valueCurrencyTwo;

    public static TextView currencyText;

    private IndicatingView indicator;

    public static ProgressBar progressBar;

    public static TextView tfPosts;


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

        valueCurrencyOne = (EditText) findViewById(R.id.valueCurrencyOne);
        valueCurrencyOne.setText("1");
        valueCurrencyTwo = (EditText) findViewById(R.id.valueCurrencyTwo);
        Double rateUSD = Math.round(100.0 * HomeActivity.items.get(1).getRate()) / 100.0;
        valueCurrencyTwo.setText(rateUSD.toString());

        currencyText = (TextView) findViewById(R.id.currencyText);
        currencyText.setText("1 Euro equals " + rateUSD.toString() + " US dollar");


        indicator = (IndicatingView) findViewById(R.id.generated_graphic);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        tfPosts = (TextView) findViewById(R.id.numberItemsJSON);
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
                /*Drawable myDrawable = getResources().getDrawable(R.drawable.dollar);
                int h = myDrawable.getIntrinsicHeight();
                int w = myDrawable.getIntrinsicWidth();
                myDrawable.setBounds( 0 , 0, 0, 0 );
                buttonCurrencyOne.setCompoundDrawables(null, null, null, null);*/

                startActivity(intent);
            }
        });


        buttonCurrencyTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ChooseCurrency.class);

                intent.putExtra("EXTRA_BUTTON", "buttonCurrencyTwo");

               /* Drawable myDrawable = getResources().getDrawable(R.drawable.dollar);
                int h = myDrawable.getIntrinsicHeight();
                int w = myDrawable.getIntrinsicWidth();
                myDrawable.setBounds( 0 , 0, 0, 0 );
                buttonCurrencyTwo.setCompoundDrawables(null, null, null, null);*/
                startActivity(intent);
            }
        });

        valueCurrencyOne.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthValueCurrencyOne = valueCurrencyOne.getText().toString().trim().length();
                if(valueCurrencyOne.hasFocus() && lengthValueCurrencyOne != 0){
                        Double conversion = currencyConversion(Double.parseDouble(s.toString()), currencyOne, currencyTwo);
                        valueCurrencyTwo.setText(conversion.toString());
                    }
                else if (valueCurrencyOne.hasFocus() && lengthValueCurrencyOne == 0){
                    valueCurrencyTwo.setText("");
                }
            }
        });

        valueCurrencyTwo.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthValueCurrencyTwo = valueCurrencyTwo.getText().toString().trim().length();
                if(valueCurrencyTwo.hasFocus() && lengthValueCurrencyTwo != 0){
                        Double conversion = currencyConversion(Double.parseDouble(s.toString()), currencyTwo, currencyOne);
                        valueCurrencyOne.setText(conversion.toString());
                }
                else if (valueCurrencyTwo.hasFocus() && lengthValueCurrencyTwo == 0){
                    valueCurrencyOne.setText("");
                }
            }
        });
    }


    public static Double currencyConversion (Double amount, ListItem currencyOne, ListItem currencyTwo){

        Double converted;

        if (currencyOne.getCode()=="EUR"){
            converted = amount*currencyTwo.getRate();
        }
        else if (currencyTwo.getCode()=="EUR"){
            converted = amount/currencyOne.getRate();
        }
        else {
            converted = amount/currencyOne.getRate();
            converted = converted*currencyTwo.getRate();
        }
        converted = Math.round(100.0 * converted) / 100.0;
        return converted;
    }



    @Override
    public void addPostsNumber(int countPosts) {
        tfPosts.setText(countPosts + " posts");

    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };

    private void sendRequest(){
        setIndicatorStatus(IndicatingView.SUCCESS);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alphaview);
        indicator.startAnimation(animation1);

        progressBar.setVisibility(View.VISIBLE);

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
                    title.setText("FAILED");
                    bodyText.setText("");
                }

                indicator.clearAnimation();
            }
        });
    }

    @Override
    public void success(ModelPost publication) {
        this.publication = publication;
        updatePublication();
        setIndicatorStatus(IndicatingView.SUCCESS);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        updatePublication();
        setIndicatorStatus(IndicatingView.FAILED);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void setIndicatorStatus (final int status){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
            }
        });
    }
}
