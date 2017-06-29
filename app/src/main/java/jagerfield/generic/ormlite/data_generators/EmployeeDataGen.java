package jagerfield.generic.ormlite.data_generators;

import android.content.Context;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlite.models.Employee;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoCrud;

public class EmployeeDataGen
{
    public final int amount;
    private Context context;

    public EmployeeDataGen(int amount, Context context)
    {
        this.amount = amount;
        this.context = context;
        generateData();
    }

    public void generateData()
    {
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

