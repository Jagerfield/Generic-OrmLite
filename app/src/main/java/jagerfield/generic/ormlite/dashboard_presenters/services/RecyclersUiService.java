package jagerfield.generic.ormlite.dashboard_presenters.services;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.data_generators.BuildingTableReadWriteData;
import jagerfield.generic.ormlite.models.Building;

public class RecyclersUiService
{

    public RecyclersUiService()
    { }

    public static RecyclersUiService getNewInstance()
    {
        return new RecyclersUiService();
    }

    public void loadRecyclers(Activity activity) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        Context context = activity.getApplicationContext();
        RecyclerView recyclerViewOne = (RecyclerView) activity.findViewById(R.id.recyclerViewOne);
        RecyclerView recyclerViewTwo = (RecyclerView) activity.findViewById(R.id.recyclerViewTwo);
        RecyclerView recyclerViewThree = (RecyclerView) activity.findViewById(R.id.recyclerViewThree);

        int dbVersion = DbVersionService.getDaoDbVersion(context);

        if (dbVersion==1)
        {

        }
        else if (dbVersion==2)
        {
            BuildingTableReadWriteData.getNewInstance().readWriteData(activity, "Peter", 3, new BuildingTableReadWriteData.ICall() {
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
        else if (dbVersion==3)
        {

        }
    }


}












