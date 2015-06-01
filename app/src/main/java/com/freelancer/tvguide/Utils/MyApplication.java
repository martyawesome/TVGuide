package com.freelancer.tvguide.Utils;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Android 18 on 6/1/2015.
 * <p/>
 * Initialization of the Models to be used
 *
 * @see com.activeandroid.ActiveAndroid
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
