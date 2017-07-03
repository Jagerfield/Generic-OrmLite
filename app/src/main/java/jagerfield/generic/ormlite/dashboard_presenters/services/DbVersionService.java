package jagerfield.generic.ormlite.dashboard_presenters.services;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.app_utils.PrefrenceUtil;
import jagerfield.generic.ormlitelib.DaoHelper;

public class DbVersionService
{
//    private final Activity activity;
//    private final int restartDelay = 3000;

    public DbVersionService()
    {   }

    public static DbVersionService getNewInstance() throws Exception
    {
        return new DbVersionService();
    }

    public void upgrade(Activity activity, IServiceCallback iServiceCallback) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }
        int nextVersion = setVersionAndButtonsStates(activity) + 1;
        changeDbVersion(activity,  nextVersion, iServiceCallback);
    }

    public void downgrade(Activity activity, IServiceCallback iServiceCallback) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }
        int nextVersion = setVersionAndButtonsStates(activity) - 1;
        changeDbVersion(activity, nextVersion, iServiceCallback);
    }

    private void changeDbVersion(Activity activity, int nextVersion, IServiceCallback iServiceCallback) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        String msg = "";
        boolean restart = false;
        if (nextVersion>3)
        {
            nextVersion = 3;
            msg = "Highest DB version is 3";
            restart = false;
        }
        else if (nextVersion<=0)
        {
            nextVersion = 1;
            msg = "Lowest DB version is 1";
            restart = false;
        }
        else
        {
            msg = "App will restart now to change the DB version to version " + String.valueOf(nextVersion);
            restart = true;
        }

        if(restart)
        {
            PrefrenceUtil.setInt(activity, C.APPDB_VERSION_KEY, nextVersion);
//            restartApplication(activity);
            iServiceCallback.onServiceComplete(msg);
        }
    }

    public int setVersionAndButtonsStates(Activity activity) throws Exception
    {
        if (C.sysIsBroken(activity))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        Context context = activity.getApplicationContext();
        TextView dbVersionTv = (TextView) activity.findViewById(R.id.dbVersionTv);
        Button downgradeDbVersionBt = (Button) activity.findViewById(R.id.downgradeDbVersionBt);
        Button updateDbVersionBt = (Button) activity.findViewById(R.id.updateDbVersionBt);

        int version = 0;
        try
        {
            version = getDaoDbVersion(context);
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

    public static int getDaoDbVersion(Context context)
        {
        int version;
        version = DaoHelper.getInstance(context).getDatabaseVersion();
        return version;
    }

//    private void restartApplication(Context context)
//    {
//        if (C.sysIsBroken(context))
//        {
//            throw new IllegalArgumentException("Activity is null");
//        }
//
//        final Context con = context;
//        MyHandler<DbVersionService> handler = new MyHandler(this);
//        handler.postDelayed(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                C.restartApplication(con);
//            }
//        }, restartDelay);
//    }

    //    private final MyHandler mHandler = new MyHandler(this);

    /**  http://www.androiddesignpatterns.com/2013/01/inner-class-handler-memory-leak.html
     * Instances of anonymous classes do not hold an implicit
     * reference to their outer class when they are "static".
     */
    //    private static final Runnable sRunnable = new Runnable()
    //    {
    //        @Override
    //        public void run() {C.restartApplication(context); }
    //    };

//    private static class MyHandler<T> extends Handler
//    {
//        private final WeakReference<T> weakReference;
//
//        public MyHandler(T outer)
//        {
//            weakReference = new WeakReference<T>(outer);
//        }
//
//        @Override
//        public void handleMessage(Message msg)
//        {
//            T outer = weakReference.get();
//            if (outer != null)
//            {
//                String str = "";
//            }
//        }
//    }

}
