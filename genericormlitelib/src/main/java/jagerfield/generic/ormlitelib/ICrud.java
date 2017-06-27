package jagerfield.generic.ormlitelib;

import com.j256.ormlite.dao.Dao;

import java.util.List;

public interface ICrud<T, Serializable>
{
    List getAll(Class T) throws Exception;
    T getById(Class T, int id) throws Exception;
    List<T> getByColumn(Class T, String columnName, Serializable value) throws Exception;
    void deleteById(Class T, int id) throws Exception;
    int add(T t) throws Exception;
    Dao.CreateOrUpdateStatus update(T t) throws Exception;
}
