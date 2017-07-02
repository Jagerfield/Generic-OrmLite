package jagerfield.generic.ormlite.dashboard_presenters.services;


import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;

public class DashboardViewsStateService
{
    public DashboardViewsStateService() {
    }

    public static DashboardViewsStateService getNewInstance()
    {
        return new DashboardViewsStateService();
    }

    public void setDashboardTableViewsStates(Activity activity, boolean state) throws Exception
    {
        if (C.sysIsBroken(activity)) {
            throw new IllegalArgumentException("Activity is null") ;
        }

        int countRows = 0;
        Context context = activity.getApplicationContext();

        TableLayout dashboardTable = (TableLayout) activity.findViewById(R.id.dashboardTable);

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

    public void setButtonState(Context context, boolean state, Button button)
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
