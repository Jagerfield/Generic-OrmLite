package jagerfield.generic.ormlite.dashboard_presenters.services;

import android.app.Activity;
import android.content.Context;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigOne;
import jagerfield.generic.ormlite.data_generators.BuildingTableReadWriteData;
import jagerfield.generic.ormlite.data_generators.PersonTableReadWriteData;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlite.recycler_adapters.BuildingListViewAdaptor;
import jagerfield.generic.ormlite.recycler_adapters.PersonListViewAdaptor;
import jagerfield.generic.ormlitelib.DaoHelper;

public class RecyclersUiService
{

    public RecyclersUiService()
    { }

    public static RecyclersUiService getNewInstance()
    {
        return new RecyclersUiService();
    }

    public void loadRecyclers(final Activity activity, int amount) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        Context context = activity.getApplicationContext();

        int dbVersion = DbVersionService.getDaoDbVersion(context);

        if (dbVersion==1)
        {
            writeReadPersonTable(activity, amount);
        }
        else if (dbVersion==2)
        {
            writeReadPersonTable(activity, amount);
            writeReadBuildingTable(activity, amount);
        }
        else if (dbVersion==3)
        {
            PersonTableReadWriteData.getNewInstance().readWriteData(activity, "Jack", 3, new PersonTableReadWriteData.ICall() {
                @Override
                public void InsertRowInPersonsTable(Person person)
                {
                    String name = person.getName();
                    String str;
                }

                @Override
                public void oneError(String msg, Exception e)
                {
                    String str;
                }
            });

            BuildingTableReadWriteData.getNewInstance().readWriteData(activity, "Building_B", 3, new BuildingTableReadWriteData.ICall() {
                @Override
                public void InsertRowInBuildingsTable(Building building)
                {
                    String name = building.getName();
                    String str;
                }

                @Override
                public void oneError(String msg, Exception e)
                {
                    String str;
                }
            });
        }
    }

    private void writeReadPersonTable(final Activity activity, int amount) throws Exception
    {
        Context context = activity.getApplicationContext();

        if (!DaoHelper.getInstance(context).isTableExist(Person.class))
        {
            throw new IllegalArgumentException("Person table is missing");
        }

        PersonTableReadWriteData.getNewInstance().readWriteData(activity, "Peter", amount, new PersonTableReadWriteData.ICall()
        {
            @Override
            public void InsertRowInPersonsTable(final Person person)
            {
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        PersonListViewAdaptor.getInstance().updateList(person);
                    }
                });

                String name = person.getName();
            }

            @Override
            public void oneError(String msg, Exception e)
            {
                throw new IllegalArgumentException("Couldn't read write to Person table", e);
            }
        });
    }

    private void writeReadBuildingTable(final Activity activity, int amount) throws Exception
    {
        Context context = activity.getApplicationContext();

        if (!DaoHelper.getInstance(context).isTableExist(Building.class))
        {
            throw new IllegalArgumentException("Person table is missing");
        }

        BuildingTableReadWriteData.getNewInstance().readWriteData(activity, "Building_A", amount, new BuildingTableReadWriteData.ICall() {
            @Override
            public void InsertRowInBuildingsTable(final Building building)
            {
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        BuildingListViewAdaptor.getInstance().updateList(building);
                    }
                });

                String name = building.getName();
            }

            @Override
            public void oneError(String msg, Exception e)
            {
                throw new IllegalArgumentException("Couldn't read write to Buildings table", e);
            }
        });
    }
}












