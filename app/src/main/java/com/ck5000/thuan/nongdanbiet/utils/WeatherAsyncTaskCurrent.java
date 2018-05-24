package com.ck5000.thuan.nongdanbiet.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.ck5000.thuan.nongdanbiet.R;
import com.ck5000.thuan.nongdanbiet.model.OpenWeatherJSonCurrent;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;


public class WeatherAsyncTaskCurrent extends AsyncTask<Void,Void,OpenWeatherJSonCurrent> {
    ProgressDialog dialog;
    Activity activity;
    TypePrediction typePrediction;
    String q = null;
    double latitude;
    double longitude;
    NumberFormat format = new DecimalFormat("#0.0");
    Bitmap myBitmap=null;

    /**
     * Constructor dùng để lấy thời tiết theo địa chỉ bất kỳ
     * @param activity
     * @param q
     */
    public WeatherAsyncTaskCurrent(Activity activity, String q)
    {
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
    public WeatherAsyncTaskCurrent(Activity activity, double latitude, double longitude)
    {
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
    protected OpenWeatherJSonCurrent doInBackground(Void... params) {
        OpenWeatherJSonCurrent openWeatherJSon=null;
        if(typePrediction== TypePrediction.LATITUDE_LONGITUDE)
            openWeatherJSon= OpenWeatherMapAPI.prediction(latitude,longitude);
        else
            openWeatherJSon= OpenWeatherMapAPI.prediction(q);
        try {
            String idIcon = openWeatherJSon.getWeather().get(0).getIcon().toString();
            String urlIcon = "http://openweathermap.org/img/w/"+idIcon+".png";
            //Tiến hành tạo đối tượng URL
            URL urlConnection = new URL(urlIcon);
            //Mở kết nối
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            //Đọc dữ liệu
            InputStream input = connection.getInputStream();
            //Tiến hành convert qua hình ảnh
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openWeatherJSon;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(OpenWeatherJSonCurrent openWeatherJSon) {
        super.onPostExecute(openWeatherJSon);

        TextView txtTemperature=(TextView) activity.findViewById(R.id.txtTemperature);
        TextView txtCurrentAddressName=(TextView) activity.findViewById(R.id.txtCurrentAddressName);
        ImageView imageView=(ImageView) activity.findViewById(R.id.imgBauTroi);
        TextView txtWind=(TextView) activity.findViewById(R.id.txtWind);
        TextView txtCloudliness= (TextView) activity.findViewById(R.id.txtCloudliness);
        TextView txtPressure= (TextView) activity.findViewById(R.id.txtPressure);
        TextView txtHumidty= (TextView) activity.findViewById(R.id.txtHumidty);
        TextView txtSunrise= (TextView) activity.findViewById(R.id.txtSunrise);
        TextView txtSunset= (TextView) activity.findViewById(R.id.txtSunset);
        TextView txtTime= (TextView) activity.findViewById(R.id.txtTemperature);

        double temperature=openWeatherJSon.getMain().getTemp()-273.15;
        String wind= openWeatherJSon.getWind().getSpeed()+" m/s";
        String mesg = openWeatherJSon.getWeather().get(0).getMain();
        String cloudiness=mesg;
        String pressure= openWeatherJSon.getMain().getPressure()+" hpa";
        String humidity=openWeatherJSon.getMain().getHumidity()+" %";
        Date timeCurrent = new Date(openWeatherJSon.getDt()*1000);
        String Current = timeCurrent.toGMTString();
        Date timeSunrise = new Date(openWeatherJSon.getSys().getSunrise()*1000);
        String Sunrise= timeSunrise.getHours()+":"+timeSunrise.getMinutes();
        Date timeSunSet = new Date(openWeatherJSon.getSys().getSunset()*1000);
        String sunset= timeSunSet.getHours()+":"+timeSunSet.getMinutes();

        txtTime.setText(Current);
        txtTemperature.setText(format.format(temperature)+"°C");
        imageView.setImageBitmap(myBitmap);
        txtWind.setText(wind);
        txtCloudliness.setText(cloudiness);
        txtPressure.setText(pressure);
        txtHumidty.setText(humidity);
        txtSunrise.setText(Sunrise);
        txtSunset.setText(sunset);
        if ( q!= null )
            txtCurrentAddressName.setText(q);
        else
            txtCurrentAddressName.setText(openWeatherJSon.getName());

        this.dialog.dismiss();
    }
}
