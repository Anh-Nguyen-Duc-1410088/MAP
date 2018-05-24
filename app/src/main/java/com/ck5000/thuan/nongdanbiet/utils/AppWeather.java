package com.ck5000.thuan.nongdanbiet.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ck5000.thuan.nongdanbiet.R;
import com.ck5000.thuan.nongdanbiet.model.DailyItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class AppWeather extends BaseAdapter {
    NumberFormat format = new DecimalFormat("#0.0");
    SimpleDateFormat ft = new SimpleDateFormat("E d M");
    class Daily{
        TextView thoi_gian;
        ImageView icon;
        TextView temp_max;
        TextView temp_min;
        TextView description;
        TextView rain;
        TextView main;
    }
    public List<DailyItem> mlistAppInfo;
    LayoutInflater infater = null;
    private Context mContext;
    public AppWeather(Context context, List<DailyItem> apps) {
        infater = LayoutInflater.from(context);
        mContext = context;
        this.mlistAppInfo = apps;
    }
    @Override
    public int getCount() {
        return mlistAppInfo.size();
    }

    @Override
    public Object getItem(int i) {
        return mlistAppInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Daily daily = null;
        if (convertView == null) {
            convertView = infater.inflate(R.layout.item_daily,
                    null);
            daily = new Daily();
            daily.icon = (ImageView) convertView
                    .findViewById(R.id.daily_icon);
            daily.description = (TextView) convertView
                    .findViewById(R.id.daily_description);
            daily.main = (TextView) convertView
                    .findViewById(R.id.daily_main);
            daily.rain = (TextView) convertView
                    .findViewById(R.id.daily_rain);
            daily.temp_max = (TextView) convertView
                    .findViewById(R.id.daily_temp_max);
            daily.temp_min = (TextView) convertView
                    .findViewById(R.id.daily_temp_min);
            daily.thoi_gian = (TextView) convertView
                    .findViewById(R.id.daily_thoi_gian);
            convertView.setTag(daily);
        } else {
            daily = (Daily) convertView.getTag();
        }
        final DailyItem item = (DailyItem) getItem(position);
        if (item != null) {

            Date time = new Date(item.getDt()*1000);
            String thơi_gian = ft.format(time);
            String main = item.getHumidity()+"%";
            String rain = item.getSpeed()+"m/s";
            double tempmax = item.getTemp().getMax()- 273.15;
            double tempmin = item.getTemp().getMin()- 273.15;
            daily.main.setText(main);
            daily.thoi_gian.setText(thơi_gian);
            daily.description.setText(item.getWeather().get(0).getDescription());
            daily.temp_max.setText(format.format(tempmax)+"°C");
            daily.temp_min.setText(format.format(tempmin)+"°C");
            daily.rain.setText(rain);
            daily.icon.setImageBitmap(item.getIcon());
        }
        return convertView;
    }
}
