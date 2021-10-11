package app.Columns;

import app.Cell.Cell;
import app.Types.Type;

public class IntegerColumn extends Column {
    public IntegerColumn() {}
    public IntegerColumn(String name){
        this.type = Type.INTEGER;
        this.name = name;
    }
    @Override
    public Type getType() {
        return type;
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
