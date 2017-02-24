package xyz.aiyouweiya.materialinstagram;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by aiyouweiya on 2017/2/24.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
