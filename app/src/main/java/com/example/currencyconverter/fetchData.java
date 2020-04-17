package com.example.currencyconverter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.content.Context;


public class fetchData extends AsyncTask<Void, Integer ,Void> {

    String data = ""; // to get the data from the web api
    private ProgressDialog progbar; // the progressbar
    private Context nContext = null;

    fetchData(Context context)
    {
        nContext = context;
    }

    //runs befor the background method is called.
    //setting up the progress bar
    @Override
    protected void onPreExecute() {



        final ProgressDialog dialog= ProgressDialog.show(nContext,"", "Please wait....",true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    dialog.dismiss();
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }).start();



        super.onPreExecute();
//
//
    }

//    private CharSequence getString(int progessbar) {
//        return null;
//    }
//
    // to update the progressbar
    @Override
    public void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progbar.setProgress(values[0]);
    }

    // TO fetch data from the web api running in the background
    @Override
    protected Void doInBackground(Void... voids) {
        //int progresscount = 0;
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            data = data + line;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //conversion of currency that runs after background thread finishes
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //dismiss the cancel button
        //progbar.dismiss();

        Currency_Conversion.fetData = this.data;
        Log.i("fwtcdhdhdhdh", Currency_Conversion.fetData);
    }


}


