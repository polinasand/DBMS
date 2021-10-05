package app.Columns;

import app.Cell;
import app.types.IntInvl;
import app.types.Type;

public class IntInvlColumn extends Column{
    public IntInvlColumn(String name){
        this.name = name;
    }
    @Override
    public Type getType() {
        return Type.INTINTVL;
    }

    @Override
    public Cell getDefault() {
        return new Cell(new IntInvl());
    }
    @Override
    public Boolean validateValue(String value) {
        return IntInvl.parseString(value) != null;
    }
}
