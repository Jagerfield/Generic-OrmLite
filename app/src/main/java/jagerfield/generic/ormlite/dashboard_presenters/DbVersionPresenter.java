package jagerfield.generic.ormlite.dashboard_presenters;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.UserInteractionPresenter;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.app_utils.PrefrenceUtil;
import jagerfield.generic.ormlitelib.DaoHelper;

public class DbVersionPresenter
{
    private final Activity activity;
    private final Context context;

    public DbVersionPresenter(Activity activity)
    {
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public static DbVersionPresenter execute(Activity activity) throws Exception
    {
        return new DbVersionPresenter(activity);
    }

    public void changeDbVersion(int nextVersion, UserInteractionPresenter.ICallback iCallbackMainActivity) throws Exception
    {
        if (nextVersion>3)
        {
            nextVersion = 3;
            iCallbackMainActivity.showMessage("Highest DB version is 3, app will restart now");
        }
        else if (nextVersion<=0)
        {
            nextVersion = 1;
            iCallbackMainActivity.showMessage("Lowest DB version is 1, app will restart now");
        }
        else
        {
            iCallbackMainActivity.showMessage("App will restart now to change the DB version");
        }

        PrefrenceUtil.setInt(context, C.APPDB_VERSION_KEY, nextVersion);

        restartApplication(context);
    }

    public int getDaoDbVersion() throws Exception
    {
        TextView dbVersionTv = (TextView) activity.findViewById(R.id.dbVersionTv);
        Button downgradeDbVersionBt = (Button) activity.findViewById(R.id.downgradeDbVersionBt);
        Button updateDbVersionBt = (Button) activity.findViewById(R.id.updateDbVersionBt);

        int version = 0;
        try
        {
            version = DaoHelper.getInstance(context).getDatabaseVersion();
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

    private void restartApplication(Context context)
    {

        // Post a message and delay its execution for 10 minutes.
//        mHandler.postDelayed(sRunnable, 1000 * 60 * 10);

        final Context con = context;
        MyHandler<DbVersionPresenter> handler = new MyHandler(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                C.restartApplication(con);
            }
        }, 2000);
    }

    public static class AgumentsPackage
    {
        private Context context;
        private UserInteractionPresenter.ICallback iCallbackMainActivity;
        private int nextVersion;

    }

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

    private static class MyHandler<T> extends Handler
    {
        private final WeakReference<T> weakReference;

        public MyHandler(T outer)
        {
            weakReference = new WeakReference<T>(outer);
        }

        @Override
        public void handleMessage(Message msg)
        {
            T outer = weakReference.get();
            if (outer != null)
            {
                String str = "";
            }
        }
    }

}
