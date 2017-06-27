package jagerfield.generic.ormlite.data_generators;

import android.content.Context;
import android.util.Log;

import jagerfield.generic.ormlite.models.Building;
import jagerfield.generic.ormlitelib.DaoCrud;
import jagerfield.generic.ormlitelib.DaoHelper;

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

        try
        {
            if (DaoHelper.getInstance(context).isTableExist(Building.class))
            {
                generateData();
            }
            else
            {
                Log.e("TAG", "Table 'Building' is missing");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
                    building.setAddress("Address-" + name + String.valueOf(counter));

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
