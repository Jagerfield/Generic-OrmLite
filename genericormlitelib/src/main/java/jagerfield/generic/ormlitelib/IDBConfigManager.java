package jagerfield.generic.ormlitelib;

import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.support.ConnectionSource;
import java.util.Set;

public interface IDBConfigManager
{
    DaoHelper getDaoGeneric();
    void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion);
    Set<Class> getTableModels();
    String getDatabaseName();
    int getDatabaseVersion();
}
