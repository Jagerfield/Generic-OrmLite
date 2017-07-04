package jagerfield.generic.ormlite.data_generators;

import android.content.Context;
import android.util.Log;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlite.models.Employee;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoCrud;
import jagerfield.generic.ormlitelib.DaoHelper;

public class EmployeeTableReadWriteData
{
    public EmployeeTableReadWriteData(int amount, Context context)
    { }

    public static PersonTableReadWriteData getNewInstance()
    {
        return new PersonTableReadWriteData();
    }

    public void generateData(final int amount, final Context context) throws Exception
    {
        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Context is null");
        }

        if (!DaoHelper.getInstance(context).isTableExist(Employee.class))
        {
            Log.e("TAG", "Table 'employees' is missing");
            throw new IllegalArgumentException("Table 'employees' is missing");
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int counter = 0;
                while (counter<amount)
                {
                    Person person = new Person();
                    person.setName("Employee-" + String.valueOf(counter));
                    person.setAge(20+counter);

                    Building building = new Building();
                    building.setName("Building-" + String.valueOf(counter));
                    building.setAddress("Address-" + "Building-" + String.valueOf(counter));

                    Employee employee = new Employee(person, building);

                    try
                    {
                        DaoCrud.getInstance(context).add(employee);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    counter++;
                }
            }
        }).start();
    }
}

