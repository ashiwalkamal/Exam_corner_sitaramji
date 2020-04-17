package com.attors.examcorner;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class Exam extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/poppinslightitalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }




}
