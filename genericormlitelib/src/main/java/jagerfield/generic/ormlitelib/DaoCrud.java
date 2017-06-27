package jagerfield.generic.ormlitelib;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import java.io.Serializable;
import java.util.List;

public class DaoCrud<T> implements ICrud<T, Serializable>
{
    private Context context;
    private static DaoCrud instance;
    private DaoCrud(Context context)
    {
        this.context = context;
    }

    public synchronized static DaoCrud getInstance(Context context)
    {
        if (instance == null )
        {
            instance = new DaoCrud(context);
        }

        return instance;
    }

    public synchronized Dao getEntityDao(Class T) throws Exception
    {
        Dao dao = DaoHelper.getInstance(context).getDao(T);
        return dao;
    }

    @Override
    public synchronized List<T> getAll(Class T) throws Exception
    {
        List<T> list = getEntityDao(T).queryForAll();
        return list;
    }

    @Override
    public synchronized T getById(Class T, int id) throws Exception
    {
        T entity = (T) getEntityDao(T).queryForId(id);
        return entity;
    }

    @Override
    public synchronized List<T> getByColumn(Class T, String columnName, Serializable value) throws Exception
    {
        List<T> list = getEntityDao(T).queryForEq(columnName, value);
        return list;
    }

    @Override
    public synchronized void deleteById(Class T, int id) throws Exception
    {
        getEntityDao(T).deleteById(id);
    }

    @Override
    public synchronized int add(T t) throws Exception
    {
        int i = getEntityDao(t.getClass()).create(t);
        return i;
    }

    @Override
    public synchronized com.j256.ormlite.dao.Dao.CreateOrUpdateStatus update(T t) throws Exception
    {
        com.j256.ormlite.dao.Dao.CreateOrUpdateStatus i = getEntityDao(t.getClass()).createOrUpdate(t);
        return i;
    }
}
