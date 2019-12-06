package com.example.currencyconverter;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RequestOperator extends Thread {

    public interface RequestOperatorListener {

    }

    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener(RequestOperatorListener listener) {
        this.listener = listener;
    }


    @Override
    public void run() {
        super.run();
        try {
            sleep(2000);


            reuquestRates();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void reuquestRates() throws IOException, JSONException {
        //URL address
        URL obj = new URL("https://api.openrates.io/latest");

        //Executor
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //Determined what method will be used (GET, POST, PUT, or DELETE)
        con.setRequestMethod("GET");

        //Determine the content type. In this case, it is a JSON variable
        con.setRequestProperty("Content-Type", "application/json");

        //Make request and receive a response
        responseCode = con.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        InputStreamReader streamReader;

        //If response okay, using InputStream
        //If not, using error stream
        if (responseCode == 200) {
            streamReader = new InputStreamReader(con.getInputStream());
        } else {
            streamReader = new InputStreamReader(con.getErrorStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print Result
        System.out.println(response.toString());

        if (responseCode == 200) {
            parsingJsonCurrencies(response.toString());
        }
    }


    public void parsingJsonCurrencies(String response) throws JSONException {

        //attempts to create a json object of achieving
        JSONObject object = new JSONObject(response);
        JSONObject rates =  object.getJSONObject("rates");

        List<Currency> currencies = HomeActivity.createListC();

        for (int i = 1; i < currencies.size(); i++) {
            String code = currencies.get(i).getCode();
            if (code != null && !code.equals("EUR")){
                Double rate = rates.getDouble(code);
                currencies.get(i).setRate(rate);

                Currency updatedC = currencies.get(i);
                HomeActivity.mDb.currencyDao().update(updatedC);
            }

            System.out.println(i);
        }

        MainActivity.sendRequestButton.setText("Update Rates OK");
        MainActivity.progressBar.setVisibility(View.INVISIBLE);

    }

}



