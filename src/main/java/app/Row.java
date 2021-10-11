package app;

import app.Cell.Cell;

import java.util.ArrayList;


public class Row {

    private ArrayList<Cell> cells;

    public Row(ArrayList<Cell> cells) {
        this.cells = cells;

    }

    public Cell getCell(int index) {
        return cells.get(index);
    }

    public void addCell(Cell cell){
        this.cells.add(cell);

    }

    public Boolean deleteCell(int ind) {
        if (ind >= this.cells.size())
            return false;
        this.cells.remove(ind);
        return true;
    }

    public int size() {
        return cells.size();
    }
}
