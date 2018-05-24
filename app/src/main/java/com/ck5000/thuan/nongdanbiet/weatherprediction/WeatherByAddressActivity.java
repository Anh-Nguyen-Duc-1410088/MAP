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
import com.ck5000.thuan.nongdanbiet.utils.WeatherAsyncTaskCurrent;


public class WeatherByAddressActivity extends ActionBarActivity {
    static String q = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_current_location);
        Button buttonWeather = (Button) findViewById(R.id.dubaothoitiet);
        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isNetworkConnected())
                {
                    Toast.makeText(WeatherByAddressActivity.this,"Bạn cần mở kết nối internet", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent iWeather=new Intent(WeatherByAddressActivity.this,WeatherForecast.class);
                Bundle bundle =new Bundle();
                bundle.putString("location",q);
                iWeather.putExtra("ck5000",bundle);
                startActivity(iWeather);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWeatherByAddress();
    }

    /**
     * hàm gọi đa tiến trình truy suất thời tiết theo địa chỉ
     */
    private void getWeatherByAddress() {
        Intent i=getIntent();
        //lấy địa chỉ từ bên ChooseAddressActivity gửi qua:
        String q=i.getStringExtra("ADDRESS");
        this.q = q;
        WeatherAsyncTaskCurrent task=new WeatherAsyncTaskCurrent(this,q);
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_by_address, menu);
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
