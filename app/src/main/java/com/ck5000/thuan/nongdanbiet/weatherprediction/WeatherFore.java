package com.ck5000.thuan.nongdanbiet.weatherprediction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ck5000.thuan.nongdanbiet.R;
import com.ck5000.thuan.nongdanbiet.utils.WeatherAsyncTaskDaily_Forecast;

/**
 * Created by ck5000 on 04-May-17.
 */

public class WeatherFore extends ActionBarActivity {
    static double lat;
    static double lon;
    public static WeatherFore it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        it = this;
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWeatherDaily_Forecast();
    }

    /**
     * source code lấy địa điểm hiện tại của thiết bị
     * chú ý nhớ cấp quyền trong Manifest để cho phép truy suất
     */
    private void getWeatherDaily_Forecast() {
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("ck5000_latlon");
        lon = packageFromCaller.getDouble("location_lon");
        lat = packageFromCaller.getDouble("location_lat");
        WeatherAsyncTaskDaily_Forecast task = new WeatherAsyncTaskDaily_Forecast(it , this, lat,lon);
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_current_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
