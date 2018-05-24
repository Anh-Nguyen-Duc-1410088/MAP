package com.ck5000.thuan.nongdanbiet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ck5000.thuan.nongdanbiet.Class_Bao.AppAdapter;
import com.ck5000.thuan.nongdanbiet.Class_Bao.RSSItem;
import com.ck5000.thuan.nongdanbiet.Class_Bao.RssParser;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

public class BaiBaoActivity extends FragmentActivity {

    List<RSSItem> items;
    RssParser rssParser = new RssParser();
    ListView listView;
    Button button1;
    Button button2;
    Button button3;
    public static BaiBaoActivity it;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_bao);
        initImageLoader(this);
        it = this;
        items = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);
        button1 = (Button) findViewById(R.id.textView1);
        button2 = (Button) findViewById(R.id.textView2);
        button3 = (Button) findViewById(R.id.textView3);

        new GetKN(this).execute();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetKN(BaiBaoActivity.it).execute();
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new GetNTM(BaiBaoActivity.it).execute();
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new GetNDV(BaiBaoActivity.it).execute();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BaiBaoActivity.this, NewActivity.class);
                intent.putExtra("link", items.get(i).get_link());
                startActivity(intent);
            }
        });
    }



    public class GetListItem extends AsyncTask<Void, Void, Void> {

        Context context;
        ProgressDialog pd;

        GetListItem(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            items = rssParser.getRSSFeedItems("http://nongnghiep.vn/rss/khuyen-nong-7.rss");

            Log.d("rss", items.size() + "");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            AppAdapter adapter = new AppAdapter(context, items);
            listView.setAdapter(adapter);

        }

    }

    public class GetKN extends AsyncTask<Void, Void, Void> {

        Context context;
        ProgressDialog pd;

        GetKN(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            items = rssParser.getRSSFeedItems("http://nongnghiep.vn/rss/khuyen-nong-7.rss");

            Log.d("rss", items.size() + "");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            AppAdapter adapter = new AppAdapter(context, items);
            listView.setAdapter(adapter);

        }
    }

    public class GetNTM extends AsyncTask<Void, Void, Void> {

        Context context;
        ProgressDialog pd;

        GetNTM(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            items = rssParser.getRSSFeedItems("http://nongnghiep.vn/rss/nong-thon-moi-10.rss");

            Log.d("rss", items.size() + "");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            AppAdapter adapter = new AppAdapter(context, items);
            listView.setAdapter(adapter);

        }

    }

    public class GetNDV extends AsyncTask<Void, Void, Void> {

        Context context;
        ProgressDialog pd;

        GetNDV(Context context) {
            this.context = context;
        }

        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            items = rssParser.getRSSFeedItems("http://nongnghiep.vn/rss/nnvn-ban-doc-11.rss");

            Log.d("rss", items.size() + "");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            AppAdapter adapter = new AppAdapter(context, items);
            listView.setAdapter(adapter);

        }

    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }


}
