package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button secondActivityButton;
    public static Button sendRequestButton;
    public static Button buttonCurrencyOne;
    public static Button buttonCurrencyTwo;
    Button lab3;


    public static List<Currency> currencies = HomeActivity.createListC();

    public static Currency eur = HomeActivity.getCurrencyByCode("EUR", currencies);
    public static int posEur = currencies.indexOf(eur);
    public static Currency currencyOne = currencies.get(posEur);

    public static Currency usd = HomeActivity.getCurrencyByCode("USD", currencies);
    public static int posUsd = currencies.indexOf(usd);
    public static Currency currencyTwo = currencies.get(posUsd);

    public static EditText valueCurrencyOne;
    public static EditText valueCurrencyTwo;


    public static TextView currencyText;


    public static ProgressBar progressBar;

    Button oneIcon;
    Button twoIcon;
    Button threeIcon;
    Button fourIcon;
    Button fiveIcon;
    Button sixIcon;
    Button sevenIcon;
    Button eightIcon;
    Button nineIcon;
    Button zeroIcon;

    Button clearIcon;
    Button commaIcon;

    Button addIcon;
    Button minusIcon;
    Button divisionIcon;
    Button multiIcon;

    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';

    private double val1 = Double.NaN;
    private double val2;

    private char ACTION;
    private final char EQU = 0;

    public static Button resultButton;


    public static Button resultTo;

    TextView calculation;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        lab3 = (Button) findViewById(R.id.lab3);


        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        buttonCurrencyOne = (Button) findViewById(R.id.buttonCurrencyOne);
        buttonCurrencyTwo = (Button) findViewById(R.id.buttonCurrencyTwo);

        valueCurrencyOne = (EditText) findViewById(R.id.valueCurrencyOne);
        valueCurrencyOne.setText("1");
        valueCurrencyTwo = (EditText) findViewById(R.id.valueCurrencyTwo);
        Double rateUSD = Math.round(100.0 * currencies.get(posUsd).getRate()) / 100.0;
        valueCurrencyTwo.setText(rateUSD.toString());

        currencyText = (TextView) findViewById(R.id.currencyText);
        currencyText.setText("1 Euro equals " + rateUSD.toString() + " US dollar");


        oneIcon = (Button) findViewById(R.id.oneIcon);
        twoIcon = (Button) findViewById(R.id.twoIcon);
        threeIcon = (Button) findViewById(R.id.threeIcon);
        fourIcon = (Button) findViewById(R.id.fourIcon);

        fiveIcon = (Button) findViewById(R.id.fiveIcon);
        sixIcon = (Button) findViewById(R.id.sixIcon);
        sevenIcon = (Button) findViewById(R.id.sevenIcon);
        eightIcon = (Button) findViewById(R.id.eightIcon);

        nineIcon = (Button) findViewById(R.id.nineIcon);
        zeroIcon = (Button) findViewById(R.id.zeroIcon);
        clearIcon = (Button) findViewById(R.id.clearIcon);
        commaIcon = (Button) findViewById(R.id.commaIcon);

        addIcon = (Button) findViewById(R.id.addIcon);
        minusIcon = (Button) findViewById(R.id.minusIcon);
        divisionIcon = (Button) findViewById(R.id.divisionIcon);
        multiIcon = (Button) findViewById(R.id.multiIcon);

        resultButton = (Button) findViewById(R.id.resultBtn);

        resultTo = (Button) findViewById(R.id.resultTo);


        calculation = (TextView) findViewById(R.id.calculation);
        result = (TextView) findViewById(R.id.result);


        oneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "1");
            }
        });
        twoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "2");

            }
        });
        threeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "3");
            }
        });
        fourIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "4");
            }
        });

        fiveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "5");
            }
        });
        sixIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "6");
            }
        });
        sevenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "7");
            }
        });
        eightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation.setText(calculation.getText().toString() + "8");

            }
        });
        nineIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "9");
            }
        });
        zeroIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calculation.setText(calculation.getText().toString() + "0");
            }
        });

        clearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calculation.getText().length() > 0) {
                    CharSequence name = calculation.getText().toString();
                    calculation.setText(name.subSequence(0, name.length() - 1));
                } else {
                    val1 = Double.NaN;
                    val2 = Double.NaN;
                    calculation.setText(null);
                    result.setText(null);
                }
            }
        });

        clearIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                val1 = Double.NaN;
                val2 = Double.NaN;
                calculation.setText(null);
                result.setText(null);

                setEnableNumbersAndComma(true);

                return true;
            }
        });

        commaIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!calculation.getText().toString().contains(".")) {
                    calculation.setText(calculation.getText().toString() + ".");
                }

            }
        });

        addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calculation.getText() != "" || result.getText().toString().contains("=")) {
                    compute();
                    ACTION = ADDITION;
                    result.setText(String.valueOf(val1) + "+");
                    calculation.setText(null);

                    setEnableNumbersAndComma(true);

                }

            }
        });
        minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calculation.getText() != "" || result.getText().toString().contains("=")) {
                    compute();
                    ACTION = SUBTRACTION;
                    result.setText(String.valueOf(val1) + "-");
                    calculation.setText(null);

                    setEnableNumbersAndComma(true);

                }
            }
        });
        multiIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calculation.getText() != "" || result.getText().toString().contains("=")) {
                    compute();
                    ACTION = MULTIPLICATION;
                    result.setText(String.valueOf(val1) + "*");
                    calculation.setText(null);

                    setEnableNumbersAndComma(true);

                }
            }
        });
        divisionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calculation.getText() != "" || result.getText().toString().contains("=")) {
                    compute();
                    ACTION = DIVISION;
                    result.setText(String.valueOf(val1) + "/");
                    calculation.setText(null);

                    setEnableNumbersAndComma(true);

                }

            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (calculation.getText() != "" && !result.getText().toString().contains("=")) {
                    compute();
                    ACTION = EQU;
                    result.setText(result.getText().toString() + String.valueOf(val2) + " = " + String.valueOf(val1));
                    calculation.setText(String.valueOf(val1));

                    setEnableNumbersAndComma(false);
                }
            }
        });

        resultTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (result.getText().toString() != "") {

                    valueCurrencyOne.setText(String.valueOf(val1));
                    valueCurrencyTwo.setText(currencyConversion(Double.parseDouble(String.valueOf(val1)), currencyOne, currencyTwo).toString());

                }
            }
        });


        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        //ChangeActivity Button
        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);
            }
        });

        lab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Lab3.class);

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


                startActivity(intent);
            }
        });

        valueCurrencyOne.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthValueCurrencyOne = valueCurrencyOne.getText().toString().trim().length();
                if (valueCurrencyOne.hasFocus() && lengthValueCurrencyOne != 0) {
                    Double conversion = currencyConversion(Double.parseDouble(s.toString()), currencyOne, currencyTwo);
                    valueCurrencyTwo.setText(conversion.toString());
                } else if (valueCurrencyOne.hasFocus() && lengthValueCurrencyOne == 0) {
                    valueCurrencyTwo.setText("");
                }
            }
        });

        valueCurrencyTwo.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lengthValueCurrencyTwo = valueCurrencyTwo.getText().toString().trim().length();
                if (valueCurrencyTwo.hasFocus() && lengthValueCurrencyTwo != 0) {
                    Double conversion = currencyConversion(Double.parseDouble(s.toString()), currencyTwo, currencyOne);
                    valueCurrencyOne.setText(conversion.toString());
                } else if (valueCurrencyTwo.hasFocus() && lengthValueCurrencyTwo == 0) {
                    valueCurrencyOne.setText("");
                }
            }
        });
    }


    private void setEnableNumbersAndComma(Boolean bool) {
        zeroIcon.setEnabled(bool);
        oneIcon.setEnabled(bool);
        twoIcon.setEnabled(bool);
        threeIcon.setEnabled(bool);
        fourIcon.setEnabled(bool);
        fiveIcon.setEnabled(bool);
        sixIcon.setEnabled(bool);
        sevenIcon.setEnabled(bool);
        eightIcon.setEnabled(bool);
        nineIcon.setEnabled(bool);

        commaIcon.setEnabled(bool);

    }

    private void compute() {
        if (!Double.isNaN(val1)) {
            val2 = Double.parseDouble(calculation.getText().toString());

            switch (ACTION) {
                case ADDITION:
                    val1 = val1 + val2;
                    break;
                case SUBTRACTION:
                    val1 = val1 - val2;
                    break;
                case MULTIPLICATION:
                    val1 = val1 * val2;
                    break;
                case DIVISION:
                    val1 = val1 / val2;
                    break;
                case EQU:
                    break;
            }
        } else {
            val1 = Double.parseDouble(calculation.getText().toString());
        }
    }


    public static Double currencyConversion(Double amount, Currency currencyOne, Currency currencyTwo) {

        Double converted;

        if (currencyOne.getCode() != null) {
            if (currencyOne.getCode().equals("EUR")) {
                converted = amount * currencyTwo.getRate();
                converted = Math.round(100.0 * converted) / 100.0;
                return converted;
            }
        } else if (currencyTwo.getCode() != null) {
            if (currencyTwo.getCode().equals("EUR")) {
                converted = amount / currencyOne.getRate();
                converted = Math.round(100.0 * converted) / 100.0;
                return converted;
            }
        }

        converted = amount / currencyOne.getRate();
        converted = converted * currencyTwo.getRate();

        converted = Math.round(100.0 * converted) / 100.0;
        return converted;
    }


    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };

    private void sendRequest() {

        progressBar.setVisibility(View.VISIBLE);

        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        ro.start();


    }
}
