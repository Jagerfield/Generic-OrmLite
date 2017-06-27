package jagerfield.generic.ormlitelib;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.Set;


public class DaoHelper<T> extends OrmLiteSqliteOpenHelper
{
    private static DaoHelper instance;
    private static DaoConfiguration daoConfiguration;
    private static Context context;

    private DaoHelper(Context context)
    {
        super(context, daoConfiguration.getDatabaseName(), null, daoConfiguration.getDatabaseVersion());
        this.context = context;
    }

    public synchronized static DaoHelper getInstance(Context context)
    {
        if (instance==null)
        {
            instance= new DaoHelper(context);
            instance.getWritableDatabase();
        }

        return instance;
    }

    private synchronized Dao getDaoEntity(Class T) throws Exception
    {
        Dao dao = DaoHelper.getInstance(context).getDao(T);
        return dao;
    }

    public static void InitializeDaoAndTables(Application application, DaoConfiguration configManager)
    {
        daoConfiguration = configManager;
        DaoHelper daoHelper = DaoHelper.getInstance(application.getApplicationContext());
    }

    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        super.onConfigure(db);
        db.getVersion();
        String str = "";
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource)
    {
        try
        {
            createTables(daoConfiguration.getTableModels());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean deleteDatabase(String dbName)  throws Exception
    {
//        String [] array = context.databaseList();
//        List<String> list = new ArrayList<String>(Arrays.asList(array));
//        ListIterator<String> iterator = list.listIterator();
//
//        try
//        {
//            while(iterator.hasNext())
//            {
//                String entry = iterator.next();
//                if (!entry.endsWith(".db"))
//                {
//                    iterator.remove();
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return false;
//        }

        if (dbName == null || dbName.isEmpty())
        {
            throw new IllegalArgumentException("dbName is null or empty");
        }

        return context.deleteDatabase(dbName);
    }

    public void createTables(Set<Class> S) throws Exception
    {
        for (Class a: S)
        {
            TableUtils.createTableIfNotExists(getConnectionSource(), a);
        }
    }

    public boolean isTableExist(Class T) throws Exception
    {
        Dao dao = getDao(T);
        return dao.isTableExists();
    }

    public int createTable(Class T) throws Exception
    {
        return TableUtils.createTableIfNotExists(getConnectionSource(), T);
    }

    public int dropTable(Class T, boolean ignoreError) throws Exception
    {
        return TableUtils.dropTable(getConnectionSource(), T, ignoreError);
    }

    public int clearData(Context context, Class T) throws Exception
    {
        return TableUtils.clearTable(getConnectionSource(), T);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        Log.i(DaoHelper.class.getName(), "Updating the DB, all previously stored data would be lost");
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
    public void close()
    {
        super.close();

        if (instance!= null)
        {
            instance.close();
            instance = null;
        }
    }

}

