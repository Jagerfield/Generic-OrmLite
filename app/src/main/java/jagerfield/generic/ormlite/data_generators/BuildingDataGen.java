package jagerfield.generic.ormlite.data_generators;

import android.content.Context;
import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlitelib.DaoCrud;

public class BuildingDataGen
{
    public final String name;
    public final int amount;
    private Context context;

    public BuildingDataGen(String name, int amount, Context context)
    {
        this.name = name + "-";
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
                    Building building = new Building();
                    building.setName(name + String.valueOf(counter));
                    building.setAddress("Address-" + String.valueOf(counter));

                    try
                    {
                        DaoCrud.getInstance(context).add(building);
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
