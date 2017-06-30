package jagerfield.generic.ormlite.dao_config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.support.ConnectionSource;

import java.util.HashSet;
import java.util.Set;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlite.models.Employee;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoConfiguration;
import jagerfield.generic.ormlitelib.DaoHelper;

public class AppDaoConfigThree extends DaoConfiguration
{
    private Set<Class> tableModels;
    private final static String DATABASE_NAME = C.DB_GIVEN_NAME;
    private final static int DATABASE_VERSION = 3;
    private Context context;

    public AppDaoConfigThree(Context context)
    {
        this.context = context;
        tableModels = new HashSet<>();

        if(DATABASE_VERSION == 2)
        {
            tableModels.add(Person.class);
            tableModels.add(Building.class);
            tableModels.add(Employee.class);
        }
    }

    @Override
    public Set<Class> getAppTableModels() {
        return tableModels;
    }

    @Override
    public String getConfigDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public int getConfigDatabaseVersion() {
        return DATABASE_VERSION;
    }

    @Override
    public int getSqlLiteDatabaseVersion(DaoHelper daoHelper)
    {
        int v = daoHelper.getDatabaseVersion();
        return v;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        switch (oldVersion)
        {
            case 1:
                updateFromVersionOne(database, connectionSource, oldVersion, newVersion);
                break;
            case 2:
                updateFromVersionTwo(database, connectionSource, oldVersion, newVersion);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        switch (newVersion)
        {
            case 1:
                try
                {
                    int result = super.getDaoHelper(context).dropTable(Building.class, true);
                    Log.i("TAG", "Added table 'buildings");
                    int result1 = super.getDaoHelper(context).createTable(Employee.class);
                    Log.i("TAG", "Added table 'buildings");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case 2:
                try
                {
                    int result1 = super.getDaoHelper(context).createTable(Employee.class);
                    Log.i("TAG", "Added table 'buildings");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void updateFromVersionOne(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        Log.i("TAG", "Upgrading DB from version 2");

        if (oldVersion==1)
        {
            try
            {
                int result1 = super.getDaoHelper(context).createTable(Building.class);
                Log.i("TAG", "Added table 'buildings");
                int result = super.getDaoHelper(context).createTable(Employee.class);
                Log.i("TAG", "Added table 'buildings");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if (oldVersion==2)
        {
            try
            {
                int result = super.getDaoHelper(context).createTable(Employee.class);
                Log.i("TAG", "Added table 'buildings");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void updateFromVersionTwo(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        Log.i("TAG", "Upgrading DB from version 2");

        if (oldVersion==2)
        {
            try
            {
                int result = super.getDaoHelper(context).createTable(Employee.class);
                Log.i("TAG", "Added table 'buildings");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

}