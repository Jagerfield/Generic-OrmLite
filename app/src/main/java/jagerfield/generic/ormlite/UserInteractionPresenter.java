package jagerfield.generic.ormlite;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigTwo;
import jagerfield.generic.ormlite.dashboard_presenters.DbAvailabilityPresenter;
import jagerfield.generic.ormlite.dashboard_presenters.DbVersionPresenter;
import jagerfield.generic.ormlitelib.DaoHelper;

public class UserInteractionPresenter
{
    private Button createDatabaseBt;
    private Button deleteDatabaseBt;
    private Button updateDbVersionBt;
    private Button downgradeDbVersionBt;
    private Activity activity;
    private Context context;
    private ICallback iCallbackMainActivity;

    public UserInteractionPresenter(Activity activity, ICallback iCallbackMainActivity)
    {
        this.activity = activity;
        this.iCallbackMainActivity = iCallbackMainActivity;
        context = activity.getApplicationContext();
        initialize();

        try
        {
            DbAvailabilityPresenter.execute().configureDatabaseButtons(activity, new jagerfield.generic.ormlite.dashboard_presenters.ICallback()
            {   @Override
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

    public void updatedUi()
    {
        if (C.sysIsBroken(activity)) { return;}

        try
        {
            DbVersionPresenter.execute(activity).getDaoDbVersion();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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

//        isBuildingsTableTv = (TextView) activity.findViewById(R.id.isBuildingsTableTv);
//        buildingsEntriesTv = (TextView) activity.findViewById(R.id.buildingsEntriesTv);
//        personsEntriesTv = (TextView) activity.findViewById(R.id.personsEntriesTv);
//        employeesEntriesTv = (TextView) activity.findViewById(R.id.employeesEntriesTv);

        createDatabaseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    C.createAppDB(activity.getApplicationContext());

                    DbAvailabilityPresenter.execute().configureDatabaseButtons(activity, new jagerfield.generic.ormlite.dashboard_presenters.ICallback() {
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
                    boolean result = DaoHelper.dropDatabase(AppDaoConfigTwo.DATABASE_NAME);

                    DbAvailabilityPresenter.execute().configureDatabaseButtons(activity, new jagerfield.generic.ormlite.dashboard_presenters.ICallback() {
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
                try
                {
                    DbVersionPresenter.execute(activity).upgrade(iCallbackMainActivity);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        downgradeDbVersionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    DbVersionPresenter.execute(activity).downgrade(iCallbackMainActivity);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
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

    public interface ICallback
    {
        public void showMessage(String msg);
    }
}











