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
    private Context context;

    public fetchData(Context context) {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        // setting up the progress bar

        progbar = new ProgressDialog(context);
        progbar.setCancelable(true);
        progbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progbar.setProgressStyle(0);
        progbar.setMax(100);
        progbar.setMessage(getString(R.string.Progressbar));

        // make a button.
        progbar.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.Cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        progbar.show();
        super.onPreExecute();
    }

    private CharSequence getString(int progessbar) {
        return null;
    }

    // to update the progressbar
    @Override
    public void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progbar.setProgress(values[0]);
    }

    // TO fetch data from the web api
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

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        //dismiss the cancel button
        progbar.dismiss();

        Currency_Conversion.fetData = this.data;
        Log.i("fwtcdhdhdhdh", Currency_Conversion.fetData);
    }


}


