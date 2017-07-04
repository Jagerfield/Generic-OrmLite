package jagerfield.generic.ormlite;

import android.app.Application;
import jagerfield.generic.ormlite.app_utils.C;

public class MyApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        C.createAppDB(getApplicationContext());
    }


}
