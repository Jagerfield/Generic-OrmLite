package jagerfield.generic.ormlite.models;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "buildings")
public class Building
{
    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = "id", generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(columnName = "name", unique = true, canBeNull = false)
    private String name;

    @DatabaseField(columnName = "address", unique = true, canBeNull = false)
    private String address;

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
        String NAME_FIELD = "name";
    }
}
