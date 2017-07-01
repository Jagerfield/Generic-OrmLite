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
                upgradeFromVersionOne(database, connectionSource, oldVersion, newVersion);
                break;
            case 2:
                upgradeFromVersionTwo(database, connectionSource, oldVersion, newVersion);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void upgradeFromVersionOne(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        if (oldVersion==1)
        {
            try
            {
                upgradeFromOlderVersions(newVersion);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void upgradeFromVersionTwo(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        if (oldVersion==2)
        {
            try
            {
                upgradeFromOlderVersions(newVersion);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void upgradeFromOlderVersions(int newVersion) throws Exception
    {
        int result1;
        int result2;

        if(newVersion== 3)
        {
            if (!DaoHelper.getInstance(context).isTableExist(Building.class))
            {
                result1 = super.getDaoHelper(context).createTable(Building.class);
            }

            if (!DaoHelper.getInstance(context).isTableExist(Employee.class))
            {
                result2 = super.getDaoHelper(context).createTable(Employee.class);
            }

            if (DaoHelper.getInstance(context).isTableExist(Building.class))
            {
                Log.i("TAG", "Added table 'buildings");
            }

            if (DaoHelper.getInstance(context).isTableExist(Employee.class))
            {
                Log.i("TAG", "Added table 'emplyees");
            }
        }
    }

}