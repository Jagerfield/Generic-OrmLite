package jagerfield.generic.ormlite;

import android.app.Application;

import jagerfield.generic.ormlite.DaoConfig.AppDaoConfig;
import jagerfield.generic.ormlitelib.DaoHelper;

public class MyApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        DaoHelper.InitializeDaoAndTables(this, new AppDaoConfig(this));

    }
}
