package jagerfield.generic.ormlite.data_generators;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.j256.ormlite.stmt.QueryBuilder;
import java.util.List;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlitelib.DaoCrud;
import jagerfield.generic.ormlitelib.DaoHelper;

public class BuildingTableReadWriteData
{
    private int amount = 0;

    boolean workState = false;

    public BuildingTableReadWriteData()
    { }

    public static BuildingTableReadWriteData getNewInstance()
    {
        return new BuildingTableReadWriteData();
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public boolean isThreadWorking() {
        return workState;
    }

    public void setThreadWorkState(boolean state) {
        this.workState = state;
    }

    public void readWriteData(Activity activity_, final String name_, int amount, final BuildingTableReadWriteData.ICall client) throws Exception
    {
        this.amount = amount;
        final Activity activity = activity_;

        checkUp(activity);

        try
        {
            DaoCrud.getInstance(activity.getApplicationContext()).clearTable(activity.getApplicationContext(), Building.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            setAmount(0);
            client.oneError("can't write to the Persons table", e);
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int counter = 0;

                while (counter<getAmount())
                {
                    Building building = new Building();
                    String name = name_ + "-" + String.valueOf(counter);
                    building.setName(name);
                    building.setAddress("Address-" + name + String.valueOf(counter));

                    try
                    {
                        if (DaoHelper.getInstance(activity.getApplicationContext()).isTableExist(Building.class))
                        {
                            DaoCrud.getInstance(activity.getApplicationContext()).add(building);
                            readRow(activity, Building.IColumns.NAME_FIELD, name, 1, client);
                        }
                        else
                        {
                            throw new IllegalStateException("Persons table is missing");
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        setAmount(0);
                        client.oneError("can't write to the Persons table", e);
                    }

                    counter++;
                }

                if(counter<getAmount())
                {
                    workState = true;
                }
                else
                {
                    workState = false;
                }

            }
        }).start();
    }

    public void readRow(Activity activity_, final String columnName, final String fieldValue, final long limit, final BuildingTableReadWriteData.ICall client) throws Exception
    {
        final Activity activity = activity_;

        checkUp(activity);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                QueryBuilder<Building, String> queryBuilder = null;

                try
                {
                    queryBuilder = DaoCrud.getInstance(activity.getApplicationContext()).queryBuilderGeneric(Building.class);
                    queryBuilder.where().eq(columnName, fieldValue);
                    queryBuilder.limit(limit);
                    queryBuilder.orderBy(columnName, true);
                    List<Building> list = queryBuilder.query();
                    final Building building = list.get(0);

                    if (list!=null && list.get(0)!=null)
                    {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                client.InsertRowInBuildingsTable(building);
                            }
                        });
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    client.oneError("can't read from the Persons table", e);
                }
            }
        }).start();
    }

    private void checkUp(Context context) throws Exception
    {
        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Context is null");
        }

        if (!DaoHelper.getInstance(context).isTableExist(Building.class))
        {
            Log.e("TAG", "Table 'Persons' is missing");
            throw new IllegalArgumentException("'buildings' table doesn't exist");
        }
    }

    public interface ICall
    {
        void InsertRowInBuildingsTable(Building building);
        void oneError(String msg, Exception e);
    }

}

//    private int amount = 0;
//
//    boolean workState = false;
//
//    public BuildingTableReadWriteData()
//    { }
//
//    public static BuildingTableReadWriteData getNewInstance()
//    {
//        return new BuildingTableReadWriteData();
//    }
//
//    public int getAmount()
//    {
//        return amount;
//    }
//
//    public void setAmount(int amount)
//    {
//        this.amount = amount;
//    }
//
//    public boolean isThreadWorking() {
//        return workState;
//    }
//
//    public void setThreadWorkState(boolean state) {
//        this.workState = state;
//    }
//
//    public void readWriteData(Activity activity_, final String name_, int amount, final ICall client) throws Exception
//    {
//        checkUp(activity_);
//        writeData(activity_, name_, amount, client);
//    }
//
//    public void writeData(Activity activity_, final String name_, int amount, final ICall client) throws Exception
//    {
//        this.amount = amount;
//        final Activity activity = activity_;
//
//        checkUp(activity);
//
//        DaoCrud.getInstance(activity.getApplicationContext()).clearTable(activity.getApplicationContext(), Building.class);
//
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                int counter = 0;
//
//                while (counter<getAmount())
//                {
//                    Building building = new Building();
//                    String name = name_ + "-" + String.valueOf(counter);
//                    building.setName(name);
//                    building.setAddress("Address-" + name + String.valueOf(counter));
//
//                    try
//                    {
//                        if (DaoHelper.getInstance(activity.getApplicationContext()).isTableExist(Building.class))
//                        {
//                            DaoCrud.getInstance(activity.getApplicationContext()).add(building);
//                            readRow(activity, Building.IColumns.NAME_FIELD, name, 1, client);
//                        }
//
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                        client.oneError("can't write to the Building table", e);
//                    }
//
//                    counter++;
//                }
//
//                if(counter<getAmount())
//                {
//                    workState = true;
//                }
//                else
//                {
//                    workState = false;
//                }
//
//            }
//        }).start();
//
//    }
//
//    public void readRow(Activity activity_, final String columnName, final String fieldValue, final long limit, final ICall client) throws Exception
//    {
//        final Activity activity = activity_;
//
//        checkUp(activity);
//
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//
//                QueryBuilder<Building, String> queryBuilder = null;
//
//                try
//                {
//                    queryBuilder = DaoCrud.getInstance(activity.getApplicationContext()).queryBuilderGeneric(Building.class);
//
//                    queryBuilder.where().eq(columnName, fieldValue);
//                    queryBuilder.limit(limit);
//                    queryBuilder.orderBy(columnName, true);
//                    List<Building> list = queryBuilder.query();
//                    final Building building = list.get(0);
//
//                    if (list!=null && list.get(0)!=null)
//                    {
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run()
//                            {
//                                client.InsertRowInBuildingsTable(building);
//                            }
//                        });
//                    }
//
//                }
//                catch (Exception e)
//                    {
//                        e.printStackTrace();
//                        client.oneError("can't read from the Building table", e);
//                    }
//            }
//        }).start();
//    }
//
//    private void checkUp(Context context) throws Exception
//    {
//        if (C.sysIsBroken(context))
//        {
//            throw new IllegalArgumentException("Context is null");
//        }
//
//        if (!DaoHelper.getInstance(context).isTableExist(Building.class))
//        {
//            Log.e("TAG", "Table 'building' is missing");
//            throw new IllegalArgumentException("'buildings' table doesn't exist");
//        }
//    }
//
//    public interface ICall
//    {
//        void InsertRowInBuildingsTable(Building building);
//        void oneError(String msg, Exception e);
//    }
//
//}
