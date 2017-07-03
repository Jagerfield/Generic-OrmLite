package jagerfield.generic.ormlite.data_generators;

import android.content.Context;
import android.util.Log;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoCrud;
import jagerfield.generic.ormlitelib.DaoHelper;

public class PersonDataGen
{

    public PersonDataGen() {
    }

    public static PersonDataGen getNewInstance()
    {
        return new PersonDataGen();
    }

    public void generateData( Context context_, String name_, final int amount) throws Exception
    {
        final String name = name_ + "-";
        final Context context = context_;

        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Context is null");
        }

        if (!DaoHelper.getInstance(context).isTableExist(Person.class))
        {
            Log.e("TAG", "Table 'persons' is missing");
            throw new IllegalArgumentException("Table 'persons' is missing");
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
                    person.setName(name + String.valueOf(counter));
                    person.setAge(30+counter);

                    try
                    {
                        DaoCrud.getInstance(context).add(person);
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
