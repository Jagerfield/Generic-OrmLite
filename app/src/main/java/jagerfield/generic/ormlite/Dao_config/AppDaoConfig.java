package jagerfield.generic.ormlite.Dao_config;

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
    private final int DATABASE_VERSION = 12;
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

        if (oldVersion==11)
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



/*

        I added zip and country columns to the buildings table but the Tableinfo can't detct them. You need to remove the table now to get things to work.
        try
        {
            if (DaoHelper.getInstance(context).isTableExist(Building.class))
            {
                TableInfo tableInfo = ((BaseDaoImpl) super.getDaoEntity(context, Building.class)).getTableInfo();

                if(tableInfo.hasColumnName("zip"))
                {
                    String str  = "";
                }
                else
                {
                    int result2 = super.getDaoEntity(context, Building.class).executeRaw("ALTER TABLE `buildings` ADD COLUMN zip STRING;");

                    tableInfo = ((BaseDaoImpl) super.getDaoEntity(context, Building.class)).getTableInfo();

                    if(tableInfo.hasColumnName("zip"))
                    {
                        String str  = "";
                    }
                }

                String str  = "";

//                GenericRawResults<String[]> results =
//                        dao.queryRaw("SELECT name FROM sqlite_master WHERE type = 'table'");
//                for (String[] result : results) {
//                    System.out.println("One table is: " + result[0]);
//                }
            }
            else
            {
                int result1 = super.getDaoHelper(context).createTable(Building.class);
                int result2 = super.getDaoEntity(context, Building.class).executeRaw("ALTER TABLE `buildings` ADD COLUMN country STRING;");

                TableInfo tableInfo = ((BaseDaoImpl) super.getDaoEntity(context, Building.class)).getTableInfo();

                if(tableInfo.hasColumnName("country"))
                {
                    String str  = "";
                }

                String str = "";
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


 */


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