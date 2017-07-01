package jagerfield.generic.ormlite;

import android.app.Application;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.app_utils.PrefrenceUtil;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigOne;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigThree;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigTwo;
import jagerfield.generic.ormlitelib.DaoHelper;

public class MyApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        C.createAppDB(getApplicationContext());
    }


}
