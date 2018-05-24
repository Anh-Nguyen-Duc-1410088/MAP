package com.ck5000.thuan.nongdanbiet.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import com.ck5000.thuan.nongdanbiet.R;
import com.ck5000.thuan.nongdanbiet.model.DailyItem;
import com.ck5000.thuan.nongdanbiet.model.OpenWeatherJSondaily;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;



public class WeatherAsyncTaskDaily_Forecast extends AsyncTask<Void,Void,OpenWeatherJSondaily> {
    ProgressDialog dialog;
    Activity activity;
    TypePrediction typePrediction;
    String q= null;
    Context context;
    double latitude;
    double longitude;
    NumberFormat format = new DecimalFormat("#0.0");
    List<Bitmap> myBitmapDaily;
    List<Bitmap> myBitmapForecast;
    int weather_daily = 7;
    int weather_forecast = 8;

    /**
     * Constructor dùng để lấy thời tiết theo địa chỉ bất kỳ
     * @param activity
     * @param q
     */
    public WeatherAsyncTaskDaily_Forecast(Context context, Activity activity, String q)
    {
        this.context = context;
        this.activity=activity;
        this.typePrediction=TypePrediction.ADDRESS_NAME;
        this.q=q;
        this.dialog=new ProgressDialog(activity);
        this.dialog.setTitle("Đang tải thông tin ...");
        this.dialog.setMessage("Vui lòng chờ...");
        this.dialog.setCancelable(true);
    }

    /**
     * constructor cho phép lấy thông tin thời tiết theo tọa độ bất kỳ
     * @param activity
     * @param latitude
     * @param longitude
     */
    public WeatherAsyncTaskDaily_Forecast(Context context, Activity activity, double latitude, double longitude)
    {
        this.context = context;
        this.activity=activity;
        this.typePrediction=TypePrediction.LATITUDE_LONGITUDE;
        this.latitude=latitude;
        this.longitude=longitude;
        this.dialog=new ProgressDialog(activity);
        this.dialog.setTitle("Đang tải thông tin ...");
        this.dialog.setMessage("Vui lòng chờ...");
        this.dialog.setCancelable(true);
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.dialog.show();
    }

    @Override
    protected OpenWeatherJSondaily doInBackground(Void... params) {
        OpenWeatherJSondaily openWeatherJSon=null;
        if(typePrediction== TypePrediction.LATITUDE_LONGITUDE) {
            openWeatherJSon = OpenWeatherMapAPI.predictionDaily(latitude, longitude, 7);
        }
        else {
            openWeatherJSon = OpenWeatherMapAPI.predictionDaily(q, 7);
        }
        String idIcon;
        String urlIcon;
        URL urlConnection;
        InputStream input;
        HttpURLConnection connection;
        for(int i=0;i<weather_daily;i++)
            try {
                idIcon = openWeatherJSon.getList().get(i).getWeather().get(0).getIcon();
                urlIcon = "http://openweathermap.org/img/w/"+idIcon+".png";
                //Tiến hành tạo đối tượng URL
                urlConnection = new URL(urlIcon);
                //Mở kết nối
                connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                //Đọc dữ liệu
                input = connection.getInputStream();
                //Tiến hành convert qua hình ảnh
                //myBitmapDaily.add(BitmapFactory.decodeStream(input));
                openWeatherJSon.getList().get(i).setIcon(BitmapFactory.decodeStream(input));
            } catch (Exception e) {
                e.printStackTrace();
            }
        /*for(int i=0;i<weather_daily;i++)
            try {
                idIcon = openWeatherJSon.getOpenWeatherJSonForecast().getList().get(i).getWeather().get(0).getIcon();
                urlIcon = "http://openweathermap.org/img/w/"+idIcon+".png";
                //Tiến hành tạo đối tượng URL
                urlConnection = new URL(urlIcon);
                //Mở kết nối
                connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                //Đọc dữ liệu
                input = connection.getInputStream();
                //Tiến hành convert qua hình ảnh
                //myBitmapForecast.add(BitmapFactory.decodeStream(input));
                openWeatherJSon.getOpenWeatherJSonForecast().getList().get(i).setIcon(BitmapFactory.decodeStream(input));
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        return openWeatherJSon;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(OpenWeatherJSondaily openWeatherJSon) {
        super.onPostExecute(openWeatherJSon);
        TextView textView = (TextView) activity.findViewById(R.id.textView3);
        if ( q!= null )
            textView.setText(q);
        else
            textView.setText(openWeatherJSon.getCity().getName());
        ListView listView = (ListView) activity.findViewById(R.id.list_daily);
        List<DailyItem> items;
        items = openWeatherJSon.getList();
        AppWeather appWeather = new AppWeather(context,items);
        listView.setAdapter(appWeather);
        this.dialog.dismiss();
    }
}

