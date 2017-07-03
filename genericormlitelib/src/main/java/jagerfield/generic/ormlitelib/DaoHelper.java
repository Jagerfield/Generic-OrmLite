package jagerfield.generic.ormlitelib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.Serializable;
import java.util.Set;

public class DaoHelper<T> extends OrmLiteSqliteOpenHelper
{
    private static DaoHelper instance;
    private static DaoConfiguration daoConfiguration;
    private static SQLiteDatabase sqLiteDatabase;

    private DaoHelper(Context context)
    {
        super(context, daoConfiguration.getConfigDatabaseName(), null, daoConfiguration.getConfigDatabaseVersion());
    }

    public synchronized static DaoHelper getInstance(Context context)
    {
        if (instance==null)
        {
            instance= new DaoHelper(context);
            sqLiteDatabase = instance.getWritableDatabase();
        }

        return instance;
    }

    public static void initializeDaoAndTables(Context context, DaoConfiguration configManager)
    {
        daoConfiguration = configManager;
        DaoHelper daoHelper = DaoHelper.getInstance(context);
    }

    public synchronized static SQLiteDatabase getSqLiteDatabase()
    {
        return sqLiteDatabase;
    }

    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        super.onConfigure(db);
        db.getVersion();
        this.sqLiteDatabase = db;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            createTables(daoConfiguration.getAppTableModels());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public synchronized static void setDaoHelperInstanceToNull()
    {
        instance = null;
    }

    public synchronized static boolean dropDatabase(Context context, String dbName)  throws Exception
    {
        if (dbName == null || dbName.isEmpty())
        {
            throw new IllegalArgumentException("dbName is null or empty");
        }
        boolean result = context.deleteDatabase(dbName);
        instance = null;
        return result;
    }

    public synchronized void createTables(Set<Class> S) throws Exception
    {
        for (Class a: S)
        {
            TableUtils.createTableIfNotExists(getConnectionSource(), a);
        }
    }

    public synchronized boolean isTableExist(Class T) throws Exception
    {
        Dao dao = getDao(T);
        return dao.isTableExists();
    }

    public synchronized int createTable(Class T) throws Exception
    {
        return TableUtils.createTableIfNotExists(getConnectionSource(), T);
    }

    public synchronized int dropTable(Class T, boolean ignoreError) throws Exception
    {
        return TableUtils.dropTable(getConnectionSource(), T, ignoreError);
    }

    public synchronized int clearTableData(Context context, Class T) throws Exception
    {
        return TableUtils.clearTable(getConnectionSource(), T);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        Log.i(DaoHelper.class.getName(), "Updating the DB");
        try
        {
            daoConfiguration.onUpgrade(database, connectionSource, oldVersion, newVersion);
        }
        catch (Exception e)
        {
            // Table exists ?
            e.printStackTrace();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i(DaoHelper.class.getName(), "Downgrading the DB");

        try
        {
            daoConfiguration.onDowngrade(db, oldVersion, newVersion);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public boolean isOpen()
    {
        return super.isOpen();
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return super.getConnectionSource();
    }

    @Override
    public String getDatabaseName() {
        return super.getDatabaseName();
    }

    public synchronized int getDatabaseVersion()
    {
        return getWritableDatabase().getVersion();
    }

//    public synchronized GenericRawResults getDbTablesList(Class T) throws Exception
//    {
//        Dao dao = getDaoEntity(T);
//        GenericRawResults<String[]> results = dao.queryRaw("SELECT name FROM sqlite_master WHERE type = 'table'");
//
//        return results;
//    }

    @Override
    public void close()
    {
        try
        {
            instance.close();
            instance = null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            instance = null;
        }

        super.close();
    }

}

