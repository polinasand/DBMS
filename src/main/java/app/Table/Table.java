package app.Table;

import app.Cell.Cell;
import app.Columns.Column;
import app.Row.Row;
import app.Schema.Schema;
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

    public Table(String name) {
        this.name = name;
        this.schema = new Schema();
        this.rows = new ArrayList<>();
        //this.addEmptyRow();
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

    public Boolean setRow(int index, Row row) {
        if (index >=0 && index < this.rows.size() && validateRow(row)){
            this.rows.set(index, row);
            return true;
        }
        return false;
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

    public void addEmptyRow(){
        ArrayList<Cell> cells = new ArrayList<>();
        for (String colName : schema.getKeys()) {
            Column col = this.getColumn(colName);
            cells.add(col.getDefault());
        }
        rows.add(new Row(cells));
        System.out.println(rows.size()+" added 1 row");
    }

    public Boolean addColumn(Column column) {
        if (this.schema.add(column)) {
            for (Row row : this.rows) {
                row.addCell(new Cell<>(column.getDefault()));
            }
            return true;
        }
        return false;
    }

    public Boolean deleteColumn(String name) {
        Column column = this.schema.getColumn(name);
        if (column != null) {
            int ind = new ArrayList<>(this.schema.getKeys()).indexOf(name);
            for (Row row : this.rows) {
                if (!row.deleteCell(ind))
                    return false;
            }
            return this.schema.delete(name);

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

