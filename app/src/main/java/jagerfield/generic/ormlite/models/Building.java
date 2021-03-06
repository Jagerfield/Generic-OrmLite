package jagerfield.generic.ormlite.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "buildings")
public class Building
{
    private static final long serialVersionUID = 1L;

    @DatabaseField(columnName = IColumns.ID, generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    @DatabaseField(columnName = IColumns.NAME_FIELD, unique = true, canBeNull = false)
    private String name;

    @DatabaseField(columnName = IColumns.ADDRESS_FIELD, unique = true, canBeNull = false)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public interface IColumns
    {
        String ID = "id";
        String NAME_FIELD = "name";
        String ADDRESS_FIELD = "address";
    }
}
