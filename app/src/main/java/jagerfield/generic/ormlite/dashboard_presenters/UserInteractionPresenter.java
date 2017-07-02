package jagerfield.generic.ormlite.dashboard_presenters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigTwo;
import jagerfield.generic.ormlite.dashboard_presenters.services.AppRestartService;
import jagerfield.generic.ormlite.dashboard_presenters.services.DbAvailabilityService;
import jagerfield.generic.ormlite.dashboard_presenters.services.DbVersionService;
import jagerfield.generic.ormlite.dashboard_presenters.services.IServiceCallback;
import jagerfield.generic.ormlitelib.DaoHelper;

public class UserInteractionPresenter
{
    private Button createDatabaseBt;
    private Button dropDatabaseBt;
    private Button updateDbVersionBt;
    private Button downgradeDbVersionBt;
    private Activity activity;
    private Context context;
    private IInteractionCallback iCallbackMainActivity;

    public UserInteractionPresenter(Activity activity, IInteractionCallback iCallbackMainActivity)
    {
        this.activity = activity;
        this.iCallbackMainActivity = iCallbackMainActivity;
        context = activity.getApplicationContext();
        initialize();

        try
        {
            DbAvailabilityService.getNewInstance().configureDatabaseButtons(activity, new IServiceCallback()
            {   @Override
                public void onServiceComplete(String msg)
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
            DbVersionService.getNewInstance().adjustButtonsStates(activity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void initialize()
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        createDatabaseBt = (Button) activity.findViewById(R.id.createDatabaseBt);
        dropDatabaseBt = (Button) activity.findViewById(R.id.deleteDatabaseBt);
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
                    if (C.sysIsBroken(context))
                    {
                        throw new IllegalArgumentException("Activity is null");
                    }

                    C.createAppDB(context);

                    DbAvailabilityService.getNewInstance().configureDatabaseButtons(activity, new IServiceCallback() {
                        @Override
                        public void onServiceComplete(String msg)
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

        dropDatabaseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if (C.sysIsBroken(activity))
                    {
                        throw new IllegalArgumentException("Activity is null");
                    }

                    boolean result = DaoHelper.dropDatabase(AppDaoConfigTwo.DATABASE_NAME);
                    DbAvailabilityService.getNewInstance().configureDatabaseButtons(activity, new IServiceCallback() {
                        @Override
                        public void onServiceComplete(String msg)
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
                    DbVersionService.getNewInstance().upgrade(activity, new IServiceCallback() {
                        @Override
                        public void onServiceComplete(String msg)
                        {
                            try
                            {
                                AppRestartService.getNewInstance().restartApplication(activity.getApplicationContext(), 3000);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
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
                    DbVersionService.getNewInstance().downgrade(activity, new IServiceCallback() {
                        @Override
                        public void onServiceComplete(String msg)
                        {
                            try
                            {
                                AppRestartService.getNewInstance().restartApplication(activity.getApplicationContext(), 3000);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
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

}











