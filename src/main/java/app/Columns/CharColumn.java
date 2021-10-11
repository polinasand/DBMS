package app.Columns;

import app.Cell.Cell;
import app.Types.Type;

public class CharColumn extends Column{
    public CharColumn(String name){
        this.type = Type.CHAR;
        this.name = name;
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Cell getDefault() {
        return new Cell("");
    }
    @Override
    public Boolean validateValue(String value) {
        return value.length()==1;
    }
}
