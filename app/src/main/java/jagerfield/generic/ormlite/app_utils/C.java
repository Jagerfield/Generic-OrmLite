package jagerfield.generic.ormlite.app_utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import jagerfield.generic.ormlite.MainActivity;
import jagerfield.generic.ormlite.R;
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
        Intent intentRestartApp = new Intent(context, MainActivity.class);
        int intentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, intentId, intentRestartApp, PendingIntent.FLAG_CANCEL_CURRENT);
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

    public static boolean sysIsBroken(Context context, View view)
    {
        boolean result = false;
        if (context==null) { Log.e("TAG", "Activity is null"); result = true; }
        if (view==null) { Log.e("TAG", "View is null"); result = true; }

        return result;
    }

    public static boolean sysIsBroken(Context context, List<View> views)
    {
        boolean result = false;
        if (context==null)
        {
            Log.e("TAG", "Activity is null"); result = true;
        }
        if (views==null)
        {
            Log.e("TAG", "Views is null"); result = true;
        }
        for (View view:views)
        {
            if (view==null)
            {
                result = true;
                Log.e("TAG", "View is null"); result = true;
                break;
            }
        }

        return result;
    }

    public static boolean sysIsBroken(Context context)
    {
        boolean result = false;
        if (context==null) { Log.e("TAG", "Activity is null"); result = true; }

        return result;
    }

    public static void setButtonState(Context context, boolean state, Button button)
    {
        if (C.sysIsBroken(context, button))
        {
            return;
        }

        if (state)
        {
            button.setEnabled(true);
            button.setTextColor(ContextCompat.getColor(context, R.color.greendark));
        }
        else
        {
            button.setEnabled(false);
            button.setTextColor(ContextCompat.getColor(context, R.color.greymedium));
        }
    }
}
