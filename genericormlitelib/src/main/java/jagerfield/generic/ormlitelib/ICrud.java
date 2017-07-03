package jagerfield.generic.ormlitelib;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.io.Serializable;
import java.util.List;

public interface ICrud<T, Serializable>
{
    List getAll(Class T) throws Exception;
    T getById(Class T, int id) throws Exception;
    List<T> getByColumn(Class T, String columnName, Serializable value) throws Exception;
    void deleteById(Class T, int id) throws Exception;
    void clearTable(Context context, Class T) throws Exception;
    int add(T t) throws Exception;
    Dao.CreateOrUpdateStatus update(Class T) throws Exception;
    QueryBuilder<T, java.io.Serializable> queryBuilderGeneric(Class T) throws Exception;
}
