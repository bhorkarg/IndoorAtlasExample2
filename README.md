# IndoorAtlasExample2
Example which uses Application singleton class to share LocationManager object across activities

## 1. Basics

All the basic concepts of IndoorAtlas API apply here. A quick demo is written in [IndoorAtlasExample](https://github.com/bhorkarg/IndoorAtlasExample/blob/master/README.md)


## 2. Sharing ``` IALocationManager ``` 

In this example, I have used Application singleton class to share the ``` IALocationManager ``` instance with other activities. Instantiated the object when the Application is started.

```java
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
```

Make sure to add ``` android:name=".MyApp" ``` in the Applicaiton tag of AndroidManifest.xml.

Now to use the ``` IALocationManager ``` object in other Activities we simply have to get the application object and call ``` getLocationManager() ```

```java
MyApp myApp = (MyApp) getApplication();
mLocationManager = myApp.getLocationManager();
```

## 3. Advantage

This style can be useful in simple cases where there is a need to share a few objects with all activities of your application.

Other alternatives include creating a separate Singleton class which can be shared across Activities. In some cases a Service can be created which can be bound by several activities to provide similar functionality.
