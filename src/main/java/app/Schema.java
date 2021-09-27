package app;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Schema {
    private HashMap<String, Column> columns;

    public Collection<Column> getColumns() {
        return columns.values();
    }

    public Column getColumn(String key) {
        return columns.getOrDefault(key, null);
    }

    public Schema() {
        columns = new HashMap<>();
    }

    public Schema(List<Column> _columns) {
        this.columns = new HashMap<>();
        _columns.forEach(column -> this.columns.put(column.name, column));
    }


}
