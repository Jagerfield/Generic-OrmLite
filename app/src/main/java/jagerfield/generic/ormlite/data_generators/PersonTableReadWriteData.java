package jagerfield.generic.ormlite.data_generators;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.j256.ormlite.stmt.QueryBuilder;
import java.util.List;
import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlitelib.DaoCrud;
import jagerfield.generic.ormlitelib.DaoHelper;

public class PersonTableReadWriteData
{
    private int amount = 0;

    boolean workState = false;

    public PersonTableReadWriteData()
    { }

    public static PersonTableReadWriteData getNewInstance()
    {
        return new PersonTableReadWriteData();
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public boolean isThreadWorking() {
        return workState;
    }

    public void setThreadWorkState(boolean state) {
        this.workState = state;
    }

    public void readWriteData(Activity activity_, final String name_, int amount, final PersonTableReadWriteData.ICall client) throws Exception
    {
        this.amount = amount;
        final Activity activity = activity_;

        checkUp(activity, Person.class);

        try
        {
            DaoCrud.getInstance(activity.getApplicationContext()).clearTable(activity.getApplicationContext(), Person.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            setAmount(0);
            client.oneError("can't write to the Persons table", e);
        }

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int counter = 0;

                while (counter<getAmount())
                {
                    Person person = new Person();
                    String name = name_ + "-" + String.valueOf(counter);
                    person.setName(name);
                    person.setAge(30  + counter);

                    try
                    {
                        if (DaoHelper.getInstance(activity.getApplicationContext()).isTableExist(Person.class))
                        {
                            DaoCrud.getInstance(activity.getApplicationContext()).add(person);
                            readAndPostRow(activity, Person.IColumns.NAME_FIELD, name, 1, client);
                        }
                        else
                        {
                            throw new IllegalStateException("Persons table is missing");
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        setAmount(0);
                        client.oneError("can't write to the Persons table", e);
                    }

                    counter++;
                }

                if(counter<getAmount())
                {
                    workState = true;
                }
                else
                {
                    workState = false;
                }

            }
        }).start();
    }

    public void readAndPostRow(Activity activity_, final String columnName, final String fieldValue, final long limit, final PersonTableReadWriteData.ICall client) throws Exception
    {
        final Activity activity = activity_;

        checkUp(activity, Person.class);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                QueryBuilder<Person, String> queryBuilder = null;

                try
                {
                    queryBuilder = DaoCrud.getInstance(activity.getApplicationContext()).queryBuilderGeneric(Person.class);
                    queryBuilder.where().eq(columnName, fieldValue);
                    queryBuilder.limit(limit);
                    queryBuilder.orderBy(columnName, true);
                    List<Person> list = queryBuilder.query();
                    final Person person = list.get(0);

                    if (list!=null && list.get(0)!=null)
                    {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                client.InsertRowInPersonsTable(person);
                            }
                        });
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    client.oneError("can't read from the Persons table", e);
                }
            }
        }).start();
    }

    private void checkUp(Context context, Class T) throws Exception
    {
        if (C.sysIsBroken(context))
        {
            throw new IllegalArgumentException("Context is null");
        }

        if (!DaoHelper.getInstance(context).isTableExist(T))
        {
            Log.e("TAG", "Table 'Persons' is missing");
            throw new IllegalArgumentException("'Personss' table doesn't exist");
        }
    }

    public interface ICall
    {
        void InsertRowInPersonsTable(Person person);
        void oneError(String msg, Exception e);
    }

}
