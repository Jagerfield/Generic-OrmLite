package jagerfield.generic.ormlite.DaoConfig;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.support.ConnectionSource;
import java.util.HashSet;
import java.util.Set;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoConfiguration;

public class AppDaoConfig extends DaoConfiguration
{
    private Set<Class> tableModels;
    private final String DATABASE_NAME = "OrmLiteTest.db";
    private final int DATABASE_VERSION = 11;
    private Context context;

    public AppDaoConfig(Context context)
    {
        this.context = context;
        tableModels = new HashSet<>();
        tableModels.add(Person.class);
        tableModels.add(Building.class);
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
            case DATABASE_VERSION -1:
                updateFromPreviousVersion(database, connectionSource, oldVersion, newVersion);
                break;
            default:
                break;
        }
    }

    public void updateFromPreviousVersion(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        Log.i("TAG", "Upgrading DB from version 2");

    }

}
