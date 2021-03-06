package app.Schema;

import app.Columns.Column;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Schema {
    private HashMap<String, Column> columns;
    private ArrayList<String> order;

    public Schema() {

        columns = new HashMap<>();
        order = new ArrayList<>();
    }

    public Schema(List<Column> _columns) {
        this.columns = new HashMap<>();
        _columns.forEach(column -> this.columns.put(column.name, column));
        this.order = new ArrayList<>();
        _columns.forEach(column -> this.order.add(column.name));
    }
    public Collection<Column> getColumns() {
        return columns.values();

    }

    public Collection<String> getKeys() { return order; }

    public Column getColumn(String key) {
        return columns.getOrDefault(key, null);
    }

    public Boolean add(Column column) {
        if (columns.get(column.name) == null) {
            columns.put(column.name, column);
            order.add(column.name);
            return true;
        }
        return false;
    }

    public  Boolean delete(String name) {
        if (this.columns.containsKey(name)) {
            this.columns.remove(name);
            this.order.remove(name);
            return true;
        }
        return false;
    }
}
