package jagerfield.generic.ormlite.dashboard_presenters.services;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import jagerfield.generic.ormlite.app_utils.C;

public class AppRestartService
{
    public AppRestartService()
    {}

    public static AppRestartService getNewInstance()
    {
        return new AppRestartService();
    }

    public void restartApplication(Context context, int restartDelay) throws Exception
    {
        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Activity is null");
        }

        final Context con = context;
        MyHandler<DbVersionService> handler = new MyHandler(this);
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                C.restartApplication(con);
            }
        }, restartDelay);
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
