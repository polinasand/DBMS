package app.Columns;

import app.Cell;
import app.Types.Type;

public class IntegerColumn extends Column {

    public IntegerColumn(String name){
        this.name = name;
    }
    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public Cell getDefault() {
        return new Cell(0);
    }
    @Override
    public Boolean validateValue(String value) {
        try {
            Integer.parseInt(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
