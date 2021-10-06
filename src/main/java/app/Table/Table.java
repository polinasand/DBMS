package app.Table;

import app.Columns.Column;
import app.Row;
import app.Schema;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class Table {
    private ArrayList<Row> rows;
    private String name;
    private Schema schema;

    public Table() {
        this.name = "default"+ (new Random(10000).nextInt());
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
        if (row.size() == schemaKeys.size()) {
            for (int i = 0; i < row.size(); i++) {
                String value = row.getCell(i).getValue().toString();
                Column column = schema.getColumn(schemaKeys.get(i));
                System.out.println(value+' '+column.getType().toString());
                if (!column.validateValue(value)) {
                    return false;

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
            if (validateRow(row)) {
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
        System.out.println(gson);
        System.out.println(gsonRows);
        System.out.println(gsonRows.contains(gson));
        return gsonRows.contains(gson);
    }
}

