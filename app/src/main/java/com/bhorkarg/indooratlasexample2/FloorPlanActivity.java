package com.bhorkarg.indooratlasexample2;

import android.net.Uri;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.IARegion;
import com.indooratlas.android.sdk.resources.IAFloorPlan;
import com.indooratlas.android.sdk.resources.IAResourceManager;
import com.indooratlas.android.sdk.resources.IAResult;
import com.indooratlas.android.sdk.resources.IAResultCallback;
import com.indooratlas.android.sdk.resources.IATask;
import com.squareup.picasso.Picasso;

public class FloorPlanActivity extends AppCompatActivity implements IARegion.Listener, IALocationListener {

    IALocationManager mLocationManager;
    IAResourceManager mResourceManager;

    IATask<IAFloorPlan> mIATask;

    IAResultCallback<IAFloorPlan> floorPlanResultCallback = new IAResultCallback<IAFloorPlan>() {
        @Override
        public void onResult(IAResult<IAFloorPlan> iaResult) {
            if (iaResult.isSuccess()) {
                //get the image and load it into ImageView
                IAFloorPlan iaFloorPlan = iaResult.getResult();

                ImageView imageFloorPlan = (ImageView) findViewById(R.id.imageFloorPlan);
                Picasso.with(FloorPlanActivity.this).load(iaFloorPlan.getUrl()).into(imageFloorPlan);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        //get the location manager of this application
        MyApp myApp = (MyApp) getApplication();
        mLocationManager = myApp.getLocationManager();

        mResourceManager = IAResourceManager.create(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLocationManager.registerRegionListener(this);
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.unregisterRegionListener(this);
        mLocationManager.removeLocationUpdates(this);
    }

    @Override
    public void onEnterRegion(IARegion iaRegion) {
        //Cancel earlier stuck task if any
        if (mIATask != null && !mIATask.isCancelled()) {
            mIATask.cancel();
        }

        //Get FloorPlan loading task
        mIATask = mResourceManager.fetchFloorPlanWithId(iaRegion.getId());
        mIATask.setCallback(floorPlanResultCallback, Looper.getMainLooper());
    }

    @Override
    public void onExitRegion(IARegion iaRegion) {
        //Clear the image
        ImageView imageFloorPlan = (ImageView) findViewById(R.id.imageFloorPlan);
        imageFloorPlan.setImageURI(null);
        Toast.makeText(FloorPlanActivity.this, "Moved out of region", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(IALocation iaLocation) {
        //Nothing
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        //Nothing
    }
}
