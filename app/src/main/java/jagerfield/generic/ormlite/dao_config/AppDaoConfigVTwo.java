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

public class AppDaoConfigVTwo extends DaoConfiguration
{
    private Set<Class> tableModels;
    private final static String DATABASE_NAME = C.DB_GIVEN_NAME;
    private final static int DATABASE_VERSION = 2;
    private Context context;

    public AppDaoConfigVTwo(Context context)
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
    public Set<Class> getTableModels() {
        return tableModels;
    }

    @Override
    public String getDatabaseName() {
        return DATABASE_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        switch (oldVersion)
        {
            case 1:
                updateFromPreviousVersion(database, connectionSource, oldVersion, newVersion);
                break;
            default:
                break;
        }
    }

    public void updateFromPreviousVersion(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        Log.i("TAG", "Upgrading DB from version 2");

        if (oldVersion==1)
        {
            try
            {
                int result1 = super.getDaoHelper(context).createTable(Building.class);
                Log.i("TAG", "Added table 'buildings");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

}