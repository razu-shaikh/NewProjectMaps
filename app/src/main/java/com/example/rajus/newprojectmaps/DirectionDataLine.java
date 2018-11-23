package com.example.rajus.newprojectmaps;

/**
 * Created by rajus on 4/4/2018.
 */

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;

/**
 * Created by rajus on 3/30/2018.
 */

public class DirectionDataLine extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    LatLng latLng;


    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];
        latLng = (LatLng) objects[2];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googleDirectionsData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String sa) {

        String[] directionsLists;
        DataParser parser = new DataParser();
        directionsLists = parser.parseDirectionLine(sa);
        displayDirection(directionsLists);

    }

    public void displayDirection(String[] directionsList) {

        int count = directionsList.length;
        for (int i = 0; i < count; i++) {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.RED);
            options.width(10);
            options.addAll(PolyUtil.decode(directionsList[i]));

            mMap.addPolyline(options);
        }
    }

}
