package jagerfield.generic.ormlite.app_utils;


import android.content.Context;

import jagerfield.generic.ormlitelib.DaoHelper;

public class C
{
    public static final String APP_PREFERENCES = "APP_PREFERENCES";
    public static final String DB_GIVEN_NAME = "OrmLiteTest.db";

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
}
