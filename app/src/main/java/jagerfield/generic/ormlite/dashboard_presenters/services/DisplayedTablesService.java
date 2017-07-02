package jagerfield.generic.ormlite.dashboard_presenters.services;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;

public class DisplayedTablesService
{
    public DisplayedTablesService()
    {

    }

    public DisplayedTablesService getNewInstance()
    {
        return new DisplayedTablesService();
    }

    public void adjustRecyclersLayout(Activity activity) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

//        LinearLayout recyclers = (LinearLayout) activity.findViewById(R.id.recyclers);

        RecyclerView recyclerViewOne = (RecyclerView) activity.findViewById(R.id.recyclerViewOne);
        RecyclerView recyclerViewTwo = (RecyclerView) activity.findViewById(R.id.recyclerViewTwo);
        RecyclerView recyclerViewThree = (RecyclerView) activity.findViewById(R.id.recyclerViewThree);



    }


}
