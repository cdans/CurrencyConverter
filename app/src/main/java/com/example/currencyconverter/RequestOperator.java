package com.example.currencyconverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestOperator extends Thread {

    public interface RequestOperatorListener{
        void success (ModelPost publication);
        void failed (int responseCode);
    }

    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener (RequestOperatorListener listener) { this.listener = listener; }

    @Override
    public void run(){
        super.run();
        try{
            ModelPost publication = request ();
            if (publication!=null){
                success(publication);
            }
            else{
                failed (responseCode);
            }
        }catch (IOException e){
            failed(-1);
        }catch (JSONException e){
            failed(-2);
        }
    }

    private ModelPost request() throws IOException, JSONException{

        //URL address
        URL obj = new URL ("https://jsonplaceholder.typicode.com/posts/1");

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
        if(responseCode==200){
            streamReader = new InputStreamReader(con.getInputStream());
        }else {
            streamReader = new InputStreamReader(con.getErrorStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        //print Result
        System.out.println(response.toString());

        if (responseCode==200){
            return parsingJsonObject(response.toString());
        }
        else{
            return null;
        }
    }

    public ModelPost parsingJsonObject(String response) throws JSONException {

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

    private void failed (int code){
        if (listener != null){
            listener.failed(code);
        }
    }

    private void success(ModelPost publication){
        if (listener!=null){
            listener.success(publication);
        }
    }


}
