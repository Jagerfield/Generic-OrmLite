package jagerfield.generic.ormlite.dao_config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.support.ConnectionSource;
import java.util.HashSet;
import java.util.Set;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.models.Building;
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
            tableModels.add(Building.class);
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
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
