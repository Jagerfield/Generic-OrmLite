package jagerfield.generic.ormlite.dashboard_presenters;

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

public class DbAvailabilityPresenter
{
    public DbAvailabilityPresenter()
    { }
    
    public static DbAvailabilityPresenter execute() throws Exception
    {
        return new DbAvailabilityPresenter();
    }

    public boolean configureDatabaseButtons(Activity activity, ICallback iCallback) throws Exception
    {
        String dbName = "";
        boolean result = false;
        Context context = activity.getApplicationContext();

        TableLayout dashboardTable = (TableLayout) activity.findViewById(R.id.dashboardTable);
        TextView dbNameTv = (TextView) activity.findViewById(R.id.dbNameTv);
        Button createDatabaseBt = (Button) activity.findViewById(R.id.createDatabaseBt);

        try
        {
            dbName = getCurrentDbName(context).trim();
            if(dbName !=null && !dbName.isEmpty())
            {
                dbNameTv.setText(dbName);
                result = true;
                setDashboardTableViewsStates(context, true, dashboardTable);
                setButtonState(context, false, createDatabaseBt);
                iCallback.updateDashboardUi();
            }
            else
            {
                dbNameTv.setText("None");
                result = false;
                setDashboardTableViewsStates(context, false, dashboardTable);
                setButtonState(context, true, createDatabaseBt);
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

    private void setDashboardTableViewsStates(Context context, boolean state, TableLayout dashboardTable) throws Exception
    {
        int countRows = 0;
        try
        {
            if (C.sysIsBroken(context, dashboardTable)) {
                return ;
            }

            countRows = dashboardTable.getChildCount();

            for(int i=0; i < countRows; i++)
            {
                View rowChildView = dashboardTable.getChildAt(i);
                int resID = rowChildView.getId();

                if (rowChildView instanceof TableRow)
                {
                    int countViews = ((TableRow) rowChildView).getChildCount();
                    for(int j=0; j < countViews; j++)
                    {
                        View view = ((TableRow) rowChildView).getChildAt(j);
                        if (view instanceof Button)
                        {
                            view.setEnabled(state);
                            if(state)
                            {
                                setButtonState(context, true, (Button) view);
                            }
                            else
                            {
                                setButtonState(context,false, (Button) view);
                            }
                        }
                        else if (view instanceof TextView)
                        {
                            if(!state)
                            {
                                String tag = null;
                                if (view.getTag()!=null)
                                {
                                    tag = view.getTag().toString();
                                    if (tag.equals(C.TAG_DASHBOARD_TV))
                                    {
                                        ((TextView)view).setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void setButtonState(Context context, boolean state, Button button)
    {
        if (C.sysIsBroken(context, button))
        {
            return;
        }

        if (state)
        {
            button.setEnabled(true);
            button.setTextColor(ContextCompat.getColor(context, R.color.greendark));
        }
        else
        {
            button.setEnabled(false);
            button.setTextColor(ContextCompat.getColor(context, R.color.greymedium));
        }
    }
}
