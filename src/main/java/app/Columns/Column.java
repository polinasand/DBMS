package app.Columns;


import app.Cell;
import app.types.Type;

public abstract class Column {
    public String name;

    public Column() { }

    public abstract Type getType();
    public abstract Boolean validateValue(String value);
    public abstract Cell getDefault();

    public static Column getColumn(String name, Type type) {
        switch(type) {
            case INTEGER:
                return new IntegerColumn(name);
            case REAL:
                return new RealColumn(name);
            case STRING:
                return new StringColumn(name);
            case CHAR:
                return new CharColumn(name);
            case TEXTFILE:
                return new TextFileColumn(name);
            case INTINTVL:
                return new IntInvlColumn(name);
            case CHARINVL:
                return new CharInvlColumn(name);
            default:
                return null;

        }

    }


}
