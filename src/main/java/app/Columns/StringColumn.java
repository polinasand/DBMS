package app.Columns;

import app.Cell;
import app.Types.Type;

public class StringColumn extends Column{
    public StringColumn(String name){
        this.name = name;
    }
    @Override
    public Type getType() {
        return Type.STRING;
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
