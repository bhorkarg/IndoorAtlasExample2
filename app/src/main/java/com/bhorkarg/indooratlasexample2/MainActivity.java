package com.bhorkarg.indooratlasexample2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Get the location manager of this application
        MyApp myapp = (MyApp)getApplication();
        myapp.destroyLocationManager();
    }

    public void buttonClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnFloorPlan:
                intent = new Intent(this, FloorPlanActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLocation:
                intent = new Intent(this, LocationActivity.class);
                startActivity(intent);
                break;
        }

    }
}
