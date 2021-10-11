package app.Columns;

import app.Cell.Cell;
import app.Types.Type;

public class StringColumn extends Column{
    public StringColumn() {}
    public StringColumn(String name){
        this.type = Type.STRING;
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
        return true;
    }

}
