package jagerfield.generic.ormlite.data_generators;

import android.content.Context;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoCrud;

public class PersonData
{
    public final String name;
    public final int amount;
    private Context context;

    public PersonData(String name, int amount, Context context)
    {
        this.name = name;
        this.amount = amount;
        this.context = context;
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
