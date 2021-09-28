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

    public Boolean addRows(ArrayList<Row> rows) {
        if (rows.size() == 0)
            return true;
        Schema rowsSchema = rows.get(0).getSchema();
        if (rowsSchema.equals(schema)) {
            this.rows.addAll(rows);
            return true;
        }
        System.out.println("Wrong schema.");
        return false;
    }

    public Boolean deleteRow(int index) {
        if (index >= 0 && index < rows.size()) {
            this.rows.remove(index);
            return true;
        }
        return false;
    }
}
