package com.ck5000.thuan.nongdanbiet.weatherprediction;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ck5000.thuan.nongdanbiet.R;
import com.ck5000.thuan.nongdanbiet.utils.GPSTracker;
import com.ck5000.thuan.nongdanbiet.utils.WeatherAsyncTaskCurrent;


public class WeatherCurrentLocationActivity extends ActionBarActivity {
    static double lat;
    static double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_current_location);
        Button buttonWeather = (Button) findViewById(R.id.dubaothoitiet);
        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkConnected()) {
                    Toast.makeText(WeatherCurrentLocationActivity.this, "Bạn cần mở kết nối internet", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent iWeathe = new Intent(WeatherCurrentLocationActivity.this, WeatherFore.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("location_lon", lon);
                bundle.putDouble("location_lat", lat);
                iWeathe.putExtra("ck5000_latlon", bundle);
                startActivity(iWeathe);
            }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCurrentLocation();
    }

    /**
     * source code lấy địa điểm hiện tại của thiết bị
     * chú ý nhớ cấp quyền trong Manifest để cho phép truy suất
     */
    private void getCurrentLocation() {

        GPSTracker lastLocation = new GPSTracker(this);
        if (lastLocation.canGetLocation()) {
            lat = lastLocation.getLatitude();
            lon = lastLocation.getLongitude();
            //ta lấy được thì truyền vĩ độ, kinh độ để xem thời tiết
            WeatherAsyncTaskCurrent task = new WeatherAsyncTaskCurrent(this, lastLocation.getLatitude(), lastLocation.getLongitude());
            task.execute();
        }
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
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}
