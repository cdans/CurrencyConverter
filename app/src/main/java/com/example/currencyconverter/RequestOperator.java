package com.example.currencyconverter;

import org.json.JSONException;

import java.io.IOException;

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

    }
}
