package jagerfield.generic.ormlite.app_utils;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import jagerfield.generic.ormlite.MainActivity;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigOne;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigThree;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigTwo;
import jagerfield.generic.ormlitelib.DaoHelper;

public class C
{
    public static final String APP_PREFERENCES = "APP_PREFERENCES";
    public static final String DB_GIVEN_NAME = "OrmLiteTest.db";
    public static final String KEY_APPDB_VERSION = "KEY_APPDB_VERSION";
    public static final String TAG_DASHBOARD_TV = "dashboard_reading";

    public static boolean dropDB(String dbName, Context context)  throws Exception
    {
        if (dbName == null || dbName.isEmpty())
        {
            throw new IllegalArgumentException("dbName is null or empty");
        }
        boolean result = context.deleteDatabase(dbName);
        DaoHelper.setDaoHelperInstanceToNull();
        return result;
    }

    public static void restartApplication(Context context)
    {
        Intent intentRestartActivity = new Intent(context, MainActivity.class);
        int intentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, intentId, intentRestartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 300, mPendingIntent);
        System.exit(0);
    }

    public static void createAppDB(Context context)
    {
        final int dbVersion = PrefrenceUtil.getInt(context, C.KEY_APPDB_VERSION, 1);

        switch (dbVersion)
        {
            case 1:
                DaoHelper.initializeDaoAndTables(context, new AppDaoConfigOne(context));
                break;

            case 2:
                DaoHelper.initializeDaoAndTables(context, new AppDaoConfigTwo(context));
                break;

            default:
                DaoHelper.initializeDaoAndTables(context, new AppDaoConfigThree(context));
                break;
        }
    }
}
