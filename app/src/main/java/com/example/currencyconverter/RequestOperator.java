package com.example.currencyconverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestOperator extends Thread {

    public interface RequestOperatorListener {
        void success(ModelPost publication);

        void failed(int responseCode);

        void addPostsNumber(int countPosts);
    }

    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener(RequestOperatorListener listener) {
        this.listener = listener;
    }

  /* public interface CurrencyUnit {
        String getCurrencyCode();
        int getNumericCode();
        int getDefaultFractionDigits ();
        CurrencyContext getContext (); // new
    }*/


    @Override
    public void run() {
        super.run();
        try {
            sleep(2000);
            ModelPost publication = request();
            String jsnString = request2();
            request3();
            if (publication != null) {
                success(publication);
                addPostsNumber(this.countPosts(jsnString));

            } else {
                failed(responseCode);
            }
        } catch (IOException e) {
            failed(-1);
        } catch (JSONException e) {
            failed(-2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void addPostsNumber(int countPosts) {
        if (listener != null) {
            listener.addPostsNumber(countPosts);
        }
    }

    private int countPosts(String jsnString) {
        int counter = 0;
        char c;
        for (int i = 0; i < jsnString.length(); i++) {
            c = jsnString.charAt(i);
            if (c == '{') counter++;
        }
        return counter;
    }


    private String request2() throws IOException, JSONException {
        //URL address
        URL obj = new URL("https://jsonplaceholder.typicode.com/posts");

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
            return response.toString();
        } else {
            return null;
        }
    }

    private void request3() throws IOException, JSONException {
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


    private ModelPost request() throws IOException, JSONException {

        //URL address
        URL obj = new URL("https://jsonplaceholder.typicode.com/posts/1");

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
            return parsingJsonPost(response.toString());
        } else {
            return null;
        }
    }

    public void parsingJsonCurrencies(String response) throws JSONException {

        //attempts to create a json object of achieving
        JSONObject object = new JSONObject(response);
        JSONObject rates =  object.getJSONObject("rates");

        for (int i = 1; i<HomeActivity.currencies.size(); i++) {
            String code = HomeActivity.currencies.get(i).getCode();
            if (code != null){
                Double rate = rates.getDouble(code);
                HomeActivity.currencies.get(i).setRate(rate);
            }

            System.out.println(i);
        }
    }

    public ModelPost parsingJsonPost(String response) throws JSONException {

        //attempts to create a json object of achieving
        JSONObject object = new JSONObject(response);

        ModelPost post = new ModelPost();

        //because we will not need ID and User ID, they do not necessarily
        //get from a server in the JSON object
        post.setId(object.optInt("id", 0));
        post.setUserId(object.optInt("userId", 0));

        //if the variables have not been found will be held JSONException
        post.setTitle(object.getString("title"));
        post.setBodyText(object.getString("body"));

        return post;
    }

    private void failed(int code) {
        if (listener != null) {
            listener.failed(code);
        }
    }

    private void success(ModelPost publication) {
        if (listener != null) {
            listener.success(publication);
        }
    }
}



