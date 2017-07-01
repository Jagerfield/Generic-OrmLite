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

public class AppDaoConfigOne extends DaoConfiguration
{
    private Set<Class> tableModels;
    public final static String DATABASE_NAME = C.DB_GIVEN_NAME;
    public final static int DATABASE_VERSION = 1;
    private Context context;

    public AppDaoConfigOne(Context context)
    {
        this.context = context;
        tableModels = new HashSet<>();

        if(DATABASE_VERSION == 1)
        {
            tableModels.add(Person.class);
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
             default:
                break;
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        int result = -3;

        switch (oldVersion)
        {
            case 2:
                downgradeToVersionOne(newVersion);
                break;

            case 3:
                downgradeToVersionOne(newVersion);
                break;

            default:
                break;
        }
    }

    private void downgradeToVersionOne(int newVersion)
    {
        if (newVersion == 1)
        {
            try
            {
                if (DaoHelper.getInstance(context).isTableExist(Building.class))
                {
                    super.getDaoHelper(context).dropTable(Building.class, true);
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                if (DaoHelper.getInstance(context).isTableExist(Employee.class))
                {
                    super.getDaoHelper(context).dropTable(Employee.class, true);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            try
            {
                if (!DaoHelper.getInstance(context).isTableExist(Building.class))
                {
                    Log.i("TAG", "Added table 'buildings");
                }

                if (!DaoHelper.getInstance(context).isTableExist(Employee.class))
                {
                    Log.i("TAG", "Added table 'employees");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
