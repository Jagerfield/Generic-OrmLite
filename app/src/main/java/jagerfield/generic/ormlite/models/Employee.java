package jagerfield.generic.ormlite.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;

@DatabaseTable(tableName = "employees")
public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = IColumns.ID, generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(columnName = IColumns.PERSON_FIELD, unique = true, canBeNull = false)
    private Person person;

    @DatabaseField(columnName = IColumns.BUILDING_FIELD, canBeNull = false)
    private Building building;

    public Employee(Person person, Building building)
    {
        this.person = person;
        this.building = building;
    }

    public interface IColumns
    {
        String ID = "id";
        String PERSON_FIELD = "person";
        String BUILDING_FIELD = "building";
    }
}
