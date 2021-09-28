package app;

import java.util.Collection;
import java.util.HashMap;

public class Database {
    private HashMap<String, Table> tables;
    private String name;

    public Database() {
        this.name = "db1";
        this.tables = new HashMap<>();
    }

    public Table get(String name) {
        return this.tables.getOrDefault(name, null);
    }

    public Collection<Table> getList() {
        return tables.values();
    }

    public String getName() {
        return name;
    }

    public Boolean add(Table table) {
        if (tables.containsKey(table.getName())) {
            System.out.println("Duplicate value for table name");
            return false;
        }
        tables.put(table.getName(), table);
        return true;
    }

    public Boolean delete(String name) {
        if (!tables.containsKey(name)) {
            System.out.println("Table name does not exists");
            return false;
        }
        tables.remove(name);
        return true;
    }

}
