package app.Columns;

import app.Cell.Cell;
import app.Types.IntInvl;
import app.Types.Type;

public class IntInvlColumn extends Column{
    public IntInvlColumn(String name){
        this.type = Type.INTINVL;
        this.name = name;
    }
    @Override
    public Type getType() {
        return type;
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
