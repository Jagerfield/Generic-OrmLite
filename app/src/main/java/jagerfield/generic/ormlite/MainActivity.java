package jagerfield.generic.ormlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import jagerfield.generic.ormlite.dashboard_presenters.ICallbackMainActivity;
import jagerfield.generic.ormlite.dashboard_presenters.Presenter;
import jagerfield.generic.ormlite.models.Person;
import jagerfield.generic.ormlite.recycler_adapters.BuildingListViewAdaptor;
import jagerfield.generic.ormlite.recycler_adapters.PersonListViewAdaptor;

public class MainActivity extends AppCompatActivity implements ICallbackMainActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView personRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewOne);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView buildingRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewTwo);
        buildingRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        PersonListViewAdaptor personAdapter = PersonListViewAdaptor.getInstance();
        personRecyclerView.setAdapter(personAdapter);

        BuildingListViewAdaptor buildingAdapter = BuildingListViewAdaptor.getInstance();
        buildingRecyclerView.setAdapter(buildingAdapter);


        Presenter userInteraction = Presenter.getNewInstance(this, this);

//        Person person = new Person();
//        String name = "Test2" ;
//        person.setName(name);
//        person.setAge(30);
//
//        adapter.updateList(person);

//        try
//        {
//            C.dropDB(C.DB_GIVEN_NAME, getApplicationContext());
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

//        DaoHelper.initializeDaoAndTables(getApplicationContext(), new AppDaoConfigOne(this));

//        entityCrud = DaoCrud.getInstance(this);
//
//        int version = DaoHelper.getInstance(this).getDatabaseVersion();
//
//        boolean isOpen = DaoHelper.getInstance(this).isOpen();
//        String dbName = DaoHelper.getInstance(this).getDatabaseName();
//
//        String[] list1 = this.databaseList();
//
//        String[] list2 = this.databaseList();
//        isOpen = DaoHelper.getInstance(this).isOpen();
//        dbName = DaoHelper.getInstance(this).getDatabaseName();
//        String[] list3 = this.databaseList();


    }

    @Override
    public void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}