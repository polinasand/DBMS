package app.Columns;

import app.Cell;
import app.Types.TextFile;
import app.Types.Type;

public class TextFileColumn extends Column{
    public TextFileColumn(String name){
        this.name = name;
    }
    @Override
    public Type getType() {
        return Type.TEXTFILE;
    }

    @Override
    public Cell getDefault() {
        return new Cell(new TextFile("default.txt", "abc"));
    }
    @Override
    public Boolean validateValue(String value) {
        return TextFile.parseTextFile(value) != null;
    }
}
