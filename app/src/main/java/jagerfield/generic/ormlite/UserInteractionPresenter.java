package jagerfield.generic.ormlite;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.app_utils.PrefrenceUtil;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigTwo;
import jagerfield.generic.ormlite.dashboard_activities.DBAvailabilityCheck;
import jagerfield.generic.ormlite.dashboard_activities.ICallback;
import jagerfield.generic.ormlitelib.DaoHelper;

public class UserInteractionPresenter
{
    private Button createDatabaseBt;
    private Button deleteDatabaseBt;
    private Button updateDbVersionBt;
    private Button downgradeDbVersionBt;
    private TextView dbNameTv;
    private TextView dbVersionTv;
    private Activity activity;
    private Context context;
    private ICalls iCallsMainActivity;

    public UserInteractionPresenter(Activity activity, ICalls iCallsMainActivity)
    {
        this.activity = activity;
        this.iCallsMainActivity = iCallsMainActivity;
        context = activity.getApplicationContext();
        initialize();

        try
        {
            List<View> views = new ArrayList<>();
            views.add(dbNameTv);
            views.add(createDatabaseBt);

            if (C.sysIsBroken(activity, views)) { return; }

//            boolean result = DaoHelper.dropDatabase(AppDaoConfigTwo.DATABASE_NAME);
//            DaoHelper.initializeDaoAndTables(context, );

            boolean result2 = DBAvailabilityCheck.execute(activity, new ICallback() {
                @Override
                public void updateDashboardUi()
                {
                    updatedUi();
                }
            });

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        updateDashboardUi();
    }

    public void updatedUi()
    {
        if (C.sysIsBroken(activity)) { return;}

        getDaoDbVersion();
    }

    private void initialize()
    {
        if (activity==null) { Log.e("TAG", "Activity is null"); return; }
        createDatabaseBt = (Button) activity.findViewById(R.id.createDatabaseBt);
        deleteDatabaseBt = (Button) activity.findViewById(R.id.deleteDatabaseBt);
        updateDbVersionBt = (Button) activity.findViewById(R.id.updateDbVersionBt);
        downgradeDbVersionBt = (Button) activity.findViewById(R.id.downgradeDbVersionBt);
//        createBuidlingsTableBt = (Button) activity.findViewById(R.id.createBuidlingsTableBt);
//        deleteBuidlingsTableBt = (Button) activity.findViewById(R.id.deleteBuidlingsTableBt);
//        loadBuidlingsTableBt = (Button) activity.findViewById(R.id.loadBuidlingsTableBt);
//        clearBuidlingsTableBt = (Button) activity.findViewById(R.id.clearBuidlingsTableBt);
//        loadPersonsTableBt = (Button) activity.findViewById(R.id.loadPersonsTableBt);
//        clearPersonsTableBt = (Button) activity.findViewById(R.id.clearPersonsTableBt);
//        loadEmployeesTableBt = (Button) activity.findViewById(R.id.loadEmployeesTableBt);
//        clearEmployeesTableBt = (Button) activity.findViewById(R.id.clearEmployeesTableBt);
//        concurrentListBt = (Button) activity.findViewById(R.id.concurrentListBt);
//        concurrentLoadBt = (Button) activity.findViewById(R.id.concurrentLoadBt);
//        concurrentClearBt = (Button) activity.findViewById(R.id.concurrentClearBt);
        dbNameTv = (TextView) activity.findViewById(R.id.dbNameTv);
        dbVersionTv = (TextView) activity.findViewById(R.id.dbVersionTv);
//        isBuildingsTableTv = (TextView) activity.findViewById(R.id.isBuildingsTableTv);
//        buildingsEntriesTv = (TextView) activity.findViewById(R.id.buildingsEntriesTv);
//        personsEntriesTv = (TextView) activity.findViewById(R.id.personsEntriesTv);
//        employeesEntriesTv = (TextView) activity.findViewById(R.id.employeesEntriesTv);


        createDatabaseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                List<View> views = new ArrayList<>();
                views.add(dbNameTv);
                views.add(createDatabaseBt);

                try
                {
                    if (C.sysIsBroken(activity, views)) { return; }

                    C.createAppDB(activity.getApplicationContext());

                    boolean result = DBAvailabilityCheck.execute(activity, new ICallback() {
                        @Override
                        public void updateDashboardUi()
                        {
                            updatedUi();
                        }
                    });
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        deleteDatabaseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    List<View> views = new ArrayList<>();
                    views.add(dbNameTv);
                    views.add(createDatabaseBt);

                    if (C.sysIsBroken(activity, views)) { return; }

                    boolean result = DaoHelper.dropDatabase(AppDaoConfigTwo.DATABASE_NAME);

                    boolean result2 = DBAvailabilityCheck.execute(activity, new ICallback() {
                        @Override
                        public void updateDashboardUi()
                        {
                            updatedUi();
                        }
                    });

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        updateDbVersionBt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (C.sysIsBroken(activity)) {
                    return ;
                }

                final int version = getDaoDbVersion();

                if (version==0)
                {
                    Log.e("TAG", "DB version is zero");
                    return;
                }

                int nextVersion = version + 1;

                executeDbVersionChange(nextVersion);
            }
        });

        downgradeDbVersionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (C.sysIsBroken(activity, dbVersionTv)) {
                    return;
                }

                final int version = getDaoDbVersion();

                int nextVersion = version - 1;

                executeDbVersionChange(nextVersion);
            }
        });

//        createBuidlingsTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        deleteBuidlingsTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        loadBuidlingsTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        clearBuidlingsTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        loadPersonsTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        clearPersonsTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        loadEmployeesTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        clearEmployeesTableBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        concurrentListBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        concurrentLoadBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        concurrentClearBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    private void executeDbVersionChange(int nextVersion)
    {
        if (nextVersion>3)
        {
            nextVersion = 3;
            iCallsMainActivity.showMessage("Highest DB version is 3, app will restart now");
        }
        else if (nextVersion<=0)
        {
            nextVersion = 1;
            iCallsMainActivity.showMessage("Lowest DB version is 1, app will restart now");
        }
        else
        {
            iCallsMainActivity.showMessage("App will restart now to change the DB version");
        }

        PrefrenceUtil.setInt(activity.getApplicationContext(), C.KEY_APPDB_VERSION, nextVersion);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                C.restartApplication(activity.getApplicationContext());
            }
        }, 2000);
    }

//    private boolean configureDatabaseButtons()
//    {
//        String dbName = "";
//        boolean result = false;
//
//        try
//        {
//            dbName = getCurrentDbName().trim();
//            if(dbName !=null && !dbName.isEmpty())
//            {
//                dbNameTv.setText(dbName);
//                result = true;
//                setDashboardTableViewsStates(true);
//                C.setButtonState(context, false, createDatabaseBt);
//                updateDashboardUi();
//
//            }
//            else
//            {
//                dbNameTv.setText("None");
//                result = false;
//                setDashboardTableViewsStates(false);
//                C.setButtonState(context, true, createDatabaseBt);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            result = false;
//        }
//
//        return result;
//    }

    private boolean isDBExists() throws Exception
    {
        boolean result = false;
        String dbName = getCurrentDbName().trim();

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

    private String getCurrentDbName() throws Exception
    {
        String result = "";
        String[] array = activity.databaseList();
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

//    private void setDashboardTableViewsStates(boolean state) throws Exception
//    {
//        int countRows = 0;
//        try
//        {
//            TableLayout dashboardTable = (TableLayout) activity.findViewById(R.id.dashboardTable);
//
//            if (C.sysIsBroken(activity, dashboardTable)) {
//                return ;
//            }
//
//            countRows = dashboardTable.getChildCount();
//
//            for(int i=0; i < countRows; i++)
//            {
//                View rowChildView = dashboardTable.getChildAt(i);
//                int resID = rowChildView.getId();
//
//                if (rowChildView instanceof TableRow)
//                {
//                    int countViews = ((TableRow) rowChildView).getChildCount();
//                    for(int j=0; j < countViews; j++)
//                    {
//                        View view = ((TableRow) rowChildView).getChildAt(j);
//                        if (view instanceof Button)
//                        {
//                            view.setEnabled(state);
//                            if(state)
//                            {
//                                C.setButtonState(context, true, (Button) view);
//                            }
//                            else
//                            {
//                                C.setButtonState(context, false, (Button) view);
//                            }
//                        }
//                        else if (view instanceof TextView)
//                        {
//                            if(!state)
//                            {
//                                String tag = null;
//                                if (view.getTag()!=null)
//                                {
//                                    tag = view.getTag().toString();
//                                    if (tag.equals(C.TAG_DASHBOARD_TV))
//                                    {
//                                        ((TextView)view).setText("");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }

    private int getDaoDbVersion()
    {
        if (C.sysIsBroken(activity, dbVersionTv)) {
            return 0;
        }

        int version = 0;
        try
        {
            version = DaoHelper.getInstance(activity).getDatabaseVersion();
            dbVersionTv.setText(String.valueOf(version));
            if (version==1)
            {
                C.setButtonState(context, false, downgradeDbVersionBt);
                C.setButtonState(context, true, updateDbVersionBt);
            }
            else if (version==2)
            {
                C.setButtonState(context, true, downgradeDbVersionBt);
                C.setButtonState(context, true, updateDbVersionBt);
            }
            else if (version==3)
            {
                C.setButtonState(context, true, downgradeDbVersionBt);
                C.setButtonState(context, false, updateDbVersionBt);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            version = 0;
            dbVersionTv.setText("Error");
        }

        return version;
    }

    public interface ICalls
    {
        public void showMessage(String msg);
    }
}











