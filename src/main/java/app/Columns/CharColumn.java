package app.Columns;

import app.Cell;
import app.types.Type;

public class CharColumn extends Column{
    public CharColumn(String name){
        this.name = name;
    }
    @Override
    public Type getType() {
        return Type.CHAR;
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
