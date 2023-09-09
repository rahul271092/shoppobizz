package com.cash.earnapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {


    ProgressDialog mProgress;
    WebView webView1;
    String link,Tag;
    String mGeoLocationRequestOrigin = null;
    GeolocationPermissions.Callback  mGeoLocationCallback = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView1=findViewById(R.id.affiliate_webview1);
        mProgress = new ProgressDialog(WebViewActivity.this);
        mProgress.setMessage("Please wait...");

        if(getIntent().getStringExtra("url")!=null)
        {
            link=getIntent().getStringExtra("url");
            LoadUrl(link);
        }
    }


    private class CustomWebViewClient extends WebViewClient {
        boolean onPageStarted = false;
        Runnable hideLoadingRunnable = new Runnable() {
            @Override
            public void run() {
                if (!onPageStarted) {
                    mProgress.dismiss();
                }
            }
        };

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            onPageStarted = true;
            mProgress.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if (url.startsWith("file://")) {
                // Handle file upload request
                return true;
            }
            return super.shouldOverrideUrlLoading(view, request);
//            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            onPageStarted = false;
            webView1.postDelayed(hideLoadingRunnable, 1000);
        }
    }


    @Override
    public void onBackPressed() {
        if (webView1.canGoBack()) {
            webView1.goBack();
        } else
        {
            super.onBackPressed();
        }
    }

    public void LoadUrl(String url)
    {
        WebSettings webSettings =webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webView1.setWebViewClient(new CustomWebViewClient());
        Log.e(Tag,"Link:"+link);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        // webView1.setWebViewClient(new WebViewClient());
        webView1.getSettings().setJavaScriptEnabled(true);

//        webView1.setWebChromeClient(new WebChromeClient() {
//            private ProgressDialog mProgress;
//
//            @Override
//            public void onProgressChanged(WebView view, int progress) {
//                if (mProgress == null) {
//                    mProgress = new ProgressDialog(WebViewActivity.this);
//                    mProgress.show();
//                }
//                mProgress.setMessage("Please Wait.." + String.valueOf(progress) + "%");
//                if (progress == 100) {
//                    mProgress.dismiss();
//                    mProgress = null;
//                }
//            }
//
//        });

        webView1.loadUrl(url);
    }


}
