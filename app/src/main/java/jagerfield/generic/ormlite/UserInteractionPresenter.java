package jagerfield.generic.ormlite;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigVOne;
import jagerfield.generic.ormlitelib.DaoHelper;

public class UserInteractionPresenter
{
    private RelativeLayout dashboard;
    private Button createDatabaseBt;
    private Button deleteDatabaseBt;
    private Button updateDbVersionBt;
    private Button downgradeDbVersionBt;
    private Button createBuidlingsTableBt;
    private Button deleteBuidlingsTableBt;
    private Button loadBuidlingsTableBt;
    private Button clearBuidlingsTableBt;
    private Button loadPersonsTableBt;
    private Button clearPersonsTableBt;
    private Button loadEmployeesTableBt;
    private Button clearEmployeesTableBt;
    private Button concurrentListBt;
    private Button concurrentLoadBt;
    private Button concurrentClearBt;

    private TextView dbExistsTv;
    private TextView dbVersionTv;
    private TextView isBuildingsTableTv;
    private TextView buildingsEntriesTv;
    private TextView personsEntriesTv;
    private TextView employeesEntriesTv;

    private Activity activity;

    public UserInteractionPresenter(Activity activity)
    {
        this.activity = activity;

        interactions();

    }

    private void interactions()
    {
        if (activity==null)
        {
            Log.e("TAG", "Activity is null");
            return;
        }

        initiate();

        isDbExist();
    }

    private void initiate()
    {
        dashboard = (RelativeLayout) activity.findViewById(R.id.dashboard);
        createDatabaseBt = (Button) activity.findViewById(R.id.createDatabaseBt);
        deleteDatabaseBt = (Button) activity.findViewById(R.id.deleteDatabaseBt);
//        updateDbVersionBt = (Button) activity.findViewById(R.id.updateDbVersionBt);
//        downgradeDbVersionBt = (Button) activity.findViewById(R.id.downgradeDbVersionBt);
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
        dbExistsTv = (TextView) activity.findViewById(R.id.dbNameTv);
//        dbVersionTv = (TextView) activity.findViewById(R.id.dbVersionTv);
//        isBuildingsTableTv = (TextView) activity.findViewById(R.id.isBuildingsTableTv);
//        buildingsEntriesTv = (TextView) activity.findViewById(R.id.buildingsEntriesTv);
//        personsEntriesTv = (TextView) activity.findViewById(R.id.personsEntriesTv);
//        employeesEntriesTv = (TextView) activity.findViewById(R.id.employeesEntriesTv);


        createDatabaseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (activity==null)
                {
                    return;
                }

                try
                {
                    DaoHelper.initializeDaoAndTables(activity, new AppDaoConfigVOne(activity));
                    isDbExist();
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
                    boolean result = DaoHelper.dropDatabase(AppDaoConfigVOne.DATABASE_NAME);
                    isDbExist();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
//
//        updateDbVersionBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        downgradeDbVersionBt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
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

    private boolean isDbExist()
    {
        String dbName = "";
        boolean result = false;

        try
        {
            dbName = getCurrentDbName().trim();
            if(dbName !=null && !dbName.isEmpty())
            {
                dbExistsTv.setText("Yes");
                result = true;
                setDashboardTableViewsStates(true);
                createDatabaseBt.setEnabled(false);
            }
            else
            {
                dbExistsTv.setText("No");
                result = false;
                setDashboardTableViewsStates(false);
                createDatabaseBt.setEnabled(true);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    private void setDashboardTableViewsStates(boolean state) throws Exception
    {
        TableLayout dashboardTable = (TableLayout) activity.findViewById(R.id.dashboardTable);

        int countRows = dashboardTable.getChildCount();

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
                    }

//                    if (view instanceof TextView)
//                    {
//                        view.setEnabled(state);
//                    }
                }
            }
        }
    }
}











