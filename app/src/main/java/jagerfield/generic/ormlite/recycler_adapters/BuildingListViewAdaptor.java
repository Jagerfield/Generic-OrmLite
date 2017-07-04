package jagerfield.generic.ormlite.recycler_adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import jagerfield.generic.ormlite.R;
import jagerfield.generic.ormlite.models.Building;


public class BuildingListViewAdaptor extends RecyclerView.Adapter<BuildingListViewAdaptor.ViewHolder>
{
    private ArrayList<Building> itemsList = new ArrayList<>();

    public BuildingListViewAdaptor()
    {
        instance = this;
    }

    private static BuildingListViewAdaptor instance;

    public static BuildingListViewAdaptor getInstance()
    {
        if (instance==null)
        {
            return new BuildingListViewAdaptor();
        }

        return instance;
    }

    @Override
    public BuildingListViewAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
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
        return new BuildingListViewAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BuildingListViewAdaptor.ViewHolder holder, int position)
    {
        holder.textOne.setText(itemsList.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        int i = itemsList.size();
        return i;
    }

    public void updateList(ArrayList<Building> list)
    {
        this.itemsList = list;
        notifyDataSetChanged();
    }

    public void updateList(Building building)
    {
        if (building!=null)
        {
            itemsList.add(building);
        }

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