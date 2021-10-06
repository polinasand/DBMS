package app.Columns;

import app.Cell;
import app.Types.Type;

public class RealColumn extends Column{
    public RealColumn(String name){
        this.name = name;
    }
    @Override
    public Type getType() {
        return Type.REAL;
    }

    @Override
    public Cell getDefault() {
        return new Cell(0.0);
    }
    @Override
    public Boolean validateValue(String value) {
        try {
            Float.parseFloat(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
