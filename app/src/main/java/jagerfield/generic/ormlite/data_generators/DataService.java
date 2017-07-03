package jagerfield.generic.ormlite.data_generators;


import android.app.Activity;
import android.widget.EditText;
import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.app_utils.C;

public class DataService
{
    public DataService() {
    }

    public static DataService getNewInstance()
    {
        return new DataService();
    }

//    public void loadPersonsTable(Activity activity) throws Exception
//    {
//        if (C.sysIsBroken(activity))
//        {
//            throw new IllegalArgumentException("Activity is null");
//        }
//
//        EditText dbConcurrentTv = (EditText) activity.findViewById(R.id.dbConcurrentTv);
//        int amount = Integer.valueOf(dbConcurrentTv.getText().toString());
//
//        PersonDataGen.getNewInstance().generateData(activity.getApplicationContext(), "Peter", amount);
//    }
//
//    public void loadBuildingsTable(Activity activity) throws Exception
//    {
//        if (C.sysIsBroken(activity))
//        {
//            throw new IllegalArgumentException("Activity is null");
//        }
//
//        EditText dbConcurrentTv = (EditText) activity.findViewById(R.id.dbConcurrentTv);
//        int amount = Integer.valueOf(dbConcurrentTv.getText().toString());
//
//        BuildingTableReadWriteData.getNewInstance().writeData(activity.getApplicationContext(), "Building", amount);
//    }
//
//    public void loadEmployeesTable(Activity activity) throws Exception
//    {
//        if (C.sysIsBroken(activity))
//        {
//            throw new IllegalArgumentException("Activity is null");
//        }
//
//        EditText dbConcurrentTv = (EditText) activity.findViewById(R.id.dbConcurrentTv);
//        int amount = Integer.valueOf(dbConcurrentTv.getText().toString());
//
//        BuildingTableReadWriteData.getNewInstance().writeData(activity.getApplicationContext(), "Employee", amount);
//    }
}
