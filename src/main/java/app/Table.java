package app;

import java.util.ArrayList;

public class Table {
    private ArrayList<Row> rows;
    private String name;
    private Schema schema;

    public Table(String name, Schema schema, ArrayList<Row> data) {
        this.name = name;
        this.schema = schema;
        this.rows = data;
    }

    public String getName() {
        return name;
    }

    public Column getColumn(String key) {
        return schema.getColumn(key);
    }

    public ArrayList<Row> getRows() {
        return this.rows;
    }

    public Row getRow(int index) {
        if (index >= 0 && index < rows.size())
            return this.rows.get(index);
        return null;
    }

    public void addRows(ArrayList<Row> rows) {
        this.rows.addAll(rows);
    }

    public Boolean deleteRow(int index) {
        if (index >= 0 && index < rows.size()) {
            this.rows.remove(index);
            return true;
        }
        return false;
    }
}
