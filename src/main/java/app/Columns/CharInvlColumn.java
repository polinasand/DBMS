package app.Columns;

import app.Cell.Cell;
import app.Types.CharInvl;
import app.Types.Type;

public class CharInvlColumn extends Column{
    public CharInvlColumn(String name){
        this.type = Type.CHARINVL;
        this.name = name;
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Cell getDefault() {
        return new Cell(new CharInvl());
    }
    @Override
    public Boolean validateValue(String value) {
        return CharInvl.parseString(value) != null;
    }
}
