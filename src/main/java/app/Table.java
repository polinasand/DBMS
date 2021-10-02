package app;

import java.util.ArrayList;

public class Table {
    private ArrayList<Row> rows;
    private String name;
    private Schema schema;

    public Table(String name, Schema schema, ArrayList<Row> data) {
        this.name = name;
        this.schema = schema;
        this.rows = new ArrayList<>();
        this.addRows(data);
    }

    private Boolean validateRow(Row row) {
        ArrayList<Column> schemaTypes = new ArrayList<>(schema.getColumns());
        /*if (row.size() == schemaTypes.size()) {
            for (int i = 0; i < row.size(); i++) {
                System.out.println(row.getCell(i).getType().toString()+' ' +schemaTypes.get(i).getType().toString());
                if (!row.getCell(i).getType().equals(schemaTypes.get(i).getType()))
                    return false;
            }
        }*/
        return true;
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
        for (Row row : rows) {
            System.out.println("j");
            if (validateRow(row)) {
                System.out.println("j");
                this.rows.add(row);
            }
        }

    }

    public Boolean deleteRow(int index) {
        if (index >= 0 && index < rows.size()) {
            this.rows.remove(index);
            return true;
        }
        return false;
    }
}
