package jagerfield.generic.ormlite.recycler_adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.models.Person;

public class PersonListViewAdaptor extends RecyclerView.Adapter<PersonListViewAdaptor.ViewHolder>
{
    private ArrayList<Person> itemsList = new ArrayList<>();

    public PersonListViewAdaptor(ArrayList<Person> list)
    {
        itemsList.addAll(list);
        instance = this;
    }

//    public PersonListViewAdaptor()
//    {
//
//    }

    private static PersonListViewAdaptor instance;

    public static PersonListViewAdaptor getInstance()
    {
        if (instance==null)
        {
            ArrayList<Person> list = new ArrayList<>();
            return new PersonListViewAdaptor(list);
        }

        return instance;
    }

    @Override
    public PersonListViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = null;
        try
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonListViewAdaptor.ViewHolder holder, int position)
    {
        holder.textOne.setText(itemsList.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        int i = itemsList.size();
        return i;
    }

    public void updateList(ArrayList<Person> list)
    {
        this.itemsList = list;
        notifyDataSetChanged();
    }

    public void updateList(Person person)
    {
        if (person!=null)
        {
            itemsList.add(person);
        }
//        ArrayList<Person> list = new ArrayList<>();
//        list.addAll(itemsList);
//        list.add(person);
//
//        itemsList = new ArrayList<>();
//        for (Person p:list)
//        {
//            Person object = p;
//            itemsList.add(object);
//        }
//
        this.notifyDataSetChanged();

    }

public class ViewHolder extends RecyclerView.ViewHolder
{
    public final View hView;
    public final TextView textOne;

    public ViewHolder(View view)
    {
        super(view);
        hView = view;
        textOne = (TextView) view.findViewById(R.id.recyclerTextItemOne);
    }
}
}