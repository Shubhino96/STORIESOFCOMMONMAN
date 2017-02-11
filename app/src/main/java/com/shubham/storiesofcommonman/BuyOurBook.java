package com.shubham.storiesofcommonman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BuyOurBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_our_book);

        WebView webView  = (WebView)findViewById(R.id.buy);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.amazon.com/storiesofcommonman-First-Copy-Book-1-ebook/dp/B01M7YYHOK");
    }
}
