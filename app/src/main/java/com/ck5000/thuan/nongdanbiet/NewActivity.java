package com.ck5000.thuan.nongdanbiet;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class NewActivity extends Activity {

    WebView webview;
    String link, title, description, date;
    String detail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_layout);
        webview = (WebView) findViewById(R.id.desc);
        Bundle bundle = getIntent().getExtras();
        link = bundle.getString("link");
        WebSettings webSettings = webview.getSettings();
        webSettings.setSupportZoom(true);

        new GetData().execute();






    }

    public class GetData extends AsyncTask<Void, Void, Void> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc = Jsoup.connect(link)
                        .get();

                Elements title = doc.select("div.nn-top-post h1");
                Elements date = doc.select("div.nn-top-post span");
                Elements description = doc.select("div.nn-top-post p");
                doc.select("table").remove();
                Elements main = doc.select("div.nn-text-post ");
                detail += "<h1 style = \" color: black \">" + title.text()
                        + "</h1>";
                detail += "<font size=\" 1.2em \" style = \" color: #005500 \"><em>"
                        + date.text() + "</em></font>";
                detail += "<p style = \" color: #999999 \"><b>" + "<font size=\" 4em \" >"
                        + description.text() + "</font></b></p>";
                detail += "<font size=\" 4em \" >"+  main.toString() + "</font>";

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            webview.loadDataWithBaseURL(
                    "",
                    "<style>img{display: inline;height: auto;max-width: 100%;}"
                            + " p {font-family:\"Tangerine\", \"Sans-serif\",  \"Serif\" font-size: 48px} </style>"
                            + detail, "text/html", "UTF-8", "");

        }

    }
}
