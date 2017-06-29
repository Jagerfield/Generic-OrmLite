package jagerfield.generic.ormlite.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "persons")
public class Person implements Serializable
{
    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = IColumns.ID, generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(columnName = IColumns.NAME_FIELD, unique = true, canBeNull = false)
    private String name;

    @DatabaseField(columnName = IColumns.AGE_FIELD, canBeNull = false)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.id;
    }

    public interface IColumns
    {
        String ID = "id";
        String NAME_FIELD = "name";
        String AGE_FIELD = "age";
    }
}
