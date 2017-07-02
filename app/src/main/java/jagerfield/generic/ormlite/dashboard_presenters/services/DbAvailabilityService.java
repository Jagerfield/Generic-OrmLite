package jagerfield.generic.ormlite.dashboard_presenters.services;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;

public class DbAvailabilityService
{
    public DbAvailabilityService()
    { }
    
    public static DbAvailabilityService getNewInstance() throws Exception
    {
        return new DbAvailabilityService();
    }

    public boolean configureDatabaseButtons(Activity activity, IServiceCallback iServiceCallback) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        String dbName = "";
        boolean result = false;
        Context context = activity.getApplicationContext();

        TextView dbNameTv = (TextView) activity.findViewById(R.id.dbNameTv);
        Button createDatabaseBt = (Button) activity.findViewById(R.id.createDatabaseBt);

        DashboardViewsStateService obj = DashboardViewsStateService.getNewInstance();

        try
        {
            dbName = getCurrentDbName(context).trim();
            if(dbName !=null && !dbName.isEmpty())
            {
                dbNameTv.setText(dbName);
                result = true;
                obj.setDashboardTableViewsStates(activity, true);
                obj.setButtonState(context, false, createDatabaseBt);
                iServiceCallback.onServiceComplete("");
            }
            else
            {
                dbNameTv.setText("None");
                result = false;
                obj.setDashboardTableViewsStates(activity, false);
                obj.setButtonState(context, true, createDatabaseBt);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    public boolean isDBExists(Context context) throws Exception
    {
        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        boolean result = false;
        String dbName = getCurrentDbName(context).trim();

        if(dbName !=null && !dbName.isEmpty())
        {
            result = true;
        }
        else
        {
            result = false;
        }

        return result;
    }

    public String getCurrentDbName(Context context) throws Exception
    {
        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        String result = "";
        String[] array = context.databaseList();
        List<String> list = new ArrayList<>(Arrays.asList(array));
        for (String dbName: list)
        {
            boolean b = dbName.endsWith(".db");
            if (b)
            {
                result = dbName;
                break;
            }
        }

        return result;
    }

}
