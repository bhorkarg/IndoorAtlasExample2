package com.bhorkarg.indooratlasexample2;

import android.app.Application;

import com.indooratlas.android.sdk.IALocationManager;

/**
 * Created by Gaurav on 24-Feb-16.
 */
public class MyApp extends Application {

    private IALocationManager mLocationManager; //this will be shared application wide

    @Override
    public void onCreate() {
        super.onCreate();

        mLocationManager = IALocationManager.create(this);
    }

    public IALocationManager getLocationManager() {
        return mLocationManager;
    }

    public void destroyLocationManager() {
        //Is .destroy() really needed? Won't ART destroy this when required?
        //mLocationManager.destroy();
    }

}
