package app;

import java.util.ArrayList;


public class Row {

    private ArrayList<Cell> cells;

    public Row(ArrayList<Cell> cells) {
        this.cells = cells;

    }

    public Cell getCell(int index) {
        return cells.get(index);
    }

    public int size() {
        return cells.size();
    }
}
