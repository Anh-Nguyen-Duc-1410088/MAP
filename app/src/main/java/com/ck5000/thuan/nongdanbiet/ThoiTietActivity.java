package com.ck5000.thuan.nongdanbiet;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ck5000.thuan.nongdanbiet.weatherprediction.ChooseAddressActivity;
import com.ck5000.thuan.nongdanbiet.weatherprediction.WeatherCurrentLocationActivity;


public class ThoiTietActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoitiet);
    }

    public void showCurrentWeatherClick(View v)
    {
        if(!isNetworkConnected())
        {
            Toast.makeText(ThoiTietActivity.this,"Bạn cần mở kết nối internet",Toast.LENGTH_LONG).show();
            return;
        }
        Intent iCurrent=new Intent(ThoiTietActivity.this,WeatherCurrentLocationActivity.class) ;
        startActivity(iCurrent);
    }

    /**
     * sự kiện dùng để xem thời tiết theo địa chỉ nhập bất kỳ
     * @param v
     */
    public void showWeatherByAddressClick(View v)
    {
        if(!isNetworkConnected())
        {
            Toast.makeText(ThoiTietActivity.this,"Bạn cần mở kết nối internet",Toast.LENGTH_LONG).show();
            return;
        }
        Intent iaddress=new Intent(ThoiTietActivity.this,ChooseAddressActivity.class) ;
        startActivity(iaddress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * hàm dùng để kiểm tra xem điện thoại đang kết nối internet hay không
     * @return
     */
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
