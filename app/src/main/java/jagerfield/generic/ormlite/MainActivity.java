package jagerfield.generic.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jagerfield.generic.ormlite.app_utils.C;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigOne;
import jagerfield.generic.ormlite.dao_config.AppDaoConfigTwo;
import jagerfield.generic.ormlite.data_generators.BuildingDataGen;
import jagerfield.generic.ormlite.data_generators.EmployeeDataGen;
import jagerfield.generic.ormlite.data_generators.PersonDataGen;
import jagerfield.generic.ormlitelib.DaoHelper;
import jagerfield.generic.ormlitelib.DaoCrud;

public class MainActivity extends AppCompatActivity
{
    private DaoCrud entityCrud;
    private final int amount = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            C.dropDB(C.DB_GIVEN_NAME, getApplicationContext());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        DaoHelper.initializeDaoAndTables(getApplicationContext(), new AppDaoConfigOne(this));

        entityCrud = DaoCrud.getInstance(this);

        int version = DaoHelper.getInstance(this).getDatabaseVersion();

        boolean isOpen = DaoHelper.getInstance(this).isOpen();
        String dbName = DaoHelper.getInstance(this).getDatabaseName();

        String[] list1 = this.databaseList();

        String[] list2 = this.databaseList();
        isOpen = DaoHelper.getInstance(this).isOpen();
        dbName = DaoHelper.getInstance(this).getDatabaseName();
        String[] list3 = this.databaseList();


        UserInteractionPresenter userInteraction = new UserInteractionPresenter(this);

//        Button btnSave = (Button) findViewById(R.id.concurrentLoadBt);
//        btnSave.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//
//                try
//                {
//                    DaoHelper.getInstance(MainActivity.this).clearData(MainActivity.this, Person.class);
//                    DaoHelper.getInstance(MainActivity.this).clearData(MainActivity.this, Building.class);
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//                activateThreads();
////                txtName = (EditText) findViewById(R.id.txtName);
////                txtAge = (EditText) findViewById(R.id.txtAge);
////                if (txtName.getText().toString().isEmpty() || txtAge.getText().toString().isEmpty()) {
////                    return;
////                }
////                Person person = new Person();
////                person.setName(txtName.getText().toString());
////                person.setAge(Integer.valueOf(txtAge.getText().toString()));
////                // Add the person to database
////                try
////                {
////                    entityCrud.add(person);
////                }
////                catch (Exception e)
////                {
////                    e.printStackTrace();
////                }
//            }
//        });
//
//        Button btnSearch = (Button) findViewById(R.id.concurrentListBt);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                List<Person> persons = null;
//                List<Building> buildings = null;
//
//                try
//                {
//                    persons = entityCrud.getAll(Person.class);
//                    buildings = entityCrud.getAll(Building.class);
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                    return;
//                }
//
//                if (persons == null || persons.isEmpty())
//                {
//                    return;
//                }
//
////                try
////                {
////                    DaoHelper.getInstance(MainActivity.this).clearData(MainActivity.this, Person.class);
////                    DaoHelper.getInstance(MainActivity.this).clearData(MainActivity.this, Building.class);
////                }
////                catch (Exception e)
////                {
////                    e.printStackTrace();
////                }
//
////                TextView lblName = (TextView) findViewById(R.id.lblName);
////                TextView lblAge = (TextView) findViewById(R.id.lblAge);
////                Person person = persons.get(0);
////                lblName.setText(person.getName());
////                lblAge.setText(person.getAge());
//            }
//        });
//
//        Button btnDropBuilding = (Button) findViewById(R.id.deleteBuidlingsTableBt);
//        btnDropBuilding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                try
//                {
//                    int result = dropBuildingTable(Building.class);
//                    String str = "";
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        Button btnDropDB = (Button) findViewById(R.id.deleteDatabaseBt);
//        btnDropDB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                try
//                {
//                    try
//                    {
//                        C.dropDB(C.DB_GIVEN_NAME, getApplicationContext());
//                    }
//                    catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    String str = "";
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    private void activateThreads()
    {
        PersonDataGen personDataGen1 = new PersonDataGen("Luke", amount, MainActivity.this);
        PersonDataGen personDataGen2 = new PersonDataGen("Peter", amount, MainActivity.this);
        BuildingDataGen buildingDataGen1 = new BuildingDataGen("Building A", amount, this);
        BuildingDataGen buildingDataGen2 = new BuildingDataGen("Building B", amount, this);

        EmployeeDataGen employeeDataGen = new EmployeeDataGen(amount, this);

//        new Thread(new Runnable() {
//            @Override
//            public void run()
//            {
//                int counter = 0;
//                while (counter<amount)
//                {
//                    Person person = new Person();
//                    person.setName("Luke-" + String.valueOf(counter));
//                    person.setAge(30+counter);
//
//                    try
//                    {
//                        entityCrud.add(person);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                    counter++;
//                }
//
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run()
//            {
//                int counter = 0;
//                while (counter<amount)
//                {
//                    Person person = new Person();
//                    person.setName("Peter-" + String.valueOf(counter));
//                    person.setAge(40+counter);
//
//                    try
//                    {
//                        entityCrud.add(person);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                    counter++;
//                }
//
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run()
//            {
//                int counter = 0;
//                while (counter<amount)
//                {
//                    Building building = new Building();
//                    building.setName("Building A -" + String.valueOf(counter));
//
//                    try
//                    {
//                        entityCrud.add(building);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                    counter++;
//                }
//
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run()
//            {
//                int counter = 0;
//                while (counter<amount)
//                {
//                    Building building = new Building();
//                    building.setName("Building B -" + String.valueOf(counter));
//
//                    try
//                    {
//                        entityCrud.add(building);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                    counter++;
//                }
//
//            }
//        }).start();
    }

    private int dropBuildingTable(Class T) throws Exception
    {
        return DaoHelper.getInstance(this).dropTable(T, true);
    }


//    private boolean dropDB() throws Exception
//    {
//        return DaoHelper.getInstance(this).dropDatabase(C.DB_GIVEN_NAME;);
//    }
}