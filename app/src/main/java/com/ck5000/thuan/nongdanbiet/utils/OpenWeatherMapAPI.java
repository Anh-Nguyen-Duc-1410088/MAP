package com.ck5000.thuan.nongdanbiet.utils;


import com.ck5000.thuan.nongdanbiet.model.OpenWeatherJSonCurrent;
import com.ck5000.thuan.nongdanbiet.model.OpenWeatherJSondaily;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class OpenWeatherMapAPI {
    public static OpenWeatherJSonCurrent prediction(String q)
    {
        try {
            String location= URLEncoder.encode(q, "UTF-8");

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+location+"&appid=bc682f584f73ac2b08c04b35f126b8e9");
            InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
            OpenWeatherJSonCurrent results = new Gson().fromJson(reader, OpenWeatherJSonCurrent.class);

            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * http://api.openweathermap.org/data/2.5/weather?lat=10.778182&lon=106.665504
     * @param lat
     * @param lon
     * @return
     */
    public static OpenWeatherJSonCurrent prediction(double lat, double lon)
    {
        try {

            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=bc682f584f73ac2b08c04b35f126b8e9");
            InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
            OpenWeatherJSonCurrent results = new Gson().fromJson(reader, OpenWeatherJSonCurrent.class);

            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        return null;
    }

    public static OpenWeatherJSondaily predictionDaily(double lat, double lon, int cnt)
    {
        try {

            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?lat="+lat+"&lon="+lon+"&cnt="+cnt+"&appid=bc682f584f73ac2b08c04b35f126b8e9");
            InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
            OpenWeatherJSondaily results = new Gson().fromJson(reader, OpenWeatherJSondaily.class);

            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        return null;
    }


    public static OpenWeatherJSondaily predictionDaily(String q, int cnt)
    {
        try {
            String location= URLEncoder.encode(q, "UTF-8");
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q="+location+"&cnt="+cnt+"&appid=bc682f584f73ac2b08c04b35f126b8e9");
            InputStreamReader reader = new InputStreamReader(url.openStream(),"UTF-8");
            OpenWeatherJSondaily results = new Gson().fromJson(reader, OpenWeatherJSondaily.class);

            return results;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
        return null;
    }

}

