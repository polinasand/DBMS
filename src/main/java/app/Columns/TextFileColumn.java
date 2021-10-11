package app.Columns;

import app.Cell.Cell;
import app.Types.TextFile;
import app.Types.Type;

public class TextFileColumn extends Column{
    public TextFileColumn(String name){
        this.type = Type.TEXTFILE;
        this.name = name;
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Cell getDefault() {
        return new Cell(new TextFile("default.txt", ""));
    }
    @Override
    public Boolean validateValue(String value) {
        return TextFile.parseTextFile(value) != null;
    }
}
