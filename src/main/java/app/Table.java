package app;

import app.Columns.Column;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class Table {
    private ArrayList<Row> rows;
    private String name;
    private Schema schema;

    public Table() {
        this.name = "default"+ (new Random(1000000)).toString();
        this.schema = new Schema();
        this.rows = new ArrayList<>();

    }

    public Table(String name, Schema schema, ArrayList<Row> data) {
        this.name = name;
        this.schema = schema;
        this.rows = new ArrayList<>();
        this.addRows(data);
    }

    private Boolean validateRow(Row row) {
        ArrayList<String> schemaKeys = new ArrayList<>(this.schema.getKeys());
        //System.out.println(schemaKeys.get(0).toString()+schemaKeys.get(1));
        if (row.size() == schemaKeys.size()) {
            for (int i = 0; i < row.size(); i++) {
                String value = row.getCell(i).getValue().toString();
                Column column = schema.getColumn(schemaKeys.get(i));
                if (!column.validateValue(value)) {
                    System.out.println(value+' '+column.getType().toString());
                    //return false;
                    return true;
                }

            }
        }
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

    public Schema getSchema() { return this.schema; }
    public Boolean deleteRow(int index) {
        if (index >= 0 && index < rows.size()) {
            this.rows.remove(index);
            return true;
        }
        return false;
    }

    public Boolean contains(Row row) {
        String gson = new Gson().toJson(row);
        String gsonRows = new Gson().toJson(this.rows);
        return gsonRows.contains(gson);
    }
}

