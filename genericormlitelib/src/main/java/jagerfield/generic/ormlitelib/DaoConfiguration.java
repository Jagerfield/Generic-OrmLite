package jagerfield.generic.ormlitelib;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import java.util.Set;

public abstract class DaoConfiguration
{
    public DaoHelper getDaoHelper(Context context) throws Exception
    {
        DaoHelper instance = DaoHelper.getInstance(context);
        if (instance == null)
        {
            throw new NullPointerException("DaoHelper value is null");
        }

        return instance;
    }

    public Dao getDaoEntity(Context context, Class T) throws Exception
    {
        Dao dao = DaoHelper.getInstance(context).getDao(T);
        if (dao == null)
        {
            throw new NullPointerException("DaoHelper value is null");
        }
        return dao;
    }

    public abstract Set<Class> getTableModels();
    public abstract String getDatabaseName();
    public abstract int getDatabaseVersion();
    public abstract void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion);
}
