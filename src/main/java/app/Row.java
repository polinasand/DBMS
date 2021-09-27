package app;

import java.util.ArrayList;


public class Row {
    private Schema schema;
    private ArrayList<Cell> cells;

    public Row(Schema schema, ArrayList<Cell> cells) {
        this.schema = schema;
        this.cells = cells;
    }

    public Cell getCell(int index) {
        return cells.get(index);
    }
}
