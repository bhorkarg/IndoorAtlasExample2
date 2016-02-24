package com.bhorkarg.indooratlasexample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class LocationActivity extends AppCompatActivity implements IALocationListener{

    IALocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        MyApp myApp = (MyApp) getApplication();
        mLocationManager = myApp.getLocationManager();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeLocationUpdates(this);
    }

    @Override
    public void onLocationChanged(IALocation iaLocation) {
        TextView txtLatLng = (TextView) findViewById(R.id.txtLatLng);
        txtLatLng.setText("Lat: " + iaLocation.getLatitude() + "\nLng: " + iaLocation.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //do nothing
    }
}
